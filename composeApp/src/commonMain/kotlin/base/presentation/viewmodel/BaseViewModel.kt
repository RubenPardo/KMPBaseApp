package base.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import base.domain.model.Failure
import base.domain.usecase.BaseUseCase
import com.example.baseappv2.base.presentation.screens.BaseAction
import com.example.baseappv2.base.presentation.screens.BaseEffect
import com.example.baseappv2.base.presentation.screens.BaseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

// Clase base para el ViewModel con patrón MVI
abstract class BaseViewModel<S : BaseState, A : BaseAction, E: BaseEffect>(
    initialState: S
) : ViewModel() {

    private val inExecution: MutableList<Int> = mutableListOf()

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    val currentState: S
        get() = _state.value

    // Canal para enviar acciones al ViewModel
    private val _action : MutableSharedFlow<A> = MutableSharedFlow()
    val action = _action.asSharedFlow()

    // Canal para enviar effectos a la Screen
    private val _effect : MutableSharedFlow<E> = MutableSharedFlow()
    val effect = _effect.asSharedFlow()


    // Función para manejar las acciones
    abstract fun handleAction(action: A)

    init {
        viewModelScope.launch {
            action.collect { action ->
                handleAction(action)
            }
        }
    }

    protected fun setState(reduce: S.() -> S) {
        val newState = currentState.reduce()
        _state.value = newState
    }

    fun sendAction(action: A) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    fun sendEffect(effect: E){
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    protected fun <S : BaseState, A : BaseAction, E: BaseEffect, P : Any, M : Any> BaseViewModel<S, A, E>.execute(
        useCase: BaseUseCase<P, M>,
        params: P,
        onSuccess: (M) -> Any,
        onError: (Failure) -> Any = {},
        force: Boolean = false
    ) = viewModelScope.launch(Dispatchers.IO) {
        executeOnContext(Dispatchers.IO) {
            if (!inExecution.contains(useCase.hashCode()) || force) {
                inExecution.add(useCase.hashCode())
                val result = useCase.invoke(params)
                executeOnContext(Dispatchers.Default) {
                    result.fold(onError, onSuccess)
                    inExecution.remove(useCase.hashCode())
                }
            }
        }

    }

    private suspend fun <Result> executeOnContext(
        context: CoroutineContext,
        block: suspend CoroutineScope.() -> Result
    ): Result = withContext(context) { block() }

}
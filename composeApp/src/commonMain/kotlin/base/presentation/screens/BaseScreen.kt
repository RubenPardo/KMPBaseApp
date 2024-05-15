package base.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import base.presentation.viewmodel.BaseViewModel
import com.example.baseappv2.base.presentation.screens.BaseAction
import com.example.baseappv2.base.presentation.screens.BaseEffect
import com.example.baseappv2.base.presentation.screens.BaseState

abstract class BaseScreen<S: BaseState, A: BaseAction, E: BaseEffect, VM : BaseViewModel<S, A, E>>  {
    protected abstract val viewModel: VM

    @Composable
    fun MountScreen() {
        val state = viewModel.state.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.effect.collect{
                handleEffect(it)
            }
        }

        Scaffold(
            topBar = { TopBar(state.value) },
            bottomBar = { BottomBar(state.value) },
            floatingActionButton = { FAB(state.value) },
            content = { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)){
                    Content(state.value)
                }
            }
        )
    }

    protected fun sendAction(action: A){
        viewModel.sendAction(action)
    }

    abstract fun handleEffect(effect: E)

    @Composable
    protected open fun TopBar(state: S) = Box{}

    @Composable
    protected open fun BottomBar(state: S)  = Box{}

    @Composable
    protected open fun Content(state: S) = Box{}

    @Composable
    protected open fun FAB(state: S) = Box{}


}
package presentation.screens.list

import base.domain.model.Failure
import com.example.baseappv2.base.presentation.screens.BaseAction
import com.example.baseappv2.base.presentation.screens.BaseEffect
import com.example.baseappv2.base.presentation.screens.BaseState
import domain.model.DogModel

class ListContract {

    data class State(
        val isLoading: Boolean = false,
        val error: Failure? = null,
        val data: List<DogModel> = emptyList()
    ) : BaseState {

        val isError: Boolean
            get() = error != null
    }

    sealed class Action : BaseAction {
        data object Initialize : Action()
    }

    sealed class Effect : BaseEffect
}
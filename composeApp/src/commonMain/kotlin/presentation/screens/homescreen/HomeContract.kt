package presentation.screens.homescreen

import com.example.baseappv2.base.presentation.screens.BaseAction
import com.example.baseappv2.base.presentation.screens.BaseEffect
import com.example.baseappv2.base.presentation.screens.BaseState

class HomeContract {

    data class State(
        val isLoading: Boolean = false,
        val error: Error? = null,
        val currentIndexPage:Int = 0
    ): BaseState {

        val isError: Boolean
            get() = error != null
    }

    sealed class Action: BaseAction {
        data class ChangePage(val index:Int): Action()
    }

    sealed class Effect: BaseEffect {

    }
}
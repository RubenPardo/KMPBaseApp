package presentation.screens.loginscreen

import com.example.baseappv2.base.presentation.screens.BaseAction
import com.example.baseappv2.base.presentation.screens.BaseEffect
import com.example.baseappv2.base.presentation.screens.BaseState

class LoginContract {

    data class State(
        val isLoading: Boolean = false,
        val error: Error? = null
    ): BaseState {

        val isError: Boolean
            get() = error != null
    }

    sealed class Action: BaseAction {
        data class Login(val name:String, val password:String): Action()
    }

    sealed class Effect: BaseEffect {
        data class GoToHome(val name:String, val password:String): Effect()
    }
}
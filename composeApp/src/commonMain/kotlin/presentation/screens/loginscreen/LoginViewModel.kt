package presentation.screens.loginscreen

import androidx.lifecycle.viewModelScope
import base.presentation.viewmodel.BaseViewModel
import presentation.screens.loginscreen.LoginContract.Action
import presentation.screens.loginscreen.LoginContract.Effect
import presentation.screens.loginscreen.LoginContract.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel<State, Action, Effect>(
    initialState = State()
) {
    override fun handleAction(action: Action) {
        when (action) {
            is Action.Login -> executeLogin(action)
        }
    }

    private fun executeLogin(action: Action.Login) {
        viewModelScope.launch {

            setState { copy(isLoading = true, error = null) }
            delay(1000)
            if (action.name.isNotBlank() && action.password.isNotBlank()) {
                sendEffect(Effect.GoToHome(action.name, action.password))
            }
        }
    }
}
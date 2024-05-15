package presentation.screens.loginscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import base.presentation.screens.BaseScreen
import presentation.screens.loginscreen.LoginContract.Action
import presentation.screens.loginscreen.LoginContract.Effect
import presentation.screens.loginscreen.LoginContract.State


class LoginScreen(
    override val viewModel: LoginViewModel,
    val goToHome: () -> Unit = { },
) : BaseScreen<State, Action, Effect, LoginViewModel>() {

    override fun handleEffect(effect: Effect) {
        when (effect) {
            is Effect.GoToHome -> executeGoToHome(effect)
        }
    }

    private fun executeGoToHome(effect: Effect.GoToHome) {
        goToHome()
    }

    @Composable
    override fun Content(state: State) {

        when {
            state.isLoading -> BuildLoading()
            state.isError -> BuildError()
            else -> BuildContent()
        }

    }


    @Composable
    fun BuildContent() {
        var name by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val isValid: Boolean by remember {
            derivedStateOf {
                name.isNotBlank() && password.isNotBlank()
            }
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center.also { spacedBy(16.dp) },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                isError = name.isBlank(),
                label = { Text("Name") },
                value = name,
                onValueChange = {
                    name = it
                }
            )
            Box(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                isError = password.isBlank(),
                label = { Text("Password") },
                value = password,
                onValueChange = {
                    password = it
                }
            )
            Box(modifier = Modifier.height(32.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = isValid,
                onClick = { sendAction(Action.Login(name, password)) }
            ) {
                Text(text = "Login")
            }
        }
    }

    @Composable
    fun BuildLoading() {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }

    @Composable
    fun BuildError() {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = ":("
            )
        }
    }
}
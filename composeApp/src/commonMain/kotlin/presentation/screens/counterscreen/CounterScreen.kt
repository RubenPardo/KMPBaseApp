package presentation.screens.counterscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import base.presentation.screens.BaseScreen

class CounterScreen(
    override val viewModel: CounterViewModel,
    val name: String = "", val password: String = "",
) : BaseScreen<CounterState, CounterAction, CounterEffect, CounterViewModel>() {

    @Composable
    override fun FAB(state: CounterState) {
        FloatingActionButton(onClick = {
            sendAction(CounterAction.IncrementAction)
        }) {
            Icon(imageVector = Icons.Filled.Add, "")
        }
    }

    @Composable
    override fun TopBar(state: CounterState) {
        TopAppBar(
            title = {
                Text(text = "Counter Page")
            }
        )
    }

    @Composable
    override fun Content(state: CounterState) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Contador: ${state.counter}")
        }
    }

    override fun handleEffect(effect: CounterEffect) {

    }
}
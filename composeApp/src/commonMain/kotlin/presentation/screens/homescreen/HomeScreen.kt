package presentation.screens.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import base.presentation.screens.BaseScreen
import di.koinViewModel
import presentation.screens.counterscreen.CounterScreen
import presentation.screens.counterscreen.CounterViewModel
import presentation.screens.homescreen.HomeContract.Action
import presentation.screens.homescreen.HomeContract.Effect
import presentation.screens.homescreen.HomeContract.State
import presentation.screens.list.ListScreen
import presentation.screens.list.ListViewModel

class HomeScreen(
    override val viewModel: HomeViewModel
) : BaseScreen<State, Action, Effect, HomeViewModel>() {


    data class BottomItem(
        val icon: ImageVector,
        val name: String,
        val page: @Composable () -> Unit
    )

    private val bottomItems: List<BottomItem> = listOf(
        BottomItem(
            icon = Icons.Filled.Add, name = "Counter"
        ) { CounterScreen(koinViewModel<CounterViewModel>()).MountScreen() },
        BottomItem(
            icon = Icons.Filled.List, name = "List"
        ) { ListScreen(koinViewModel<ListViewModel>()).MountScreen() },
        BottomItem(
            icon = Icons.Filled.Face, name = "Camera"
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Item3")
            }
        },
    )


    override fun handleEffect(effect: Effect) {
        // no-op
    }

    @Composable
    override fun Content(state: State) {
        bottomItems[state.currentIndexPage].page.invoke()
    }

    @Composable
    override fun BottomBar(state: State) {
        BottomNavigation {
            bottomItems.forEachIndexed { index, item ->
                key(item.name.hashCode()) {
                    val isSelected = state.currentIndexPage == index
                    BottomNavigationItem(
                        label = { Text(text = item.name) },
                        selected = isSelected,
                        onClick = {
                            if (!isSelected) {
                                sendAction(Action.ChangePage(index))
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.name) }
                    )
                }
            }
        }
    }


}
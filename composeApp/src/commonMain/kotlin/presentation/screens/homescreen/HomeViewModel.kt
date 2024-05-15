package presentation.screens.homescreen

import base.presentation.viewmodel.BaseViewModel
import presentation.screens.homescreen.HomeContract.Action
import presentation.screens.homescreen.HomeContract.Effect
import presentation.screens.homescreen.HomeContract.State

class HomeViewModel: BaseViewModel<State, Action, Effect>(
    initialState = State()
) {

    override fun handleAction(action: Action) {
       when(action){
           is Action.ChangePage -> executeChangePage(action)
       }
    }

    private fun executeChangePage(action: Action.ChangePage) {
        setState {
            copy(currentIndexPage = action.index)
        }
    }

}
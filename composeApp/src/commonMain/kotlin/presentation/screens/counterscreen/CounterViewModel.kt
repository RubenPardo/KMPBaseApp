package presentation.screens.counterscreen

import base.presentation.viewmodel.BaseViewModel
import com.example.baseappv2.base.presentation.screens.BaseAction
import com.example.baseappv2.base.presentation.screens.BaseEffect
import com.example.baseappv2.base.presentation.screens.BaseState

data class CounterState(
    val counter: Int = 0
) : BaseState

sealed class CounterAction : BaseAction {
    data object IncrementAction : CounterAction()
}

data object CounterEffect: BaseEffect

class CounterViewModel : BaseViewModel<CounterState, CounterAction, CounterEffect>(
    CounterState()
) {

    override fun handleAction(action: CounterAction) {
        when (action) {
            is CounterAction.IncrementAction -> increment()
        }
    }

    private fun increment() {
        setState{
            copy(counter = this.counter + 1)
        }
    }


}
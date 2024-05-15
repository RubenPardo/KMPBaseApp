package presentation.screens.list

import base.domain.model.Failure
import base.presentation.viewmodel.BaseViewModel
import domain.model.DogModel
import domain.usecase.GetDogsUseCase
import presentation.screens.list.ListContract.Action
import presentation.screens.list.ListContract.Action.Initialize
import presentation.screens.list.ListContract.Effect
import presentation.screens.list.ListContract.State

class ListViewModel(
    private val getDogsUseCase: GetDogsUseCase
) : BaseViewModel<State, Action, Effect>(
    initialState = State()
) {

    init {
        sendAction(Initialize)
    }

    override fun handleAction(action: Action) {
        when (action) {
            is Initialize -> executeInitialize(action)
        }
    }

    private fun executeInitialize(action: Initialize) {
        setState { copy(isLoading = true, error = null) }
        execute(
            useCase = getDogsUseCase,
            params = GetDogsUseCase.Params,
            onSuccess = ::onInitializeSuccess,
            onError = ::onInitializeError
        )
    }

    private fun onInitializeError(failure: Failure) {
        setState { copy(isLoading = false, error = failure) }
    }

    private fun onInitializeSuccess(dogModels: List<DogModel>) {
        setState { copy(isLoading = false, error = null, data = dogModels) }
    }
}
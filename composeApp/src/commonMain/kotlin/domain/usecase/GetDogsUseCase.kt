package domain.usecase

import base.domain.model.Failure
import base.domain.model.Result
import base.domain.usecase.BaseUseCase
import domain.model.DogModel
import domain.repository.DogRepository

class GetDogsUseCase(
    private val repository: DogRepository
): BaseUseCase<GetDogsUseCase.Params, List<DogModel>> {


    data object Params

    override suspend fun invoke(
        params: Params
    ): Result<Failure, List<DogModel>> = repository.getAll()
}
package base.domain.usecase

import base.domain.model.Failure
import base.domain.model.Result


interface BaseUseCase<in Params, out Type> where Type : Any {

    suspend operator fun invoke(params:Params): Result<Failure, Type>

}
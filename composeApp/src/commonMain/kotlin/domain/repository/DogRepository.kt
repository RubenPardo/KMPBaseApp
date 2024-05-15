package domain.repository

import base.domain.model.Failure
import base.domain.model.Result
import domain.model.DogModel

interface DogRepository {

    suspend fun getAll(): Result<Failure,List<DogModel>>

}
package di.data

import data.repository.DogRepositoryMock
import domain.repository.DogRepository
import org.koin.dsl.module

val dataModule = module {
    single<DogRepository> { DogRepositoryMock() }
}
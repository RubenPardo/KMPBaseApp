package di.domain

import domain.usecase.GetDogsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetDogsUseCase)
}
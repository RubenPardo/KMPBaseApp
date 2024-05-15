package di.presentation

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import presentation.screens.counterscreen.CounterScreen
import presentation.screens.counterscreen.CounterViewModel
import presentation.screens.homescreen.HomeScreen
import presentation.screens.homescreen.HomeViewModel
import presentation.screens.list.ListScreen
import presentation.screens.list.ListViewModel
import presentation.screens.loginscreen.LoginScreen
import presentation.screens.loginscreen.LoginViewModel

actual val presentationModule = module {
    single<LoginScreen> { LoginScreen(get()) }
    single<CounterScreen> { CounterScreen(get()) }
    single<HomeScreen> { HomeScreen(get()) }
    single<ListScreen> { ListScreen(get()) }
    singleOf(::CounterViewModel)
    singleOf(::LoginViewModel)
    singleOf(::HomeViewModel)
    singleOf(::ListViewModel)
}
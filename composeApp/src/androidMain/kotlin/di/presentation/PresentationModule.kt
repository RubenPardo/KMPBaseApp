package di.presentation

import org.koin.androidx.viewmodel.dsl.viewModelOf
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
    viewModelOf(::CounterViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ListViewModel)
}
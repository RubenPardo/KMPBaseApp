package presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.baseappv2.navigation.Screen.HomeScreenRoute
import com.example.baseappv2.navigation.Screen.LoginScreenRoute
import di.koinViewModel
import presentation.screens.homescreen.HomeScreen
import presentation.screens.homescreen.HomeViewModel
import presentation.screens.loginscreen.LoginScreen
import presentation.screens.loginscreen.LoginViewModel

fun NavGraphBuilder.addHomeScreen(navController: NavController) {
    composable(
        HomeScreenRoute.route
    ) {
        HomeScreen(
            koinViewModel<HomeViewModel>(),
        ).MountScreen()
    }
}

fun NavGraphBuilder.addLogin(navController: NavController) {
    composable(LoginScreenRoute.route) {
        LoginScreen(
            koinViewModel<LoginViewModel>(),
            goToHome = {
                navController.navigate(HomeScreenRoute.route) {
                    popUpTo(LoginScreenRoute.route) {
                        inclusive = true
                    }
                }
            }
        ).MountScreen()
    }
}

/*
======================================================
======================================================
NAVIGATION WITH PARAMS
======================================================
======================================================

fun NavGraphBuilder.addHomeScreen(navController: NavController) {
    composable(
        HomeScreenRoute.route + "/{name}/{password}",
        HomeScreenRoute.arguments
    ) {
        val name = it.arguments?.getString("name") ?: ""
        val password = it.arguments?.getString("password") ?: ""
        CounterScreen(
            koinViewModel(),
            name, password,
        ).MountScreen()
    }
}

fun NavGraphBuilder.addLogin(navController: NavController) {
    composable(LoginScreenRoute.route) {
        LoginScreen(
            koinViewModel(),
            goToHome = { name, password ->
                navController.navigate(HomeScreenRoute.route + "/$name/$password") {
                    popUpTo(LoginScreenRoute.route) {
                        inclusive = true
                    }
                }
            }
        ).MountScreen()
    }
}*/


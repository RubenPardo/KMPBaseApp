package com.example.baseappv2.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route:String,
    val arguments: List<NamedNavArgument>
){

    data object LoginScreenRoute : Screen(
        route = "login",
        arguments = emptyList()
    )

    data object HomeScreenRoute : Screen(
        route = "home",
        arguments = emptyList()
    )

    /*data object HomeScreenRoute : Screen(
        route = "home",
        arguments = listOf(
            navArgument("name") { type = NavType.StringType },
            navArgument("password") { type = NavType.StringType }
        )
    )*/

}
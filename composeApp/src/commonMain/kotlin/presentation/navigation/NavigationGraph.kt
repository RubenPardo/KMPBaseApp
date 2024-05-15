package presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.baseappv2.navigation.Screen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreenRoute.route
    ) {
        addLogin(navController)
        addHomeScreen(navController)

    }
}
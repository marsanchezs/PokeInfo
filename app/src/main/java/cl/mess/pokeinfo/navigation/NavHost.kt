package cl.mess.pokeinfo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.mess.pokeinfo.detail.ui.DetailScreen
import cl.mess.pokeinfo.home.ui.HomeScreen
import cl.mess.pokeinfo.navigation.Constants.ID

@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.Detail.route + "/{id}",
            arguments = listOf(
                navArgument(ID) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(ID) ?: return@composable
            DetailScreen(
                navigateToHome = { navController.popBackStack() },
                id = id
            )
        }
    }
}

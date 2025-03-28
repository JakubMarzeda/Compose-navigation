package com.example.compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }

        composable(
            "bmi/{name}/{height}/{weight}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("height") { type = NavType.StringType },
                navArgument("weight") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val height = backStackEntry.arguments?.getString("height") ?: ""
            val weight = backStackEntry.arguments?.getString("weight") ?: ""
            BmiScreen(navController, name, height, weight)
        }

        composable(
            "api/{name}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            ApiScreen(navController, name)
        }
    }
}

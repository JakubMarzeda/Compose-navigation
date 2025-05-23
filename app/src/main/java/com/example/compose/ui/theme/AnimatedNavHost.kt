package com.example.compose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.NavHostController
import androidx.compose.animation.*
import androidx.compose.animation.core.tween

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNavigation(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = "main",
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
        popEnterTransition = { fadeIn(animationSpec = tween(300)) },
        popExitTransition = { fadeOut(animationSpec = tween(300)) }
    ) {
        composable("main") {
            MainScreen(navController)
        }
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
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            ApiScreen(navController, name)
        }
    }
}

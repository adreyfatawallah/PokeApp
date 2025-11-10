package com.example.pokeapp.features.auth.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.pokeapp.features.auth.presentation.screen.login.LoginScreen
import com.example.pokeapp.features.auth.presentation.screen.register.RegisterScreen
import com.example.pokeapp.features.home.presentation.navigation.HOME_GRAPH_ROUTE

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = AuthRoute.Login.route,
        route = AUTH_GRAPH_ROUTE,
    ) {
        composable(route = AuthRoute.Login.route) {
            LoginScreen(
                navigateToRegister = {
                    navController.navigate(AuthRoute.Register.route)
                },
                navigateToHome = {
                    navController.navigate(HOME_GRAPH_ROUTE) {
                        popUpTo(AUTH_GRAPH_ROUTE) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(route = AuthRoute.Register.route) {
            RegisterScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
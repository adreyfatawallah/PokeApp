package com.example.pokeapp.features.home.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokeapp.features.poke.presentation.list.PokeListScreen
import com.example.pokeapp.features.profile.presentation.ProfileScreen

@Composable
fun HomeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = HOME_GRAPH_ROUTE,
        startDestination = HomeRoute.Poke.route
    ) {
        composable(route = HomeRoute.Poke.route) {
            PokeListScreen(
                modifier = modifier
            )
        }
        composable(route = HomeRoute.Profile.route) {
            ProfileScreen(
                modifier = modifier
            )
        }
    }
}
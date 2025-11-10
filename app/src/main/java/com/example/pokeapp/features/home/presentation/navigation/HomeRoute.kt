package com.example.pokeapp.features.home.presentation.navigation

const val HOME_GRAPH_ROUTE = "home"

sealed class HomeRoute(val route: String) {
    object Poke : HomeRoute(route = "poke")
    object Profile : HomeRoute(route = "profile")
}
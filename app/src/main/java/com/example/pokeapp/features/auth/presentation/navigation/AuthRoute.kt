package com.example.pokeapp.features.auth.presentation.navigation

const val AUTH_GRAPH_ROUTE = "auth"

sealed class AuthRoute(val route: String) {
    object Login : AuthRoute(route = "login")
    object Register : AuthRoute(route = "register")
}
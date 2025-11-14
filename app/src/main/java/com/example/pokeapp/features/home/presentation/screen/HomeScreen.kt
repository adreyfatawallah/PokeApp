package com.example.pokeapp.features.home.presentation.screen

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokeapp.components.ProgressIndicator
import com.example.pokeapp.features.auth.presentation.navigation.AUTH_GRAPH_ROUTE
import com.example.pokeapp.features.auth.presentation.navigation.authNavGraph
import com.example.pokeapp.features.home.presentation.navigation.HOME_GRAPH_ROUTE
import com.example.pokeapp.features.home.presentation.screen.components.BottomNavBar

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = homeViewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.value == null) {
        ProgressIndicator()
    } else {
        val startDestination = if (uiState.value == true) HOME_GRAPH_ROUTE else AUTH_GRAPH_ROUTE
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = startDestination,
        ) {
            authNavGraph(navController)

            composable(HOME_GRAPH_ROUTE) {
                BottomNavBar()
            }
        }
    }
}
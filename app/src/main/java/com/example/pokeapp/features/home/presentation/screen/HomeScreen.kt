package com.example.pokeapp.features.home.presentation.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokeapp.features.home.presentation.screen.components.BottomNavBar
import com.example.pokeapp.features.auth.presentation.navigation.AUTH_GRAPH_ROUTE
import com.example.pokeapp.features.auth.presentation.navigation.authNavGraph
import com.example.pokeapp.features.home.presentation.navigation.HOME_GRAPH_ROUTE

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = homeViewModel.uiState.collectAsStateWithLifecycle()
    val startDestination = if (uiState.value) HOME_GRAPH_ROUTE else AUTH_GRAPH_ROUTE
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        authNavGraph(navController)

        composable(HOME_GRAPH_ROUTE) {
            homeViewModel.isLogin()
            Log.e("adrey", "HomeScreen")
            BottomNavBar()
        }
    }
}
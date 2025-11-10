package com.example.pokeapp.features.home.presentation.screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.pokeapp.R
import com.example.pokeapp.features.home.presentation.navigation.HomeNavGraph
import com.example.pokeapp.features.home.presentation.navigation.HomeRoute
import com.example.pokeapp.ui.theme.PokeAppTheme

enum class Destination(
    val route: String,
    val label: Int,
    val icon: ImageVector,
) {
    Home(
        route = HomeRoute.Poke.route,
        label = R.string.home,
        icon = Icons.Default.Home
    ),
    Profile(
        route = HomeRoute.Profile.route,
        label = R.string.profile,
        icon = Icons.Default.Person
    ),
}

@Composable
fun BottomNavBar() {
    val navController = rememberNavController()
    val startDestination = Destination.Home
    var selectedDestination by rememberSaveable { mutableIntStateOf(value = startDestination.ordinal) }

    Scaffold(
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            selectedDestination = index
                            navController.navigate(screen.route) {
                                popUpTo(screen.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = stringResource(id = R.string.home)
                            )
                        },
                        label = {
                            Text(text = stringResource(id = screen.label))
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        HomeNavGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeBottomNavBarPreview() {
    PokeAppTheme {
        BottomNavBar()
    }
}
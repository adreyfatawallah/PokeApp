package com.example.pokeapp.features.auth.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeapp.R
import com.example.pokeapp.features.auth.presentation.screen.login.components.LoginLayout
import com.example.pokeapp.ui.theme.PokeAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 8.dp, Alignment.CenterVertically),
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            uiState.error?.let {
                LaunchedEffect(it) {
                    snackbarHostState.showSnackbar(message = it)
                    viewModel.clearErrorMessage()
                }
            }

            if (uiState.isValid) {
                navigateToHome()
            }

            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.W500,
                    fontSize = 45.sp
                )
                LoginLayout(
                    modifier = Modifier.padding(top = 8.dp),
                    username = uiState.username,
                    onUsernameChange = {
                        viewModel.onUsernameChange(username = it)
                    },
                    password = uiState.password,
                    onPasswordChange = {
                        viewModel.onPasswordChange(password = it)
                    },
                    login = {
                        viewModel.login()
                    },
                    navigateToRegister = navigateToRegister
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    PokeAppTheme {
        LoginScreen(
            navigateToRegister = { },
            navigateToHome = { }
        )
    }
}
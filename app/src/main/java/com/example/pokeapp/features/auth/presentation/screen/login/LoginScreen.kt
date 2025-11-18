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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokeapp.R
import com.example.pokeapp.features.auth.presentation.screen.login.components.LoginForm

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.isValid) {
        if (uiState.isValid) {
            navigateToHome()
        }
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let { error ->
            val message = if (error.string != null) {
                context.getString(error.string)
            } else {
                error.message ?: context.getString(R.string.unknown_error)
            }

            snackbarHostState.showSnackbar(message)
            viewModel.clearErrorMessage()
        }
    }

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
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.W500,
                    fontSize = 45.sp
                )
                LoginForm(
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
                    navigateToRegister = {
                        viewModel.clearForm()
                        navigateToRegister()
                    }
                )
            }
        }
    }
}
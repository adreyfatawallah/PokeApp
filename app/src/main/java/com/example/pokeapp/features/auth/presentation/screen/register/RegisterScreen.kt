package com.example.pokeapp.features.auth.presentation.screen.register

import android.util.Log
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
import com.example.pokeapp.R
import com.example.pokeapp.features.auth.presentation.screen.register.components.RegisterLayout
import com.example.pokeapp.ui.theme.PokeAppTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 16.dp, Alignment.CenterVertically),
            modifier = modifier.fillMaxSize()
        ) {
            uiState.error?.let {
                LaunchedEffect(it) {
                    snackbarHostState.showSnackbar(it)
                    viewModel.clearErrorMessage()
                }
            }

            if (uiState.isSucess) {
                navigateBack()
            }

            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = stringResource(id = R.string.register),
                    fontWeight = FontWeight.W500,
                    fontSize = 25.sp
                )
                RegisterLayout(
                    modifier = Modifier.padding(top = 8.dp),
                    username = uiState.username,
                    password = uiState.password,
                    onUsernameChange = {
                        viewModel.onUsernameChange(username = it)
                    },
                    onPasswordChange = {
                        viewModel.onPasswordChange(password = it)
                    },
                    register = {
                        viewModel.register()
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    PokeAppTheme {
        RegisterScreen(
            navigateBack = { }
        )
    }
}
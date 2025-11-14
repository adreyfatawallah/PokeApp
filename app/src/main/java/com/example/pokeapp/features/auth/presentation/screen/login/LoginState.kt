package com.example.pokeapp.features.auth.presentation.screen.login

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val isValid: Boolean = false,
)

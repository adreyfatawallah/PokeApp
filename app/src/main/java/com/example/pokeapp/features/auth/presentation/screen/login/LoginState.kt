package com.example.pokeapp.features.auth.presentation.screen.login

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: Error? = null,
    val isValid: Boolean = false,
) {
    data class Error(val string: Int? = null, val message: String? = null)
}
package com.example.pokeapp.features.auth.presentation.screen.register

data class RegisterState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: Error? = null,
    val isSuccess: Boolean = false,
) {
    data class Error(val string: Int? = null, val message: String? = null)
}

package com.example.pokeapp.features.auth.presentation.screen.register

data class RegisterState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val isSucess: Boolean = false,
)

package com.example.pokeapp.features.auth.domain.entities

data class User(
    val id: Int = 0,
    val username: String,
    val password: String,
)
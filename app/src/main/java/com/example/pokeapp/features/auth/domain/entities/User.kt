package com.example.pokeapp.features.auth.domain.entities

data class User(
    val id: Int,
    val username: String,
    val password: String,
)
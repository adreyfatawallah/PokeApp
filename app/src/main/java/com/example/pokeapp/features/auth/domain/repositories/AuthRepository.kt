package com.example.pokeapp.features.auth.domain.repositories

import com.example.pokeapp.config.database.user.UserEntity
import com.example.pokeapp.features.auth.domain.entities.User

interface AuthRepository {
    suspend fun login(username: String, password: String): User?
    suspend fun register(user: UserEntity)
}
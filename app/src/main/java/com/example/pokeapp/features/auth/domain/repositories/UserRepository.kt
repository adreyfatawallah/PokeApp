package com.example.pokeapp.features.auth.domain.repositories

import com.example.pokeapp.features.auth.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(user: User): User?
    suspend fun saveLoginInfo(isLogggedIn: Boolean, username: String)
    suspend fun register(user: User)
}
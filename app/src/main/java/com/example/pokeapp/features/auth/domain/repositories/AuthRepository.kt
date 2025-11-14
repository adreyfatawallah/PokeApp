package com.example.pokeapp.features.auth.domain.repositories

import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.usecases.param.AuthParam

interface AuthRepository {
    suspend fun login(param: AuthParam): User?
    suspend fun register(param: AuthParam)
}
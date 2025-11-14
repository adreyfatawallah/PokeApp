package com.example.pokeapp.features.auth.data.repositories

import com.example.pokeapp.features.auth.data.datasources.AuthLocalDataSource
import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.repositories.AuthRepository
import com.example.pokeapp.features.auth.domain.usecases.param.AuthParam
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor (
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {

    override suspend fun login(param: AuthParam) : User? {
        return authLocalDataSource.login(param)
    }

    override suspend fun register(param: AuthParam) {
        authLocalDataSource.register(param)
    }
}
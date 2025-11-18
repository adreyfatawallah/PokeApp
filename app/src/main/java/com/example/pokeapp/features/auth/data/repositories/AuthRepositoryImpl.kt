package com.example.pokeapp.features.auth.data.repositories

import com.example.pokeapp.config.database.user.UserEntity
import com.example.pokeapp.features.auth.data.datasources.AuthLocalDataSource
import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor (
    private val localDataSource: AuthLocalDataSource
) : AuthRepository {

    override suspend fun login(username: String, password: String) : User? {
        return localDataSource.login(username, password)
    }

    override suspend fun register(user: UserEntity) {
        localDataSource.register(user)
    }
}
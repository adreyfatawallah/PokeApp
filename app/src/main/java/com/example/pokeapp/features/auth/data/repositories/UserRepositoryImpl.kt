package com.example.pokeapp.features.auth.data.repositories

import com.example.pokeapp.features.auth.data.datasources.AuthLocalDataSource
import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor (
    private val authLocalDataSource: AuthLocalDataSource
) : UserRepository {

    override suspend fun login(user: User): User? {
        return authLocalDataSource.login(user)
    }

    override suspend fun saveLoginInfo(isLogggedIn: Boolean, username: String) {
        authLocalDataSource.saveLoginInfo(isLogggedIn, username)
    }

    override suspend fun register(user: User) {
        authLocalDataSource.register(user)
    }
}
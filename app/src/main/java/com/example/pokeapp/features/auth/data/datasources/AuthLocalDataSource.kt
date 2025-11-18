package com.example.pokeapp.features.auth.data.datasources

import com.example.pokeapp.config.database.user.UserDao
import com.example.pokeapp.config.database.user.UserEntity
import com.example.pokeapp.config.database.user.toUser
import com.example.pokeapp.config.sharedpref.UserPreferences
import com.example.pokeapp.features.auth.domain.entities.User
import javax.inject.Inject

interface AuthLocalDataSource {
    suspend fun login(username: String, password: String): User?

    suspend fun register(user: UserEntity)
}

class AuthLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val userPreferences: UserPreferences
) : AuthLocalDataSource {

    override suspend fun login(username: String, password: String): User? {
        val user = userDao.getUserByUsernamePassword(username, password)
        user?.let {
            userPreferences.saveLoginInfo(true, it.username)
        }
        return user?.toUser()
    }

    override suspend fun register(user: UserEntity) {
        userDao.insertUser(user)
    }
}
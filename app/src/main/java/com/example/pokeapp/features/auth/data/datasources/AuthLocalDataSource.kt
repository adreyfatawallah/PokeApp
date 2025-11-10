package com.example.pokeapp.features.auth.data.datasources

import com.example.pokeapp.core.database.user.UserDao
import com.example.pokeapp.core.database.user.toDomain
import com.example.pokeapp.core.database.user.toEntity
import com.example.pokeapp.core.sharedpref.SharedPrefModule
import com.example.pokeapp.features.auth.domain.entities.User
import javax.inject.Inject
import javax.inject.Singleton

interface AuthLocalDataSource {
    suspend fun login(user: User): User?
    suspend fun saveLoginInfo(isloggedIn: Boolean, username: String)


    suspend fun register(user: User)
}

@Singleton
class AuthLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val sharedPref: SharedPrefModule
) : AuthLocalDataSource {

    override suspend fun login(user: User): User? {
        return userDao.getUserByUsernamePassword(user.username, user.password)?.toDomain()
    }

    override suspend fun saveLoginInfo(isloggedIn: Boolean, username: String) {
        sharedPref.saveLoginInfo(isloggedIn, username)
    }

    override suspend fun register(user: User) {
        userDao.insertUser(user.toEntity())
    }
}
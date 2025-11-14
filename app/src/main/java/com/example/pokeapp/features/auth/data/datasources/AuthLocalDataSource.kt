package com.example.pokeapp.features.auth.data.datasources

import com.example.pokeapp.core.database.user.UserDao
import com.example.pokeapp.core.database.user.toUser
import com.example.pokeapp.core.sharedpref.SharedPrefModule
import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.usecases.param.AuthParam
import com.example.pokeapp.features.auth.domain.usecases.param.toUserEntity
import javax.inject.Inject
import javax.inject.Singleton

interface AuthLocalDataSource {
    suspend fun login(param: AuthParam): User?

    suspend fun register(param: AuthParam)
}

@Singleton
class AuthLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val sharedPref: SharedPrefModule
) : AuthLocalDataSource {

    override suspend fun login(param: AuthParam): User? {
        val user = userDao.getUserByUsernamePassword(param.username, param.password)
        user?.let {
            sharedPref.saveLoginInfo(true, it.username)
        }
        return user?.toUser()
    }

    override suspend fun register(param: AuthParam) {
        userDao.insertUser(param.toUserEntity())
    }
}
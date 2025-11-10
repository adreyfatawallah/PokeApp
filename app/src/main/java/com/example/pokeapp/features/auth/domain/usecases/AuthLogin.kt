package com.example.pokeapp.features.auth.domain.usecases

import com.example.pokeapp.base.usecase.UseCase
import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLogin @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<User?, LoginParam> {

    override suspend fun invoke(param: LoginParam): User? {
        return userRepository.login(param.toDomain())
    }

    suspend fun saveLoginInfo(username: String) {
        userRepository.saveLoginInfo(true, username)
    }
}

data class LoginParam(
    val username: String,
    val password: String
)

fun LoginParam.toDomain() : User {
    return User(username = username, password = password)
}
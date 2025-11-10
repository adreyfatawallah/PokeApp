package com.example.pokeapp.features.auth.domain.usecases

import com.example.pokeapp.base.usecase.UseCase
import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRegister @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, RegisterParam> {

    override suspend fun invoke(param: RegisterParam) {
        userRepository.register(param.toDomain())
    }
}

data class RegisterParam(
    val username: String,
    val password: String
)

fun RegisterParam.toDomain() : User {
    return User(username = username, password = password)
}
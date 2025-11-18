package com.example.pokeapp.features.auth.domain.usecases

import com.example.pokeapp.base.usecase.SuspendUseCase
import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class PostLogin @Inject constructor(
    private val userRepository: AuthRepository
) : SuspendUseCase<User?, PostLogin.Param> {

    override suspend fun invoke(param: Param): User? {
        return userRepository.login(param.username, param.password)
    }

    data class Param(
        val username: String,
        val password: String
    )
}
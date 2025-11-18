package com.example.pokeapp.features.auth.domain.usecases

import com.example.pokeapp.base.usecase.SuspendUseCase
import com.example.pokeapp.config.database.user.UserEntity
import com.example.pokeapp.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class PostRegister @Inject constructor(
    private val userRepository: AuthRepository
) : SuspendUseCase<Unit, PostRegister.Param> {

    override suspend fun invoke(param: Param) {
        userRepository.register(param.toUserEntity())
    }

    data class Param(
        val username: String,
        val password: String
    )

    fun Param.toUserEntity(): UserEntity {
        return UserEntity(username = username, password = password)
    }
}
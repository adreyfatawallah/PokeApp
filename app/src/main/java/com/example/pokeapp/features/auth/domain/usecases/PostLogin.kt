package com.example.pokeapp.features.auth.domain.usecases

import com.example.pokeapp.base.usecase.SuspendUseCase
import com.example.pokeapp.features.auth.domain.entities.User
import com.example.pokeapp.features.auth.domain.repositories.AuthRepository
import com.example.pokeapp.features.auth.domain.usecases.param.AuthParam
import javax.inject.Inject

class PostLogin @Inject constructor(
    private val userRepository: AuthRepository
) : SuspendUseCase<User?, AuthParam> {

    override suspend fun invoke(param: AuthParam): User? {
        return userRepository.login(param)
    }
}
package com.example.pokeapp.features.auth.domain.usecases

import com.example.pokeapp.base.usecase.SuspendUseCase
import com.example.pokeapp.features.auth.domain.repositories.AuthRepository
import com.example.pokeapp.features.auth.domain.usecases.param.AuthParam
import javax.inject.Inject

class PostRegister @Inject constructor(
    private val userRepository: AuthRepository
) : SuspendUseCase<Unit, AuthParam> {

    override suspend fun invoke(param: AuthParam) {
        userRepository.register(param)
    }
}
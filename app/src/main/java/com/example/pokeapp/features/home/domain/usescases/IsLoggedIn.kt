package com.example.pokeapp.features.home.domain.usescases

import com.example.pokeapp.base.usecase.UseCase
import com.example.pokeapp.features.home.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsLoggedIn @Inject constructor(
    private val repository: HomeRepository
) : UseCase<Flow<Boolean>, Any?> {

    override fun invoke(param: Any?) = repository.isLoggedIn()
}
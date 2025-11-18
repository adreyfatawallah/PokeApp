package com.example.pokeapp.features.pokemon.domain.usecases

import com.example.pokeapp.base.api.ApiError
import com.example.pokeapp.base.api.Response
import com.example.pokeapp.base.usecase.SuspendUseCase
import com.example.pokeapp.features.pokemon.domain.entities.list.ListPokemon
import com.example.pokeapp.features.pokemon.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemon @Inject constructor(
    private val repository: PokemonRepository
) : SuspendUseCase<Response<ListPokemon, ApiError>, GetPokemon.Param> {

    override suspend fun invoke(param: Param) : Response<ListPokemon, ApiError> {
        return repository.getPokemon(param.url)
    }

    data class Param(val url: String)
}
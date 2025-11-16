package com.example.pokeapp.features.pokemon.domain.usecases

import com.example.pokeapp.base.api.ApiError
import com.example.pokeapp.base.api.Response
import com.example.pokeapp.base.usecase.NoParam
import com.example.pokeapp.base.usecase.SuspendUseCase
import com.example.pokeapp.features.pokemon.domain.entities.list.ListPokemon
import com.example.pokeapp.features.pokemon.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonList @Inject constructor(
    private val repository: PokemonRepository
) : SuspendUseCase<Response<ListPokemon, ApiError>, NoParam> {

    override suspend fun invoke(param: NoParam) : Response<ListPokemon, ApiError> {
        return repository.getListPokemon()
    }
}
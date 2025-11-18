package com.example.pokeapp.features.pokemon.data.repositories

import com.example.pokeapp.base.api.ApiError
import com.example.pokeapp.base.api.Response
import com.example.pokeapp.base.api.map
import com.example.pokeapp.base.api.onFailure
import com.example.pokeapp.base.api.onSuccess
import com.example.pokeapp.base.api.safeCall
import com.example.pokeapp.features.pokemon.data.datasource.PokemonLocalDataSource
import com.example.pokeapp.features.pokemon.data.datasource.PokemonRemoteDataSource
import com.example.pokeapp.features.pokemon.data.models.pokemon.PokemonResponse
import com.example.pokeapp.features.pokemon.data.models.pokemon.toDomain
import com.example.pokeapp.features.pokemon.domain.entities.list.ListPokemon
import com.example.pokeapp.features.pokemon.domain.repositories.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override suspend fun getPokemon(url: String) : Response<ListPokemon, ApiError> {
        return safeCall<PokemonResponse> {
            remoteDataSource.getPokemon(url)
        }.map { response ->
            response.toDomain()
        }.onSuccess { response ->
            Response.Success(response)
        }.onFailure { error ->
            Response.Failure(error)
        }
    }
}
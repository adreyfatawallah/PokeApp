package com.example.pokeapp.features.pokemon.data.repositories

import com.example.pokeapp.base.api.ApiError
import com.example.pokeapp.base.api.Response
import com.example.pokeapp.base.api.onFailure
import com.example.pokeapp.base.api.onSuccess
import com.example.pokeapp.base.api.safeCall
import com.example.pokeapp.features.pokemon.data.datasource.PokemonLocalDataSource
import com.example.pokeapp.features.pokemon.data.datasource.PokemonRemoteDataSource
import com.example.pokeapp.features.pokemon.data.models.pokemon.PokemonListResponse
import com.example.pokeapp.features.pokemon.domain.repositories.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override suspend fun getListPokemon() : Response<PokemonListResponse, ApiError> {
        return safeCall<PokemonListResponse> {
            remoteDataSource.getListPokemon()
        }.onSuccess {
            Response.Success(it)
        }.onFailure {
            Response.Failure(it)
        }
    }
}
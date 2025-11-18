package com.example.pokeapp.features.pokemon.data.datasource

import com.example.pokeapp.config.api.ApiService
import com.example.pokeapp.features.pokemon.data.models.pokemon.PokemonResponse
import javax.inject.Inject

interface PokemonRemoteDataSource {

    suspend fun getPokemon(url: String) : PokemonResponse
}

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : PokemonRemoteDataSource {

    override suspend fun getPokemon(url: String) : PokemonResponse {
        return apiService.getPokemon(url)
    }
}
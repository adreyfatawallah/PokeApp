package com.example.pokeapp.features.pokemon.data.datasource

import com.example.pokeapp.core.api.ApiService
import com.example.pokeapp.features.pokemon.data.models.pokemon.PokemonListResponse
import retrofit2.Response
import javax.inject.Inject

interface PokemonRemoteDataSource {

    suspend fun getListPokemon() : PokemonListResponse
}

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : PokemonRemoteDataSource {

    override suspend fun getListPokemon() : PokemonListResponse {
        return apiService.getListPokemon()
    }
}
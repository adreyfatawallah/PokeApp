package com.example.pokeapp.core.api

import com.example.pokeapp.features.pokemon.data.models.pokemon.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("limit") limit: Int = 15
    ): PokemonListResponse
}
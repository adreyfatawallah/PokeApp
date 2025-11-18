package com.example.pokeapp.config.api

import com.example.pokeapp.features.pokemon.data.models.pokemon.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getPokemon(
        @Url url: String
    ): PokemonResponse
}
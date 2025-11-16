package com.example.pokeapp.features.pokemon.domain.repositories

import com.example.pokeapp.base.api.ApiError
import com.example.pokeapp.base.api.Response
import com.example.pokeapp.features.pokemon.domain.entities.list.ListPokemon

interface PokemonRepository {
    suspend fun getListPokemon() : Response<ListPokemon, ApiError>
}
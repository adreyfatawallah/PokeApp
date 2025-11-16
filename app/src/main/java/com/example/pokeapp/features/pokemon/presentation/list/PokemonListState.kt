package com.example.pokeapp.features.pokemon.presentation.list

import com.example.pokeapp.base.api.ApiError
import com.example.pokeapp.features.pokemon.data.models.pokemon.PokemonListResponse

sealed interface PokemonListState {
    object Loading : PokemonListState
    data class Success(val pokemon: PokemonListResponse) : PokemonListState
    data class Error(val error: ApiError) : PokemonListState
}
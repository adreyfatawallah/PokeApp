package com.example.pokeapp.features.pokemon.presentation.list

import com.example.pokeapp.base.api.ApiError
import com.example.pokeapp.features.pokemon.domain.entities.list.Pokemon

sealed interface PokemonListState {
    object Loading : PokemonListState
    data class Success(val list: List<Pokemon>) : PokemonListState
    data class Error(val error: ApiError) : PokemonListState
}
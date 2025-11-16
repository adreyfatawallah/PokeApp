package com.example.pokeapp.features.pokemon.presentation.list

import com.example.pokeapp.base.api.ApiError
import com.example.pokeapp.features.pokemon.domain.entities.list.ListPokemon

sealed interface PokemonListState {
    object Loading : PokemonListState
    data class Success(val list: ListPokemon) : PokemonListState
    data class Error(val error: ApiError) : PokemonListState
}
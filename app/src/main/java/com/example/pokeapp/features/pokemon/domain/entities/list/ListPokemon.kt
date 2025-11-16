package com.example.pokeapp.features.pokemon.domain.entities.list

import com.example.pokeapp.features.pokemon.data.models.pokemon.PokemonResult

data class ListPokemon(
    val count: Int,
    val next: String?,
    val pokemon: List<Pokemon>
)
package com.example.pokeapp.features.pokemon.domain.entities.list

data class ListPokemon(
    val count: Int,
    val next: String?,
    val pokemon: List<Pokemon>
)
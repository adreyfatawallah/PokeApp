package com.example.pokeapp.features.pokemon.data.models.pokemon

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResult(
    val name: String,
    val url: String
)
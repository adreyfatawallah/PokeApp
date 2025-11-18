package com.example.pokeapp.features.pokemon.data.models.pokemon

import com.example.pokeapp.features.pokemon.domain.entities.list.Pokemon
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonItem(
    val name: String,
    val url: String
)

fun PokemonItem.toDomain() : Pokemon {
    return Pokemon(name = name, url = url)
}
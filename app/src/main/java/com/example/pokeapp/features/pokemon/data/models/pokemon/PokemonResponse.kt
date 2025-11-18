package com.example.pokeapp.features.pokemon.data.models.pokemon

import com.example.pokeapp.features.pokemon.domain.entities.list.ListPokemon
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonItem>
)

fun PokemonResponse.toDomain() : ListPokemon {
    return ListPokemon(count = count, next = next, pokemon = results.map { it.toDomain() })
}
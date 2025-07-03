package com.maxicruz.pokeapp.domain.model

data class PokemonDetail(
    val id : Int? = null,
    val name: String,
    val types: List<PokemonType> = emptyList(),
    val abilities: List<PokemonAbility> = emptyList(),
    val height: Int? = null,
    val weight: Int? = null,
) {
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/$id.png"
}

data class PokemonType(val slot: Int, val type: Type)
data class Type(val name: String)
data class PokemonAbility(val slot: Int, val is_hidden: Boolean, val ability: Ability)
data class Ability(val name: String)
package com.maxicruz.pokeapp.data.model

data class Pokemon(
    val name: String,
    val url: String
) {
    // Extraer el ID del Pokémon desde la URL
    val id: Int
        get() = url.trimEnd('/').split("/").last().toInt()

    // Generar la URL de la imagen del Pokémon
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}
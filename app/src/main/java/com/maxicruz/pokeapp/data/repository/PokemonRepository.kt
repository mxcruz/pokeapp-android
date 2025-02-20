package com.maxicruz.pokeapp.data.repository

import com.maxicruz.pokeapp.data.api.PokeApiService
import com.maxicruz.pokeapp.data.model.Pokemon
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val service : PokeApiService
) {
    suspend fun getPokemonList() : List<Pokemon> {
        return service.getPokemonList()
    }
}
package com.maxicruz.pokeapp.data.repository

import com.maxicruz.pokeapp.data.api.PokeApiService
import com.maxicruz.pokeapp.domain.model.Pokemon
import com.maxicruz.pokeapp.domain.model.PokemonDetail
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val service : PokeApiService
) {
    suspend fun getPokemonList() : List<Pokemon> {
        return service.getPokemonList()
    }

    suspend fun getPokemon(id: Int) : PokemonDetail {
        return service.getPokemon(id)
    }
}
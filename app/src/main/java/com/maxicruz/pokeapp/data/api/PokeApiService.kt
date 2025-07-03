package com.maxicruz.pokeapp.data.api

import com.maxicruz.pokeapp.domain.model.Pokemon
import com.maxicruz.pokeapp.domain.model.PokemonDetail
import javax.inject.Inject

class PokeApiService @Inject constructor(
    private val api : IPokeApiService
) {
    suspend fun getPokemonList(): List<Pokemon> {
        val response = api.getPokemonList()
        return response.body()!!.results
    }

    suspend fun getPokemon(id: Int): PokemonDetail {
        val response = api.getPokemon(id)
        return response.body()!!
    }
}

package com.maxicruz.pokeapp.data.api

import com.maxicruz.pokeapp.data.model.Pokemon
import javax.inject.Inject

class PokeApiService @Inject constructor(
    private val api : IPokeApiService
) {
    suspend fun getPokemonList(): List<Pokemon> {
        val response = api.getPokemonList()
        return response.body()!!.results
    }
}

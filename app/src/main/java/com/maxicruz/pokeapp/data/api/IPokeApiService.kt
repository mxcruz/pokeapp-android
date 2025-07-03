package com.maxicruz.pokeapp.data.api

import com.maxicruz.pokeapp.domain.model.Pokemon
import com.maxicruz.pokeapp.domain.model.PokemonDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

import com.maxicruz.pokeapp.domain.model.PokemonList
import retrofit2.http.Path

interface IPokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int = 251): Response<PokemonList>
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): Response<PokemonDetail>
}
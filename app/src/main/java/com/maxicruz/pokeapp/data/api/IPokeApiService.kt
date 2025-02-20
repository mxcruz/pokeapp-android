package com.maxicruz.pokeapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

import com.maxicruz.pokeapp.data.model.PokemonResponse

interface IPokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int = 151): Response<PokemonResponse>
}
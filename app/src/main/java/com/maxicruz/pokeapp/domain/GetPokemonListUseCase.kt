package com.maxicruz.pokeapp.domain

import com.maxicruz.pokeapp.data.model.Pokemon
import com.maxicruz.pokeapp.data.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository : PokemonRepository
) {
    suspend operator fun invoke() : List<Pokemon> = repository.getPokemonList()
}
package com.maxicruz.pokeapp.domain.usecase

import com.maxicruz.pokeapp.domain.model.Pokemon
import com.maxicruz.pokeapp.data.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository : PokemonRepository
) {
    suspend operator fun invoke() : List<Pokemon> = repository.getPokemonList()
}
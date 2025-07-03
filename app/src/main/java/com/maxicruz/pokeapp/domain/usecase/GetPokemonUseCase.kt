package com.maxicruz.pokeapp.domain.usecase

import com.maxicruz.pokeapp.data.repository.PokemonRepository
import com.maxicruz.pokeapp.domain.model.PokemonDetail
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository : PokemonRepository
) {
    suspend operator fun invoke(id: Int) : PokemonDetail = repository.getPokemon(id)
}
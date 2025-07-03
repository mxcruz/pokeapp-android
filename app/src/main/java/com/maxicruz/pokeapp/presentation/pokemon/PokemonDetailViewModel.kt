package com.maxicruz.pokeapp.presentation.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxicruz.pokeapp.domain.model.PokemonDetail
import com.maxicruz.pokeapp.domain.usecase.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    var getPokemonDetailState by mutableStateOf<GetPokemonDetailState>(GetPokemonDetailState.Idle)

    fun getPokemon(id: Int) {
        viewModelScope.launch {
            getPokemonDetailState = GetPokemonDetailState.Loading
            try {
                val pokemon = getPokemonDetailUseCase(id)
                getPokemonDetailState = GetPokemonDetailState.Success(pokemon = pokemon)
            } catch (e: Exception) {
                getPokemonDetailState = GetPokemonDetailState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun resetState() {
        getPokemonDetailState = GetPokemonDetailState.Idle
    }
}

sealed class GetPokemonDetailState {
    object Idle : GetPokemonDetailState()
    object Loading : GetPokemonDetailState()
    data class Success(val pokemon: PokemonDetail) : GetPokemonDetailState()
    data class Error(val message: String) : GetPokemonDetailState()
}


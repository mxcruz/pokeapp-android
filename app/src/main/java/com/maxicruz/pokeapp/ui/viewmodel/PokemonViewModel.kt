package com.maxicruz.pokeapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxicruz.pokeapp.domain.model.Pokemon
import com.maxicruz.pokeapp.domain.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    var getPokemonState by mutableStateOf<GetPokemonState>(GetPokemonState.Idle)

    fun fetchPokemons() {
        viewModelScope.launch {
            getPokemonState = GetPokemonState.Loading
            try {
                val pokemons = getPokemonListUseCase()
                getPokemonState = GetPokemonState.Success(pokemons = pokemons)
            } catch (e: Exception) {
                getPokemonState = GetPokemonState.Error(e.message ?: "Error desconocido")
            }
        }
}
}

sealed class GetPokemonState {
    object Idle : GetPokemonState()
    object Loading : GetPokemonState()
    data class Success(val pokemons: List<Pokemon>) : GetPokemonState()
    data class Error(val message: String) : GetPokemonState()
}


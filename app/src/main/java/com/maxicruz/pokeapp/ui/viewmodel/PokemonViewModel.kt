package com.maxicruz.pokeapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxicruz.pokeapp.data.model.Pokemon
import com.maxicruz.pokeapp.domain.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {
    val pokemonList = MutableLiveData<List<Pokemon>>()
    val isLoading = MutableLiveData<Boolean>()

    fun fetchPokemonList() {
        viewModelScope.launch {
            isLoading.value = true
            val result = getPokemonListUseCase()
            pokemonList.postValue(result)
            isLoading.value = false
        }
    }
}


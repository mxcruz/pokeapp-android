package com.maxicruz.pokeapp.presentation.pokemon

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel

import com.maxicruz.pokeapp.presentation.components.PokemonList

@Composable
fun PokemonScreen(
    viewModel: PokemonViewModel = hiltViewModel(),
) {
    val getPokemonState = viewModel.getPokemonState
    var errorMessage by remember { mutableStateOf<String?>(null) }

    when (getPokemonState) {
        is GetPokemonState.Loading -> CircularProgressIndicator()

        is GetPokemonState.Error -> {
            errorMessage = getPokemonState.message
        }

        is GetPokemonState.Idle -> {
            LaunchedEffect(Unit) {
                viewModel.fetchPokemons()
            }
        }

        is GetPokemonState.Success -> {
            PokemonList(getPokemonState.pokemons)
        }
    }
}
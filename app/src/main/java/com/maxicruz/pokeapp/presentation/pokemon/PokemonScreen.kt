package com.maxicruz.pokeapp.presentation.pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.maxicruz.pokeapp.domain.model.Pokemon
import com.maxicruz.pokeapp.presentation.components.PokemonList
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold

@Composable
fun PokemonScreen(
    viewModel: PokemonViewModel = hiltViewModel(),
) {
    val getPokemonState = viewModel.getPokemonState
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var selectedPokemon by remember { mutableStateOf<Pokemon?>(null) }

    when (getPokemonState) {
        is GetPokemonState.Loading ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        is GetPokemonState.Error -> {
            errorMessage = getPokemonState.message
        }

        is GetPokemonState.Idle -> {
            LaunchedEffect(Unit) {
                viewModel.fetchPokemons()
            }
        }

        is GetPokemonState.Success -> {
            Scaffold (
                topBar = {
                    Text(
                        text = "Lista de Pokémon",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(20.dp, top = 60.dp, bottom = 8.dp)
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(paddingValues)
                ) {
                    AnimatedContent(
                        targetState = selectedPokemon,
                        transitionSpec = { fadeIn() togetherWith fadeOut() }
                    ) { pokemon ->
                        if (pokemon == null) {
                            PokemonList(
                                pokemonList = getPokemonState.pokemons,
                                onItemClick = { selectedPokemon = it }
                            )
                        } else {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(pokemon.imageUrl),
                                    contentDescription = "Imagen grande del Pokémon",
                                    modifier = Modifier.size(200.dp)
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                Text(
                                    text = pokemon.name,
                                    style = MaterialTheme.typography.headlineMedium
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "ID: ${pokemon.id}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                // Agrega aquí más detalles si tu modelo tiene más campos
                                Spacer(modifier = Modifier.height(32.dp))
                                Button(onClick = { selectedPokemon = null }) {
                                    Text("Volver")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

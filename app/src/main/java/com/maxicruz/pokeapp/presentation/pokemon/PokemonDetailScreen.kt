package com.maxicruz.pokeapp.presentation.pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.painterResource
import com.maxicruz.pokeapp.R
import com.maxicruz.pokeapp.presentation.components.LoadingPanel
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.navigation.NavController
import com.maxicruz.pokeapp.presentation.navigation.Screen
import androidx.compose.ui.text.font.FontWeight

@Composable
fun PokemonDetailScreen(
    id: Int? = null,
    navController: NavController,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {
    val getPokemonDetailState = viewModel.getPokemonDetailState
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.resetState()
    }

    when (getPokemonDetailState) {
        is GetPokemonDetailState.Loading -> LoadingPanel()

        is GetPokemonDetailState.Error -> {
            errorMessage = getPokemonDetailState.message
        }

        is GetPokemonDetailState.Idle -> {
            viewModel.getPokemon(id ?: 1)
        }

        is GetPokemonDetailState.Success -> {
            val pokemon = getPokemonDetailState.pokemon
            Scaffold (
                topBar = {
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier.size(80.dp)
                        )
                    }
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    // Cruz de cerrar en la esquina superior derecha
                    IconButton(
                        onClick = {
                            viewModel.resetState()
                            navController.navigate(Screen.Pokemon.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(horizontal = 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar"
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(getPokemonDetailState.pokemon.imageUrl),
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
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        // Types con emoji
                        if (pokemon.types.isNotEmpty()) {
                            Row (
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    text = "Types: ",
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(end = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                pokemon.types.forEach { it ->
                                    Text(
                                        text = "${typeToEmoji(it.type.name)} ${it.type.name}",
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        // Abilities
                        if (pokemon.abilities.isNotEmpty()) {
                            Row (
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    text = "Abilities: ",
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(end = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                pokemon.abilities.forEach { it ->
                                    Text(
                                        text = it.ability.name,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        // Height & Weight
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            pokemon.height?.let {
                                Text(
                                    text = "Height: ${it / 10.0} m",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(end = 16.dp)
                                )
                            }
                            pokemon.weight?.let {
                                Text(
                                    text = "Weight: ${it / 10.0} kg",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))/*
                        // Evolutions
                        if (pokemon.evolutions.isNotEmpty()) {
                            Text(
                                text = "Evolutions: ${pokemon.evolutions.joinToString(" → ")}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        // Description
                        pokemon.description?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(32.dp))*/
                    }
                }
            }
        }
    }
}

// Mapeo simple de tipo a emoji
private fun typeToEmoji(type: String): String = when (type.lowercase()) {
    "water" -> "💧"
    "fire" -> "🔥"
    "grass" -> "🌿"
    "electric" -> "⚡"
    "bug" -> "🐞"
    "normal" -> "🔘"
    "poison" -> "☠️"
    "ground" -> "🌍"
    "fairy" -> "🧚"
    "fighting" -> "🥊"
    "psychic" -> "🔮"
    "rock" -> "🪨"
    "ghost" -> "👻"
    "ice" -> "❄️"
    "dragon" -> "🐉"
    "dark" -> "🌑"
    "steel" -> "⚙️"
    "flying" -> "🕊️"
    else -> "❓"
}

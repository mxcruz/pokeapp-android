package com.maxicruz.pokeapp.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.maxicruz.pokeapp.domain.model.Pokemon
import com.maxicruz.pokeapp.ui.viewmodel.GetPokemonState
import com.maxicruz.pokeapp.ui.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

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
                            PokemonListScreen(getPokemonState.pokemons)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonListScreen(pokemonList: List<Pokemon>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(pokemonList) { pokemon ->
            PokemonItem(pokemon)
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(pokemon.imageUrl),
            contentDescription = "Imagen del Pokémon",
            modifier = Modifier
                .size(64.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(text = "ID: ${pokemon.id}", style = MaterialTheme.typography.titleMedium)
            Text(text = pokemon.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}


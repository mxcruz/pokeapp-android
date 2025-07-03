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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxicruz.pokeapp.domain.model.Pokemon
import com.maxicruz.pokeapp.presentation.components.PokemonList
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.ui.res.painterResource
import com.maxicruz.pokeapp.R
import com.maxicruz.pokeapp.presentation.components.LoadingPanel
import androidx.navigation.NavController
import com.maxicruz.pokeapp.presentation.navigation.Screen

@Composable
fun PokemonScreen(
    navController: NavController,
    viewModel: PokemonViewModel = hiltViewModel(),
) {
    val getPokemonState = viewModel.getPokemonState
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var selectedPokemon by remember { mutableStateOf<Pokemon?>(null) }
    var filterText by remember { mutableStateOf("") }

    // Filtra la lista de pokémon por nombre
    val filteredPokemons = remember(getPokemonState, filterText) {
        if (getPokemonState is GetPokemonState.Success) {
            getPokemonState.pokemons.filter { it.name.contains(filterText, ignoreCase = true) }
        } else {
            emptyList()
        }
    }

    when (getPokemonState) {
        is GetPokemonState.Loading -> LoadingPanel()

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
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
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
                    modifier = Modifier.fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        TextField(
                            value = filterText,
                            onValueChange = { filterText = it },
                            label = { Text("Buscar por nombre") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        PokemonList(
                            pokemonList = filteredPokemons,
                            onItemClick = {
                                navController.navigate("pokemon/${it.id}") {
                                    popUpTo(Screen.Pokemon.route) { inclusive = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

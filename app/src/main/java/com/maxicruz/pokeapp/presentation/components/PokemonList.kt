package com.maxicruz.pokeapp.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxicruz.pokeapp.domain.model.Pokemon

@Composable
fun PokemonList(pokemonList: List<Pokemon>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(pokemonList) { pokemon ->
            PokemonItem(pokemon)
        }
    }
}
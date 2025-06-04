package com.maxicruz.pokeapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.maxicruz.pokeapp.domain.model.Pokemon

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
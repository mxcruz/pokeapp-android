package com.maxicruz.pokeapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.maxicruz.pokeapp.R
import com.maxicruz.pokeapp.data.model.Pokemon

class PokemonAdapter(private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPokemon: ImageView = itemView.findViewById(R.id.ivPokemon)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        // Cargar la imagen con Glide
        Glide.with(holder.itemView.context)
            .load(pokemon.imageUrl)
            .into(holder.ivPokemon)

        // Mostrar el ID y el nombre
        holder.tvId.text = "#${pokemon.id}"
        holder.tvNombre.text = pokemon.name.capitalize()
    }

    override fun getItemCount() = pokemonList.size
}

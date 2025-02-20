package com.maxicruz.pokeapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.maxicruz.pokeapp.R
import com.maxicruz.pokeapp.ui.viewmodel.PokemonViewModel
import com.maxicruz.pokeapp.ui.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: PokemonViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.pokemonList.observe(this) { pokemonList ->
            adapter = PokemonAdapter(pokemonList)
            recyclerView.adapter = adapter
        }

        viewModel.fetchPokemonList()
    }
}

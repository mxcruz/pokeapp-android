package com.maxicruz.pokeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maxicruz.pokeapp.presentation.pokemon.PokemonScreen

@Composable
fun AppNavHost(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Pokemon.route
    ) {
        composable(Screen.Pokemon.route) {
            PokemonScreen()
        }
    }
}
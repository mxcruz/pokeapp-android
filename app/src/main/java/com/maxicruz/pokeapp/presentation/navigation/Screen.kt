package com.maxicruz.pokeapp.presentation.navigation

sealed class Screen(val route: String, val title: String? = null) {
    object Splash : Screen("splash")
    object Pokemon : Screen("pokemon", "Pokémon")
}

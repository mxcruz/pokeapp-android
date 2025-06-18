package com.maxicruz.pokeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maxicruz.pokeapp.presentation.pokemon.PokemonScreen
import com.maxicruz.pokeapp.presentation.splash.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun AppNavHost(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            LaunchedEffect(key1 = Unit) {
                delay(1000)
                navController.navigate(Screen.Pokemon.route)
            }
            SplashScreen()
        }

        composable(Screen.Pokemon.route) {
            PokemonScreen()
        }
    }
}
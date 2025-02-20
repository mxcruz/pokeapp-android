package com.maxicruz.pokeapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.maxicruz.pokeapp.data.model.Pokemon
import com.maxicruz.pokeapp.domain.GetPokemonListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

@ExperimentalCoroutinesApi
class PokemonViewModelTest {

    @RelaxedMockK
    private lateinit var getPokemonListUseCase: GetPokemonListUseCase

    private lateinit var pokemonViewModel: PokemonViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        pokemonViewModel = PokemonViewModel(getPokemonListUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchPokemonList updates live data`() = runTest {
        // Arrange
        val mockData = listOf(Pokemon("Charmander"), Pokemon("Squirtle"))
        coEvery { getPokemonListUseCase() } returns mockData

        // Act
        pokemonViewModel.fetchPokemonList()

        // Assert
        assertEquals(mockData, pokemonViewModel.pokemonList.value)
    }
}

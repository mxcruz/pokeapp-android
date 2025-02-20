package com.maxicruz.pokeapp.data.repository

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

import com.maxicruz.pokeapp.data.api.PokeApiService
import com.maxicruz.pokeapp.data.model.Pokemon

import org.junit.Before
import org.junit.Test

import io.mockk.impl.annotations.RelaxedMockK

class PokemonRepositoryTest {

    @RelaxedMockK
    private lateinit var service: PokeApiService

    private lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        pokemonRepository = PokemonRepository(service)
    }

    @Test
    fun `getPokemonList returns data from api call`() = runTest {
        // Arrange
        val mockData = listOf(Pokemon("Charmander", ""), Pokemon("Squirtle", ""))
        coEvery { service.getPokemonList() } returns mockData

        // Act
        val result = pokemonRepository.getPokemonList()

        // Assert
        assertNotNull(result)
        assertEquals(mockData, result)
    }
}

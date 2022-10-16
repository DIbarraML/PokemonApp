package com.example.pokemonapp.data

import com.example.pokemonapp.data.model.PokemonResponse

interface PokemonRepository {

    suspend fun getPokemonList(page: Int): Output<PokemonResponse>
}
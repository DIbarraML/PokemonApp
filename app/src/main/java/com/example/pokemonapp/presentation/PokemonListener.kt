package com.example.pokemonapp.presentation

import com.example.pokemonapp.data.model.PokemonData

interface PokemonListener {

    fun pokemonSelected(pokemonData: PokemonData)
    fun getNextPokemonList()
}
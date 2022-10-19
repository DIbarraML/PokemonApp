package com.example.pokemonapp.presentation.ui

sealed class EventPokemonList {
    data class LostConnection(
        val message: Int
    ) : EventPokemonList()
}

package com.example.pokemonapp.presentation.events

sealed class EventPokemonList {
    data class LostConnection(
        val message: Int
    ) : EventPokemonList()
}

package com.example.pokemonapp.presentation

sealed class UIState {
    object LayoutView : UIState()
    object Loading : UIState()
    data class Error(val messageError: String) : UIState()
}
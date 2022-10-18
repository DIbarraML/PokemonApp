package com.example.pokemonapp.presentation

import android.graphics.Bitmap
import com.example.pokemonapp.data.model.PokemonData

interface PokemonInfoListener {

    fun getInfo(pokemonData: PokemonData, bitmap: Bitmap)
}
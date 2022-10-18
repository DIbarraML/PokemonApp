package com.example.pokemonapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.data.remote.PokemonRemoteDataSource
import com.example.pokemonapp.data.repository.PokemonRepositoryImpl
import com.example.pokemonapp.data.usecase.GetAbilityUseCase
import com.example.pokemonapp.data.usecase.GetMoveUseCase
import com.example.pokemonapp.data.usecase.GetPokemonDetailUseCase
import com.example.pokemonapp.data.usecase.GetPokemonListUseCase

class PokemonViewModelFactory(private val application: Application) : AbstractSavedStateViewModelFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return PokemonViewModel(
            GetPokemonListUseCase(PokemonRepositoryImpl(PokemonRemoteDataSource(context = application.applicationContext)))
        ) as T
    }
}
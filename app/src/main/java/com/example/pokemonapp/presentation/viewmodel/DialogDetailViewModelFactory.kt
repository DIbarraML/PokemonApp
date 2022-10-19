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

class DialogDetailViewModelFactory(private val application: Application) : AbstractSavedStateViewModelFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        val repository = PokemonRepositoryImpl(PokemonRemoteDataSource(context = application.baseContext))
        return DialogDetailViewModel(
            GetPokemonDetailUseCase(repository),
            GetAbilityUseCase(repository),
            GetMoveUseCase(repository)
        ) as T
    }
}
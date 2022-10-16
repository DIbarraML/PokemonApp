package com.example.pokemonapp.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pokemonapp.data.GetPokemonListUseCase
import com.example.pokemonapp.data.Output
import com.example.pokemonapp.data.PokemonRemoteDataSource
import com.example.pokemonapp.data.PokemonRepository
import com.example.pokemonapp.data.PokemonRepositoryImpl
import com.example.pokemonapp.data.model.PokemonResponse
import com.example.pokemonapp.data.remote.PokemonClient
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val useCase: GetPokemonListUseCase
): ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _pokemonList: MutableLiveData<PokemonResponse> = MutableLiveData()
    val pokemonList: LiveData<PokemonResponse> = _pokemonList

    private var pageIndex: Int = 0

    fun getPokemonList() {
        _isLoading.value = true
        viewModelScope.launch {
            when (val output = useCase.invoke(pageIndex)) {
                is Output.Success -> {
                    _pokemonList.value = output.value
                    println("lista pokemons -> "+output.value.toString())
                }
                is Output.Failure -> {
                    println("error aqui")
                }
            }
            _isLoading.value = false
        }
    }

    fun getNextPokemonList() {
        pageIndex++
        getPokemonList()
    }


}

class PokemonViewModelFactory(
): AbstractSavedStateViewModelFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return PokemonViewModel(
            GetPokemonListUseCase(PokemonRepositoryImpl(PokemonRemoteDataSource()))
        ) as T
    }
}
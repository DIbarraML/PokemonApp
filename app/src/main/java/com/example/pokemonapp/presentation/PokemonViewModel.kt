package com.example.pokemonapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.ConnectivityStateInterceptor
import com.example.pokemonapp.data.GetPokemonListUseCase
import com.example.pokemonapp.data.Output
import com.example.pokemonapp.data.PokemonRemoteDataSource
import com.example.pokemonapp.data.PokemonRepositoryImpl
import com.example.pokemonapp.data.model.PokemonResponse
import com.example.pokemonapp.data.service.exceptions.NoConnectivityException
import java.lang.Exception
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val useCase: GetPokemonListUseCase
) : BaseViewModel() {

    private val _pokemonList: MutableLiveData<PokemonResponse> = MutableLiveData()
    val pokemonList: LiveData<PokemonResponse> = _pokemonList

    private var pageIndex: Int = 0

    fun getPokemonList() {
        showLoadingView()
        viewModelScope.launch {
            when (val output = useCase.invoke(pageIndex)) {
                is Output.Success -> {
                    showLayoutView()
                    _pokemonList.value = output.value
                    Log.d("list pokemons", output.value.toString())
                }
                is Output.Failure -> {
                    showErrorView(getErrorMessage(output.exception))
                }
            }
        }
    }

    fun getNextPokemonList() {
        pageIndex++
        getPokemonList()
    }

    private fun getErrorMessage(exception: Exception) =
        when (exception) {
            is NoConnectivityException -> "Sin acceso a internet"
            else -> "Lo sentimos, tenemos inconvenientes en el sistema"
        }
}

class PokemonViewModelFactory(private val application: Application) : AbstractSavedStateViewModelFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return PokemonViewModel(
            GetPokemonListUseCase(PokemonRepositoryImpl(PokemonRemoteDataSource(context = application.applicationContext)))
        ) as T
    }
}
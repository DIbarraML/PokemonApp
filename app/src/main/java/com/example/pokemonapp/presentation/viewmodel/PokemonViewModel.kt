package com.example.pokemonapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.usecase.GetPokemonListUseCase
import com.example.pokemonapp.data.commons.Output
import com.example.pokemonapp.data.model.PokemonData
import com.example.pokemonapp.data.model.PokemonResponse
import com.example.pokemonapp.data.service.exceptions.NoConnectivityException
import com.example.pokemonapp.data.usecase.GetAbilityUseCase
import com.example.pokemonapp.data.usecase.GetMoveUseCase
import com.example.pokemonapp.data.usecase.GetPokemonDetailUseCase
import java.lang.Exception
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
) : BaseViewModel() {

    private val _pokemonList: MutableLiveData<List<PokemonData>> = MutableLiveData()
    val pokemonList: LiveData<List<PokemonData>> = _pokemonList
    private val list: MutableList<PokemonData> = mutableListOf()

    private var pageIndex: Int = 0

    fun getPokemonList() {
        showLoadingView()
        viewModelScope.launch {
            when (val output = getPokemonListUseCase.invoke(pageIndex)) {
                is Output.Success -> {
                    showLayoutView()
                    list.addAll(output.value.listResults)
                    _pokemonList.value = list
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
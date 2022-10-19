package com.example.pokemonapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.R
import com.example.pokemonapp.data.usecase.GetPokemonListUseCase
import com.example.pokemonapp.data.commons.Output
import com.example.pokemonapp.data.model.PokemonData
import com.example.pokemonapp.presentation.events.EventPokemonList
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
) : BaseViewModel() {

    private val _pokemonList: MutableLiveData<List<PokemonData>> = MutableLiveData()
    val pokemonList: LiveData<List<PokemonData>> = _pokemonList

    private val list: MutableList<PokemonData> = mutableListOf()

    private val _event = MutableLiveData<EventPokemonList>()
    val event: LiveData<EventPokemonList> get() = _event

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
                    if (list.isEmpty()) {
                        showErrorView(getErrorMessage(output.exception))
                    } else {
                        _event.value = EventPokemonList.LostConnection(R.string.lost_connection)
                    }
                }
            }
        }
    }

    fun getNextPokemonList() {
        pageIndex++
        getPokemonList()
    }
}
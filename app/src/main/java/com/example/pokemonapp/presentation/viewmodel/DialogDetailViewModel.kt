package com.example.pokemonapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.commons.Output
import com.example.pokemonapp.data.model.AbilityInfo
import com.example.pokemonapp.data.model.MoveInfo
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.usecase.GetAbilityUseCase
import com.example.pokemonapp.data.usecase.GetMoveUseCase
import com.example.pokemonapp.data.usecase.GetPokemonDetailUseCase
import kotlinx.coroutines.launch

class DialogDetailViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getAbilityUseCase: GetAbilityUseCase,
    private val getMoveUseCase: GetMoveUseCase
) : BaseViewModel() {

    private val _pokemonDetail: MutableLiveData<PokemonDetail> = MutableLiveData()

    private val _pokemonAbility: MutableLiveData<List<AbilityInfo>> = MutableLiveData()
    val pokemonAbility: LiveData<List<AbilityInfo>> = _pokemonAbility
    private val listAbility: MutableList<AbilityInfo> = mutableListOf()

    private val _pokemonMove: MutableLiveData<List<MoveInfo>> = MutableLiveData()
    val pokemonMove: LiveData<List<MoveInfo>> = _pokemonMove
    private val listMove: MutableList<MoveInfo> = mutableListOf()

    fun getPokemonDetail(name: String) {
        showLoadingView()
        viewModelScope.launch {
            when (val output = getPokemonDetailUseCase.invoke(name)) {
                is Output.Success -> {
                    _pokemonDetail.value = output.value
                    listMoreInfo(output.value)
                }
                is Output.Failure -> {
                    showErrorView(getErrorMessage(output.exception))
                }
            }
        }
    }

    private fun listMoreInfo(pokemonDetailLocal: PokemonDetail) {
        pokemonDetailLocal.abilities.map {
            getAbility(it.ability.name)
        }
        pokemonDetailLocal.moves.map {
            getMove(it.move.name)
        }
    }

    private fun getAbility(name: String) {
        viewModelScope.launch {
            when (val output = getAbilityUseCase.invoke(name)) {
                is Output.Success -> {
                    listAbility.add(output.value)
                    _pokemonAbility.value = listAbility
                }
                is Output.Failure -> {
                    showErrorView(getErrorMessage(output.exception))
                }
            }
        }
    }

    private fun getMove(name: String) {
        showLoadingView()
        viewModelScope.launch {
            when (val output = getMoveUseCase.invoke(name)) {
                is Output.Success -> {
                    listMove.add(output.value)
                    _pokemonMove.value = listMove
                }
                is Output.Failure -> {
                    showErrorView(getErrorMessage(output.exception))
                }
            }
        }
    }
}
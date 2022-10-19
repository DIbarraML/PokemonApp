package com.example.pokemonapp.presentation.ui

import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.commons.Output
import com.example.pokemonapp.data.usecase.GetAbilityUseCase
import com.example.pokemonapp.data.usecase.GetMoveUseCase
import com.example.pokemonapp.data.usecase.GetPokemonDetailUseCase
import com.example.pokemonapp.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class DialogDetailViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getAbilityUseCase: GetAbilityUseCase,
    private val getMoveUseCase: GetMoveUseCase
) : BaseViewModel() {

    fun getPokemonDetail(name: String){
        showLoadingView()
        /*viewModelScope.launch {
            when (val output = getPokemonDetailUseCase.invoke(name)) {
                is Output.Success -> {
                    showLayoutView()
                    //
                }
                is Output.Failure -> {
                    showErrorView(getErrorMessage(output.exception))
                }
            }
        }*/
    }
/*
    fun getAbility(name: String){
        showLoadingView()
        viewModelScope.launch {
            when (val output = getAbilityUseCase.invoke(name)) {
                is Output.Success -> {
                    showLayoutView()
                    //
                }
                is Output.Failure -> {
                    showErrorView(getErrorMessage(output.exception))
                }
            }
        }
    }

    fun getMove(name: String) {
        showLoadingView()
        viewModelScope.launch {
            when (val output = getMoveUseCase.invoke(name)) {
                is Output.Success -> {
                    showLayoutView()
                    //
                }
                is Output.Failure -> {
                    showErrorView(getErrorMessage(output.exception))
                }
            }
        }
    }*/
}
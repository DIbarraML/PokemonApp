package com.example.pokemonapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val stateMutableLiveData = MutableLiveData<UIState>()
    val stateEvent: LiveData<UIState> get() = stateMutableLiveData

    protected fun showErrorView(messageError: String) {
        stateMutableLiveData.value = UIState.Error(messageError)
    }

    protected fun showLayoutView() {
        stateMutableLiveData.value = UIState.LayoutView
    }

    protected fun showLoadingView() {
        stateMutableLiveData.value = UIState.Loading
    }
}
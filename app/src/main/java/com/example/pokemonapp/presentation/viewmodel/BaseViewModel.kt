package com.example.pokemonapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.data.service.exceptions.NoConnectivityException
import com.example.pokemonapp.presentation.events.UIState
import java.lang.Exception

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

    fun getErrorMessage(exception: Exception) =
        when (exception) {
            is NoConnectivityException -> { "Sin acceso a internet" }
            else -> exception.message ?: "Lo sentimos, tenemos inconvenientes en el sistema"
        }
}
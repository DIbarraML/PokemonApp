package com.example.pokemonapp.data.service.exceptions

import java.io.IOException

class NoConnectivityException : IOException() {
    companion object {
        private const val NO_CONNECTION_AVAILABLE = "No connection available"
    }
}
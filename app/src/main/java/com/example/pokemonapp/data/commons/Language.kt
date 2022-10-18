package com.example.pokemonapp.data.commons

import com.example.pokemonapp.data.model.Language

enum class UtilLanguage(private val language: String) {
    ENGLISH("en"),
    SPANISH("es");

    companion object {
        fun fromButtonStyleName(language: String) =
            values().firstOrNull { it.language == language }
    }
}

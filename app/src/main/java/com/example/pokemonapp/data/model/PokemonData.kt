package com.example.pokemonapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonData(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    private val urlImage: String
) : Parcelable {
    fun getImageUrl(): String {
        val index = urlImage.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}
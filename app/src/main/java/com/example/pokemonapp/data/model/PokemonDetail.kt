package com.example.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    val abilities: List<Ability>,
    val moves: List<Move>,
    val name: String,
    val types: Types
) {

    data class Ability(
        val ability: AbilityX,
        @SerializedName("is_hidden")
        val isHidden: Boolean,
        val slot: Int
    )

    data class Move(
        val move: MoveX,
    )

    data class AbilityX(
        val name: String,
        val url: String
    )

    data class MoveX(
        val name: String,
        val url: String
    )

    data class Types(
        val types: List<Type>
    )

    data class Type(
        val type: TypeX
    )

    data class TypeX(
        val name: String
    )
}

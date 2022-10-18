package com.example.pokemonapp.data.repository

import com.example.pokemonapp.data.commons.Output
import com.example.pokemonapp.data.model.AbilityInfo
import com.example.pokemonapp.data.model.MoveInfo
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.model.PokemonResponse

interface PokemonRepository {

    suspend fun getPokemonList(page: Int): Output<PokemonResponse>
    suspend fun getPokemonDetail(name: String): Output<PokemonDetail>
    suspend fun getAbility(name: String): Output<AbilityInfo>
    suspend fun getMove(name: String): Output<MoveInfo>
}
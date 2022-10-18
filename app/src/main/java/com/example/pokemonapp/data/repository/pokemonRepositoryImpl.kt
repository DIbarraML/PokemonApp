package com.example.pokemonapp.data.repository

import com.example.pokemonapp.data.commons.Output
import com.example.pokemonapp.data.model.AbilityInfo
import com.example.pokemonapp.data.model.MoveInfo
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.model.PokemonResponse
import com.example.pokemonapp.data.remote.PokemonRemoteDataSource

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
): PokemonRepository {
    override suspend fun getPokemonList(page: Int): Output<PokemonResponse> = pokemonRemoteDataSource.getPokemonList(page)
    override suspend fun getPokemonDetail(name: String): Output<PokemonDetail> = pokemonRemoteDataSource.getPokemonDetail(name)
    override suspend fun getAbility(name: String): Output<AbilityInfo> = pokemonRemoteDataSource.getAbility(name)
    override suspend fun getMove(name: String): Output<MoveInfo> = pokemonRemoteDataSource.getMove(name)
}
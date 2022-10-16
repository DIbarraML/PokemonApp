package com.example.pokemonapp.data

import com.example.pokemonapp.data.model.PokemonResponse

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
): PokemonRepository {
    override suspend fun getPokemonList(page: Int): Output<PokemonResponse> = pokemonRemoteDataSource.getPokemonList(page)
}
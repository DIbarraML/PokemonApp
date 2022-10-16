package com.example.pokemonapp.data

import com.example.pokemonapp.data.model.PokemonResponse

class GetPokemonListUseCase(private val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke(page: Int): Output<PokemonResponse> =
        pokemonRepository.getPokemonList(page)
}
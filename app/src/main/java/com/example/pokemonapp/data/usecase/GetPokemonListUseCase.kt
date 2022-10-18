package com.example.pokemonapp.data.usecase

import com.example.pokemonapp.data.repository.PokemonRepository
import com.example.pokemonapp.data.commons.Output
import com.example.pokemonapp.data.model.PokemonResponse

class GetPokemonListUseCase(private val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke(page: Int): Output<PokemonResponse> =
        pokemonRepository.getPokemonList(page)
}
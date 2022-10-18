package com.example.pokemonapp.data.usecase

import com.example.pokemonapp.data.repository.PokemonRepository
import com.example.pokemonapp.data.commons.Output
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.model.PokemonResponse

class GetPokemonDetailUseCase(private val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke(name: String): Output<PokemonDetail> =
        pokemonRepository.getPokemonDetail(name)
}
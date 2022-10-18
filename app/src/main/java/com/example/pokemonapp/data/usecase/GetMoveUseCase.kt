package com.example.pokemonapp.data.usecase

import com.example.pokemonapp.data.repository.PokemonRepository
import com.example.pokemonapp.data.commons.Output
import com.example.pokemonapp.data.model.AbilityInfo
import com.example.pokemonapp.data.model.MoveInfo
import com.example.pokemonapp.data.model.PokemonResponse

class GetMoveUseCase(private val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke(name: String): Output<MoveInfo> =
        pokemonRepository.getMove(name)
}
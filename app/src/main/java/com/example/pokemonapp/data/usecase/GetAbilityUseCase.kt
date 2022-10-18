package com.example.pokemonapp.data.usecase

import com.example.pokemonapp.data.repository.PokemonRepository
import com.example.pokemonapp.data.commons.Output
import com.example.pokemonapp.data.model.AbilityInfo
import com.example.pokemonapp.data.model.PokemonResponse

class GetAbilityUseCase(private val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke(name: String): Output<AbilityInfo> =
        pokemonRepository.getAbility(name)
}
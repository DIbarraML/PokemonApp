package com.example.pokemonapp.data.model

data class MoveInfo(
    val contest_combos: ContestCombos,
    val damage_class: DamageClass,
    val effect_chance: Int,
    val effect_entries: List<AbilityInfo.EffectEntry>,
    val id: Int,
    val name: String,
    val power: Int
)

data class ContestCombos(
    val normal: Normal,
)

data class DamageClass(
    val name: String,
    val url: String
)

data class Normal(
    val use_after: List<UseAfter>
)

data class UseAfter(
    val name: String,
    val url: String
)

data class UseBefore(
    val name: String,
    val url: String
)

data class Language(
    val name: String,
    val url: String
)
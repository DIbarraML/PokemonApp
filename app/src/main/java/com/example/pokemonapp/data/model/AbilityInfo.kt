package com.example.pokemonapp.data.model

data class AbilityInfo(
    val effect_changes: List<EffectChange>,
    val effect_entries: List<EffectEntry>,
    val id: Int,
    val name: String,
    val names: List<Name>
) {

    data class EffectChange(
        val effect_entries: List<EffectEntry>,
    )

    data class Name(
        val language: Language,
        val name: String
    )

    data class EffectEntry(
        val effect: String,
        val language: Language,
        val short_effect: String
    )


    data class Language(
        val name: String,
        val url: String
    )
}

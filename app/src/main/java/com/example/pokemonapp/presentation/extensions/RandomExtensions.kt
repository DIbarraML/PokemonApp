package com.example.pokemonapp.presentation.extensions

import kotlin.random.Random

fun Random.nextInt(range: IntRange): Int {
    return range.first + nextInt(range.last - range.first)
}
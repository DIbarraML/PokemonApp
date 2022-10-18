package com.example.pokemonapp.presentation.extensions

import android.graphics.Color
import kotlin.random.Random

class Utils {
    companion object {
        fun getBackgroundColorAleatory(): Int =
            Color.argb(
                255,
                Random.nextInt(IntRange(60, 255)),
                Random.nextInt(IntRange(60, 255)),
                Random.nextInt(IntRange(60, 255))
            )
    }
}

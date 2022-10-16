package com.example.pokemonapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

internal inline fun <T : ViewBinding> ViewGroup.viewBinding(
    factory: (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToRoot: Boolean? = true
) = factory(LayoutInflater.from(context), this, attachToRoot ?: true)
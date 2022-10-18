package com.example.pokemonapp.presentation.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding

class DialogPokemonFragment: DialogFragment() {

    private lateinit var binding: FragmentPokemonDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    @SuppressLint("UseGetLayoutInflater", "InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentPokemonDetailBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        return builder.create()
    }
}
package com.example.pokemonapp.presentation.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.PokemonData
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.presentation.extensions.loadImageOrFallback
import com.example.pokemonapp.presentation.viewmodel.DialogDetailViewModelFactory

class DialogPokemonFragment: DialogFragment() {

    private lateinit var binding: FragmentPokemonDetailBinding

    val viewmodel: DialogDetailViewModel by viewModels {
        DialogDetailViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    @SuppressLint("UseGetLayoutInflater", "InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentPokemonDetailBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        setViews()
        return builder.create()
    }

    private fun setViews() {
        val pokemon = arguments?.get(PARAM_EXTRA_POKEMON) as PokemonData
        //val bitmap = arguments?.get(PARAM_EXTRA_BITMAP) as Bitmap

        binding.namePokemon.text = pokemon.name
        binding.imagePokemon.loadImageOrFallback(pokemon.getImageUrl(), R.drawable.pokeball)
        binding.back.setOnClickListener {
            dismiss()
        }



        println("qwewqeqwe-> ${pokemon.toString()}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //val viewmodel = ViewModelProvider(MainActivity.context).get(DialogDetailViewModel::class.java)

    }

    companion object {
        fun newInstance() = DialogPokemonFragment()
        const val PARAM_EXTRA_BITMAP = "bitmap"
        const val PARAM_EXTRA_POKEMON = "pokemon"
    }
}
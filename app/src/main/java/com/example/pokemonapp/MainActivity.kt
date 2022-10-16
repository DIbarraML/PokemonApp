package com.example.pokemonapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.pokemonapp.data.model.PokemonData
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.presentation.PokemonAdapter
import com.example.pokemonapp.presentation.PokemonListener
import com.example.pokemonapp.presentation.PokemonViewModel
import com.example.pokemonapp.presentation.PokemonViewModelFactory

class MainActivity : AppCompatActivity(), PokemonListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PokemonViewModel by viewModels {
        PokemonViewModelFactory()
    }

    private var adapter = PokemonAdapter(listOf()) { getItemSelected(it) }

    @SuppressLint("LogConditional")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PokemonApp)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerPokemon.adapter = adapter
        viewModel.getPokemonList()

        viewModel.isLoading.observe(this) { visible ->
            binding.progressBar.apply {
                if (visible) isVisible = true else isGone = true
            }
        }

        viewModel.pokemonList.observe(this) {
            adapter.listPokemon = it.listResults
        }
    }

    private fun getItemSelected(pokemonData: PokemonData) {
        println("name pokemon-> " + pokemonData.name)
    }

    override fun pokemonSelected(pokemonData: PokemonData) {
        TODO("Not yet implemented")
    }

    override fun getNextPokemonList() {
        TODO("Not yet implemented")
    }
}
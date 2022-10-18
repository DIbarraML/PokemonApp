package com.example.pokemonapp.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.PokemonData
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.presentation.PokemonListener
import com.example.pokemonapp.presentation.viewmodel.PokemonViewModel
import com.example.pokemonapp.presentation.viewmodel.PokemonViewModelFactory
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator

class MainActivity : AppCompatActivity(), PokemonListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PokemonViewModel by viewModels {
        PokemonViewModelFactory(application)
    }
    private lateinit var paginator: RecyclerViewPaginator

    private var adapter = PokemonAdapter(mutableListOf()) { getItemSelected(it) }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PokemonApp)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        paginator = RecyclerViewPaginator(
            recyclerView = binding.recyclerPokemon,
            isLoading = { false },
            onLast = { false },
            loadMore = { viewModel.getNextPokemonList() }
        )
        paginator.threshold = 2

        binding.recyclerPokemon.adapter = adapter
        binding.refresh.setOnClickListener {
            println("REFRESH")
            viewModel.getPokemonList()
        }
        viewModel.getPokemonList()

        viewModel.stateEvent.observe(this) {
            when (it) {
                is UIState.LayoutView -> { showLayoutView() }
                is UIState.Loading -> { binding.progressBar.isVisible = true }
                is UIState.Error -> { showLayoutError( it.messageError ) }
            }
        }

        viewModel.pokemonList.observe(this) {

        }

        viewModel.pokemonList.observe(this) {
            adapter.listPokemon = it
            adapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("Recycle")
    private fun showLayoutView() {
        binding.recyclerPokemon.isVisible = true
        binding.layoutError.isGone = true
        binding.progressBar.isGone = true
    }

    private fun showLayoutError(messageError: String) {
        binding.textWarning.text = messageError
        binding.layoutError.isVisible = true
        binding.recyclerPokemon.isGone = true
        binding.progressBar.isGone = true
    }

    private fun getItemSelected(pokemonData: PokemonData) {
        println("name pokemon-> " + pokemonData.name)
    }

    override fun getNextPokemonList() {
        viewModel.getNextPokemonList()
    }
}
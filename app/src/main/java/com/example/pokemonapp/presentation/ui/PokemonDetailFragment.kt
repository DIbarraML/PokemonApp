package com.example.pokemonapp.presentation.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.presentation.delegate.viewBinding

class PokemonDetailFragment : Fragment() {

    private val binding by viewBinding ( FragmentPokemonDetailBinding::bind )

    companion object {
        fun newInstance() = PokemonDetailFragment()
    }

    private lateinit var viewModel: PokemonDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[PokemonDetailViewModel::class.java]
        // TODO: Use the ViewModel

       // val recyclerView
        val list: Map<String, String> = mapOf("move1" to "description1", "move2" to "description2")



    }


}
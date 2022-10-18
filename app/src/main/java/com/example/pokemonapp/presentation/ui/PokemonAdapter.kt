package com.example.pokemonapp.presentation.ui

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.PokemonData
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.example.pokemonapp.loadImageOrFallback
import com.example.pokemonapp.presentation.extensions.Utils
import com.example.pokemonapp.presentation.extensions.nextInt
import kotlin.random.Random

class PokemonAdapter(var listPokemon: List<PokemonData>, private val onClickListener: (PokemonData) -> Unit) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPokemon[position], onClickListener)
    }

    override fun getItemCount(): Int = listPokemon.size

    class ViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemonData: PokemonData, onClickListener: (PokemonData) -> Unit) = with(binding) {
            namePokemon.text = pokemonData.name
            imagePokemon.loadImageOrFallback(pokemonData.getImageUrl(), R.drawable.pokeball)
            binding.cardView.apply {
                backgroundTintList = ColorStateList.valueOf(Utils.getBackgroundColorAleatory())
                println("color es -> ${Random.nextInt(IntRange(60, 255))}")
                setOnClickListener {
                    onClickListener.invoke(pokemonData)
                }
            }
        }
    }
}
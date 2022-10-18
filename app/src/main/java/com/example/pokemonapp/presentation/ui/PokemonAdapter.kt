package com.example.pokemonapp.presentation.ui

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.core.widget.ImageViewCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.PokemonData
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.example.pokemonapp.presentation.PokemonInfoListener
import com.example.pokemonapp.presentation.extensions.loadImageOrFallback

class PokemonAdapter(var listPokemon: List<PokemonData>, private val pokemonInfoListener: PokemonInfoListener) :
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
        holder.bind(listPokemon[position], pokemonInfoListener)
    }

    override fun getItemCount(): Int = listPokemon.size

    class ViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemonData: PokemonData, pokemonInfoListener: PokemonInfoListener) = with(binding) {
            namePokemon.text = pokemonData.name
            imagePokemon.loadImageOrFallback(pokemonData.getImageUrl(), R.drawable.pokeball)
            binding.cardView.apply {
                val bitmap = imagePokemon.drawable.toBitmap()
                val palette = Palette.from(bitmap).generate()
                palette.dominantSwatch?.let {
                    this.backgroundTintList = ColorStateList.valueOf(it.rgb)
                }
                setOnClickListener {
                    pokemonInfoListener.getInfo(pokemonData, binding.imagePokemon.drawable.toBitmap())
                }
            }
        }
    }
}

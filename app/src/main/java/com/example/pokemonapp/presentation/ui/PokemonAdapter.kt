package com.example.pokemonapp.presentation.ui

import android.content.DialogInterface
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.PokemonData
import com.example.pokemonapp.databinding.ItemPokemonBinding
import com.example.pokemonapp.presentation.extensions.loadImageOrFallback

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

        fun bind(pokemonData: PokemonData,  onClickListener: (PokemonData) -> Unit) = with(binding) {
            imagePokemon.loadImageOrFallback(
                pokemonData.getImageUrl(),
                R.drawable.pokeball,
                object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        println("bind onsuccess->")
                        val palette = Palette.from(imagePokemon.drawable.toBitmap()).generate()
                        palette.dominantSwatch?.let { cardView.backgroundTintList = ColorStateList.valueOf(it.rgb) }
                    }

                    override fun onError() {
                        println("bind onerror->")
                    }
                })
            namePokemon.text = pokemonData.name
            cardView.apply {
                setOnClickListener  {
                    onClickListener.invoke(pokemonData)
                }
            }
        }
    }
}

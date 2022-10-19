package com.example.pokemonapp.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.InfoItemBinding
import com.example.pokemonapp.databinding.ItemPokemonBinding

class SimpleItemAdapter(val list: ArrayList<HashMap<String, String>>): RecyclerView.Adapter<SimpleItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = InfoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: InfoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hash: HashMap<String, String>) {
            binding.nameRecycler.text = hash[DialogPokemonFragment.HASH_NAME]
            binding.descriptionRecycler.text = hash[DialogPokemonFragment.HASH_DESCRIPTION]
        }
    }
}
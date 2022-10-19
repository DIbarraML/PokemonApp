package com.example.pokemonapp.presentation.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.data.commons.UtilLanguage
import com.example.pokemonapp.data.model.AbilityInfo
import com.example.pokemonapp.data.model.MoveInfo
import com.example.pokemonapp.data.model.PokemonData
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.presentation.extensions.loadImageOrFallback
import com.example.pokemonapp.presentation.viewmodel.DialogDetailViewModel
import com.example.pokemonapp.presentation.viewmodel.DialogDetailViewModelFactory

class DialogPokemonFragment : DialogFragment() {

    private lateinit var binding: FragmentPokemonDetailBinding

    private val viewModel: DialogDetailViewModel by viewModels {
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
        binding.namePokemon.text = pokemon.name
        binding.imagePokemon.loadImageOrFallback(pokemon.getImageUrl(), R.drawable.pokeball)
        binding.back.setOnClickListener {
            dismiss()
        }
        viewModel.getPokemonDetail(pokemon.name)

        viewModel.pokemonAbility.observe(this) {
            binding.recyclerAbility.layoutManager = LinearLayoutManager(context)
            binding.recyclerAbility.adapter = SimpleItemAdapter(getAbilities(it))

        }

        viewModel.pokemonMove.observe(this) {
            binding.recyclerMove.layoutManager = LinearLayoutManager(context)
            binding.recyclerMove.adapter = SimpleItemAdapter(getMoves(it))
        }
    }

    private fun getMoves(list: List<MoveInfo>): ArrayList<HashMap<String, String>> {
        val listMoves: ArrayList<HashMap<String, String>> = arrayListOf()
        list.map { moveInfo ->
            val hashMap: HashMap<String, String> = hashMapOf()
            moveInfo.effect_entries.map {
                wrapper(it)?.let { description ->
                    hashMap[HASH_NAME] = moveInfo.name
                    hashMap[HASH_DESCRIPTION] = description
                    listMoves.add(hashMap)
                }
            }
        }
        return listMoves
    }

    private fun getAbilities(list: List<AbilityInfo>): ArrayList<HashMap<String, String>> {
        val listAbilities: ArrayList<HashMap<String, String>> = arrayListOf()
        list.map { ability ->
            val hashMap: HashMap<String, String> = hashMapOf()
            ability.effect_entries.map {
                wrapper(it)?.let { description ->
                    hashMap[HASH_NAME] = ability.name
                    hashMap[HASH_DESCRIPTION] = description
                    listAbilities.add(hashMap)
                }
            }
        }
        return listAbilities
    }

    private fun wrapper(effectEntry: AbilityInfo.EffectEntry): String? {
        return if (UtilLanguage.fromLanguage(effectEntry.language.name) == UtilLanguage.ENGLISH) {
            effectEntry.short_effect
        } else {
            null
        }
    }

    companion object {
        const val PARAM_EXTRA_POKEMON = "pokemon"
        const val HASH_NAME = "name"
        const val HASH_DESCRIPTION = "description"
    }
}
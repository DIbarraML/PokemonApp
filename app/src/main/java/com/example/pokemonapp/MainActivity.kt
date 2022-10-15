package com.example.pokemonapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pokemonapp.data.remote.PokemonClient
import kotlin.concurrent.thread
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @SuppressLint("LogConditional")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PokemonApp)
        setContentView(R.layout.activity_main)


        GlobalScope.launch {
            val pokemons = PokemonClient.service.getPokemonList()
            Log.d("LISTPOKEMOSN", pokemons.body().toString())
        }

    }
}
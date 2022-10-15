package com.example.pokemonapp.data.remote

import com.example.pokemonapp.data.PokemonService
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonClient {

    private const val TIME_OUT = 30_000L
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    private val myClient = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(myClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(PokemonService::class.java)
}
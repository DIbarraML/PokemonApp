package com.example.pokemonapp.data

import com.example.pokemonapp.data.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    )
}
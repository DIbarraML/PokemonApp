package com.example.pokemonapp.data.service

import com.example.pokemonapp.data.model.AbilityInfo
import com.example.pokemonapp.data.model.MoveInfo
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.model.PokemonResponse
import com.google.gson.JsonElement
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
    ): Response<PokemonDetail>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail2(
        @Path("name") name: String
    ): Response<JsonElement>

    @GET("ability/{name}")
    suspend fun getAbility(
        @Path("name") name: String
    ): Response<AbilityInfo>

    @GET("move/{name}")
    suspend fun getMove(
        @Path("name") name: String
    ): Response<MoveInfo>
}
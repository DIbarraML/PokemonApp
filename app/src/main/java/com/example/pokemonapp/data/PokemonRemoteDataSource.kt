package com.example.pokemonapp.data

import android.content.Context
import com.example.pokemonapp.data.model.PokemonResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRemoteDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    val context: Context
) {

    private val pokemonService: PokemonService =
        ServiceFactory.createRepositoryApi(PokemonService::class.java, context = context)

    suspend fun getPokemonList(page: Int): Output<PokemonResponse> {
        runCatching {
            withContext(dispatcher) {
                val result = pokemonService.getPokemonList(
                    limit = PAGING_SIZE,
                    offset = page * PAGING_SIZE
                )
                result.body()
            }
        }.fold(
            onSuccess = { response ->
                return if (response != null) {
                    Output.Success(response)
                } else {
                    Output.Failure(Exception("Sin respuesta del servidor"))
                }
            },
            onFailure = {
                return Output.Failure(it as Exception)
            }
        )
    }

    companion object {
        private const val PAGING_SIZE = 20
    }
}
package com.example.pokemonapp.data.remote

import android.content.Context
import com.example.pokemonapp.data.ServiceFactory
import com.example.pokemonapp.data.commons.Output
import com.example.pokemonapp.data.model.AbilityInfo
import com.example.pokemonapp.data.model.MoveInfo
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.model.PokemonResponse
import com.example.pokemonapp.data.service.PokemonService
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
                    Output.Failure(Exception(MESSAGE_DEFAULT))
                }
            },
            onFailure = {
                return Output.Failure(it as Exception)
            }
        )
    }

    suspend fun getPokemonDetail(name: String): Output<PokemonDetail>{
        runCatching {
            withContext(dispatcher) {
                val result = pokemonService.getPokemonDetail(name)
                result.body()
            }
        }.fold(
            onSuccess = { response ->
                return if (response != null) {
                    Output.Success(response)
                } else {
                    Output.Failure(Exception(MESSAGE_DEFAULT))
                }
            },
            onFailure = {
                return Output.Failure(it as Exception)
            }
        )
    }

    suspend fun getAbility(name: String): Output<AbilityInfo>{
        runCatching {
            withContext(dispatcher) {
                val result = pokemonService.getAbility(name)
                result.body()
            }
        }.fold(
            onSuccess = { response ->
                return if (response != null) {
                    Output.Success(response)
                } else {
                    Output.Failure(Exception(MESSAGE_DEFAULT))
                }
            },
            onFailure = {
                return Output.Failure(it as Exception)
            }
        )
    }

    suspend fun getMove(name: String): Output<MoveInfo>{
        runCatching {
            withContext(dispatcher) {
                val result = pokemonService.getMove(name)
                result.body()
            }
        }.fold(
            onSuccess = { response ->
                return if (response != null) {
                    Output.Success(response)
                } else {
                    Output.Failure(Exception(MESSAGE_DEFAULT))
                }
            },
            onFailure = {
                return Output.Failure(it as Exception)
            }
        )
    }

    companion object {
        private const val PAGING_SIZE = 20
        private const val MESSAGE_DEFAULT = "No response from server"
    }
}
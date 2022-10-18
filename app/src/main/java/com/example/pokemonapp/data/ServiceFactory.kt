package com.example.pokemonapp.data

import android.content.Context
import com.example.pokemonapp.data.remote.ConnectivityStateInterceptor
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {

    private const val TIME_OUT = 30_000L
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    fun <T> createRepositoryApi(
        repositoryApiClass: Class<T>,
        timeOut: Long = TIME_OUT,
        timeUnit: TimeUnit = TimeUnit.MILLISECONDS,
        context: Context
    ): T {
        val myClient = OkHttpClient.Builder()
            .connectTimeout(timeOut, timeUnit)
            .readTimeout(timeOut, timeUnit)
            .addInterceptor(ConnectivityStateInterceptor(context))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(myClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(repositoryApiClass)
    }
}
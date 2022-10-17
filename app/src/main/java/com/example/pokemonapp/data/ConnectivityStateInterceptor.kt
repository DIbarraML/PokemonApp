package com.example.pokemonapp.data

import android.content.Context
import android.net.ConnectivityManager
import com.example.pokemonapp.data.service.exceptions.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityStateInterceptor(context: Context) : Interceptor {

    private val context: Context = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return try {
            if (!connectivityManager.activeNetworkInfo!!.isAvailable
                || !connectivityManager.activeNetworkInfo!!.isConnected
            ) {
                throw NoConnectivityException()
            } else {
                chain.proceed(chain.request())
            }
        } catch (e: NullPointerException) {
            throw NoConnectivityException()
        }
    }
}
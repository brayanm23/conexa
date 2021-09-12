package com.conexa.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private lateinit var httpClient: Retrofit

    fun getClient(baseUrl: String) = if (RetrofitBuilder::httpClient.isInitialized) httpClient else initRetrofit(baseUrl)

    private fun initRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
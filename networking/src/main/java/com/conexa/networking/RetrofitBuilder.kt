package com.conexa.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    private lateinit var httpClient: Retrofit

    fun getClient(baseUrl: String) = if (RetrofitBuilder::httpClient.isInitialized) httpClient else initRetrofit(baseUrl)

    private fun initRetrofit(baseUrl: String): Retrofit {

        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(InterceptorRequest())
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClientBuilder)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
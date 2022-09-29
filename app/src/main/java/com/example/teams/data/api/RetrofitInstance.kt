package com.example.teams.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val api:CandidatesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://my.api.mockaroo.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CandidatesApi::class.java)
    }
}
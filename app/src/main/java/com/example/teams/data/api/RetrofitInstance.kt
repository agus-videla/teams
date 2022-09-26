package com.example.teams.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    val api:CandidatesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://my.api.mockaroo.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CandidatesApi::class.java)
    }
}
package com.example.teams.data.api

import com.example.teams.data.database.entities.Candidate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CandidatesApi {
    @GET(value = "/candidates.json")
    fun getCandidates(@Query(value = "key") key: String): Response<List<Candidate>>
}


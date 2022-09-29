package com.example.teams.data.api

data class ApiCandidate(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val gender: String,
    val age: Int,
    val job_title: String,
    val quote: String,
    val bio: String,
    val email: String,
    val profile_picture: String,
)
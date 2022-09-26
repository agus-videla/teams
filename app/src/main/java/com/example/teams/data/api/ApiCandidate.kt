package com.example.teams.data.api

data class ApiCandidate(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val age: Int,
    val jobTitle: String,
    val quote: String,
    val bio: String,
    val profilePicture: String,
    val idTeam: Int?
)

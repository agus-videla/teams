package com.example.teams.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Candidates",
    foreignKeys = [ForeignKey(entity = Team::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("team_id"),
        onDelete = ForeignKey.CASCADE)])
class Candidate(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "job_title") val jobTitle: String,
    @ColumnInfo(name = "quote") val quote: String,
    @ColumnInfo(name = "bio") val bio: String,
    @ColumnInfo(name = "profile_picture") val profilePicture: String,
    @ColumnInfo(name = "team_id") val idTeam: Int?,
) {
    constructor() : this(
        -1,
        "",
        "",
        "",
        "",
        0,
        "",
        "",
        "",
        "",
        null
    )
}

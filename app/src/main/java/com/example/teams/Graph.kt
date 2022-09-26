package com.example.teams

import android.content.Context
import com.example.teams.data.database.TeamsDatabase
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.CoroutineScope

object Graph {
    lateinit var database: TeamsDatabase
        private set
    val teamsRepository by lazy {
        TeamsRepository(database.candidateDao(),database.teamDao())
    }

    fun provide(context: Context, scope: CoroutineScope) {
        database = TeamsDatabase.getDatabase(context, scope)
    }
}
package com.example.teams

import android.app.Application
import com.example.teams.data.model.TeamsDatabase
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TeamsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { TeamsDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { TeamsRepository(database.candidateDao()) }
}
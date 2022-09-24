package com.example.teams

import android.app.Application
import com.example.teams.data.model.TeamsDatabase
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope

class TeamsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this, CoroutineScope(SupervisorJob()))
    }
}
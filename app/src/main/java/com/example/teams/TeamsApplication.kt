package com.example.teams

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TeamsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this, CoroutineScope(SupervisorJob()))
    }
}
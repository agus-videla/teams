package com.example.teams.ui.screens.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teams.data.repository.TeamsRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: TeamsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TeamsRepository::class.java).newInstance(repository)
    }
}
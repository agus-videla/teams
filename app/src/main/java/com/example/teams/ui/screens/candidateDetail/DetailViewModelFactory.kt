package com.example.teams.ui.screens.candidateDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val idCandidate: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(idCandidate = idCandidate) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}
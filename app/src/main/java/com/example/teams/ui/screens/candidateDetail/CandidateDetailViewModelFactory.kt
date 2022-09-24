package com.example.teams.ui.screens.candidateDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class CandidateDetailViewModelFactory(private val idCandidate: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CandidateDetailViewModel::class.java)) {
            return CandidateDetailViewModel(idCandidate = idCandidate) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}
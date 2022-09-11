package com.example.teams.ui.screens.candidateDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teams.data.model.entities.Candidate
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CandidateDetailViewModel(private val repository: TeamsRepository) : ViewModel() {
    private val _state: MutableStateFlow<Candidate> = MutableStateFlow(Candidate())
    val state: StateFlow<Candidate> get() = _state

    fun select(id: Int) {
        viewModelScope.launch {
            repository.select(id).collect{
                _state.value = it
            }
        }
    }
}
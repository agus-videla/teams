package com.example.teams.ui.screens.candidates

import androidx.lifecycle.*
import com.example.teams.data.model.entities.Candidate
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CandidatesViewModel(private val repository: TeamsRepository) : ViewModel() {
    private val _state: MutableStateFlow<List<Candidate>> = MutableStateFlow(emptyList())
    val state: StateFlow<List<Candidate>> get() = _state

    private val candidateList = repository.selectAllCandidates()

    init {
        viewModelScope.launch {
            candidateList.collect {
                _state.value = it
            }
        }
    }

    fun insert(candidate: Candidate) = viewModelScope.launch {
        repository.insertCandidate(candidate)
    }
}
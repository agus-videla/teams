package com.example.teams.ui.screens.candidates

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teams.Graph
import com.example.teams.data.database.entities.Candidate
import com.example.teams.data.database.entities.Team
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.launch


class CandidatesViewModel(
    private val repository: TeamsRepository = Graph.teamsRepository
    ) : ViewModel() {
    private val _candidates: MutableState<List<Candidate>> = mutableStateOf(emptyList())
    val candidates: State<List<Candidate>> get() = _candidates
    private val _teams: MutableState<List<Team>> = mutableStateOf(emptyList())
    val teams: State<List<Team>> get() = _teams

    private val candidateList = repository.selectAllCandidates()
    private val teamList = repository.selectAllTeamsOrderedById()

    init {
        viewModelScope.launch {
            teamList.collect {
                _teams.value = it
            } // señalar finalizacion de operacion

        }
    }

    init {
        viewModelScope.launch {
            candidateList.collect {
                _candidates.value = it
            }
        }
    }

    fun insert(candidate: Candidate) = viewModelScope.launch {
        repository.insertCandidate(candidate)
    }

    fun updateTeam(id: Int, idTeam: Int) {
        viewModelScope.launch {
            repository.updateTeam(id, idTeam)
        }
    }
}
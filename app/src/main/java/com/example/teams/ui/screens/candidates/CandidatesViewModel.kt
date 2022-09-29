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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CandidatesViewModel(
    private val repository: TeamsRepository = Graph.teamsRepository
    ) : ViewModel() {

    private val _uiState: MutableState<UIState> = mutableStateOf(UIState.Loading)
    val uiState: State<UIState> get() = _uiState
    private val _teams: MutableState<List<Team>> = mutableStateOf(emptyList())
    val teams: State<List<Team>> get() = _teams

    //private val candidateList = repository.selectAllCandidates()
    private val teamList = repository.selectAllTeamsOrderedById()

    init {
        viewModelScope.launch {
            teamList.collect {
                _teams.value = it
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.selectAllCandidates().collect {
                withContext(Dispatchers.Main){
                    _uiState.value = UIState.Success(it)
                }
            }
        }
    }

    fun updateTeam(id: Int, idTeam: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val candidate = repository.API_RESPONSE.first { it.id == id }
            val dbCandidateId = repository.insertCandidate(candidate)
            repository.updateTeam(dbCandidateId, idTeam)
        }
    }
}

sealed class UIState(val data: List<Candidate>) {
    object Loading : UIState(emptyList())
    class Success(data: List<Candidate>) : UIState(data)
}
package com.example.teams.ui.screens.teams

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.teams.data.model.entities.Candidate
import com.example.teams.data.model.entities.Team
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class TeamsViewModel(private val repository: TeamsRepository): ViewModel() {
    private val _state: MutableStateFlow<MutableList<TeamWithMembers>> = MutableStateFlow(mutableListOf())
    val state: StateFlow<List<TeamWithMembers>> get() = _state

    private var allTeams: List<Team> = listOf(
        Team(1,"hi","how",3),
        Team(2,"bye","fuck",2)
    )
    private val allTeamsWithMembers: MutableList<TeamWithMembers> = mutableListOf()

    init {
        viewModelScope.launch {
            repository.selectAllTeams().collect {
                for (team in it) {
                    repository.selectCandidatesOfTeam(team.id).collect{ candidates ->
                        _state.value.add(TeamWithMembers(team, candidates))
                    }
                }
            }


        }
    }

    data class TeamWithMembers(
        val team: Team,
        val candidates : List<Candidate>?
    )
}


package com.example.teams.ui.screens.teams

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teams.data.model.entities.Candidate
import com.example.teams.data.model.entities.Team
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.math.log

class TeamsViewModel(private val repository: TeamsRepository): ViewModel() {
    private val _state: MutableStateFlow<MutableList<TeamWithMembers>> = MutableStateFlow(mutableListOf())
    val state: StateFlow<List<TeamWithMembers>> get() = _state

    private val allTeams = repository.selectAllTeams()

    init {
        viewModelScope.launch {
            allTeams.map {
                it.map { async { TeamWithMembers(it, repository.selectCandidatesOfTeam(it.id).first()) } }.awaitAll()
            }.collect {
                _state.value = it.toMutableList()
            }
                //for (team in it) {
                    //repository.selectCandidatesOfTeam(team.id).collect{ candidates ->
                        //_state.value.add(TeamWithMembers(team, candidates))
                    //}
                //}
        }
    }


    data class TeamWithMembers(
        val team: Team,
        val candidates : List<Candidate>?
    )
}


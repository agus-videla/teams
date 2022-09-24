package com.example.teams.data.repository

import androidx.annotation.WorkerThread
import com.example.teams.data.model.dao.CandidateDao
import com.example.teams.data.model.dao.TeamDao
import com.example.teams.data.model.entities.Candidate
import com.example.teams.data.model.entities.Team
import kotlinx.coroutines.flow.Flow

class TeamsRepository(
    private val candidateDao: CandidateDao,
    private val teamDao: TeamDao
) {
    @WorkerThread
    suspend fun insertCandidate(candidate: Candidate) {
        candidateDao.insert(candidate)
    }

    fun selectCandidate(id: Int): Flow<Candidate> {
        return candidateDao.getCandidate(id)
    }

    fun selectCandidatesOfTeam(id: Int): Flow<List<Candidate>> {
        return candidateDao.getCandidatesOfTeam(id)
    }

    fun selectAllCandidates(): Flow<List<Candidate>> {
        return candidateDao.getAllCandidates()
    }

    @WorkerThread
    suspend fun insertTeam(team: Team) {
        teamDao.insert(team)
    }

    @WorkerThread
    suspend fun updateTeam(id: Int, idTeam: Int) {
        candidateDao.updateTeam(id, idTeam)
    }

    fun selectTeam(id: Int): Flow<Team> {
        return teamDao.getTeam(id)
    }

    fun selectAllTeams(): Flow<List<Team>> {
        return teamDao.getAllTeams()
    }
}
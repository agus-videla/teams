package com.example.teams.data.repository

import androidx.annotation.WorkerThread
import com.example.teams.data.model.dao.CandidateDao
import com.example.teams.data.model.entities.Candidate
import kotlinx.coroutines.flow.Flow

class TeamsRepository(private val candidateDao: CandidateDao) {
    val allCandidates: Flow<List<Candidate>> = candidateDao.getAllCandidates()

    @WorkerThread
    suspend fun insert(candidate: Candidate) {
        candidateDao.insert(candidate)
    }
}
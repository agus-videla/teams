package com.example.teams.data.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.teams.data.api.ApiCandidate
import com.example.teams.data.api.RetrofitInstance
import com.example.teams.data.database.dao.CandidateDao
import com.example.teams.data.database.dao.TeamDao
import com.example.teams.data.database.entities.Candidate
import com.example.teams.data.database.entities.Team
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

const val TAG = "Repository"

class TeamsRepository(
    private val candidateDao: CandidateDao,
    private val teamDao: TeamDao,
    private val API_KEY: String = "52585be0"
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

    fun selectAllTeamsOrderedById(): Flow<List<Team>> {
        return teamDao.getAllTeamsOrderedById()
    }

    fun selectAllTeamsOrderedByMembers(): Flow<List<Team>> {
        return teamDao.getAllTeamsOrderedByMembers()
    }

    @OptIn(DelicateCoroutinesApi::class) //?
    suspend fun getApiCandidates(): List<Candidate> {
        var list: List<Candidate> = emptyList()
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCandidates(API_KEY)
            } catch (e: IOException) {
                Log.e(TAG, "IOException, internet connection might be unavailable")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                list = response.body()!!
            } else {
                Log.e(TAG, "Bad Response")
            }
        }.join()
        return list
    }

}
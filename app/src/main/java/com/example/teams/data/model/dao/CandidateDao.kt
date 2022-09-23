package com.example.teams.data.model.dao

import androidx.room.*
import com.example.teams.data.model.entities.Candidate
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidateDao {
    @Query("SELECT * FROM Candidates ORDER BY id ASC")
    fun getAllCandidates(): Flow<List<Candidate>>

    @Query("SELECT * FROM Candidates WHERE Candidates.id = :id")
    fun getCandidate(id: Int): Flow<Candidate>

    @Query("SELECT * FROM Candidates WHERE Candidates.team_id = :idTeam")
    fun getCandidatesOfTeam(idTeam: Int): Flow<List<Candidate>>

    @Query("UPDATE Candidates SET team_id = :idTeam WHERE team_id = NULL")
    suspend fun updateNullTeam(idTeam : Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(candidate: Candidate)

    @Query("DELETE FROM Candidates")
    suspend fun deleteAll()
}
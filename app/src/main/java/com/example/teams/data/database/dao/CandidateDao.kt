package com.example.teams.data.database.dao

import androidx.room.*
import com.example.teams.data.database.entities.Candidate
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidateDao {
    @Query("SELECT * FROM Candidates ORDER BY id ASC")
    fun getAllCandidates(): List<Candidate>

    @Query("SELECT * FROM Candidates WHERE Candidates.id = :id")
    fun getCandidate(id: Int): Flow<Candidate>

    @Query("SELECT * FROM Candidates WHERE Candidates.team_id = :idTeam")
    fun getCandidatesOfTeam(idTeam: Int): Flow<List<Candidate>>

    @Query("UPDATE Candidates SET team_id = :idTeam WHERE id = :id")
    suspend fun updateTeam(id: Long, idTeam: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(candidate: Candidate): Long

    @Query("DELETE FROM Candidates")
    suspend fun deleteAll()

    @Query("SELECT MAX(id) FROM Candidates")
    fun getMaxId(): Int
}
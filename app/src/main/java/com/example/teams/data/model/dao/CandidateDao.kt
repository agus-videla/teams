package com.example.teams.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teams.data.model.entities.Candidate
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidateDao {
    @Query("SELECT * FROM Candidates ORDER BY id ASC")
    fun getAllCandidates(): Flow<List<Candidate>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(candidate: Candidate)

    @Query("DELETE FROM Candidates")
    suspend fun deleteAll()
}
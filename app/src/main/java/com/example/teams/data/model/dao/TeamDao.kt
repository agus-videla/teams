package com.example.teams.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teams.data.model.entities.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("SELECT * FROM Teams ORDER BY id ASC")
    fun getAllTeams(): Flow<List<Team>>

    @Query("SELECT * FROM Teams WHERE Teams.id = :id")
    fun getTeam(id: Int): Flow<Team>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(team: Team)

    @Query("DELETE FROM Teams")
    suspend fun deleteAll()
}
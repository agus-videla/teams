package com.example.teams.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teams.data.database.entities.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("select Teams.id, Teams.name, Teams.description, Teams.max_members from Teams left join (select Candidates.team_id,  count(*) quantity from Candidates left join Teams on Candidates.team_id == Teams.id group by team_id) as sub on Teams.id = sub.team_id order by sub.quantity desc")
    fun getAllTeamsOrderedByMembers(): Flow<List<Team>>

    @Query("select Teams.* from Teams left join Candidates on Teams.id = Candidates.team_id group by Teams.id having  count(*) < Teams.max_members order by Teams.id")
    fun getAllTeamsOrderedById(): Flow<List<Team>>

    @Query("SELECT * FROM Teams WHERE Teams.id = :id")
    fun getTeam(id: Int): Flow<Team>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(team: Team)

    @Query("DELETE FROM Teams")
    suspend fun deleteAll()
}
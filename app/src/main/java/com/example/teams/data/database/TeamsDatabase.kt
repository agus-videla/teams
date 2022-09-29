package com.example.teams.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teams.data.database.dao.CandidateDao
import com.example.teams.data.database.dao.TeamDao
import com.example.teams.data.database.entities.Candidate
import com.example.teams.data.database.entities.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Candidate::class, Team::class], version = 10, exportSchema = false)
abstract class TeamsDatabase : RoomDatabase() {

    abstract fun candidateDao(): CandidateDao
    abstract fun teamDao(): TeamDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TeamsDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope,
        ): TeamsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamsDatabase::class.java,
                    "teams_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
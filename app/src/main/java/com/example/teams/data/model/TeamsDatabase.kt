package com.example.teams.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teams.data.model.dao.CandidateDao
import com.example.teams.data.model.entities.Candidate
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Candidate::class], version = 1, exportSchema = false)
abstract class TeamsDatabase : RoomDatabase() {

    abstract fun candidateDao(): CandidateDao


    private class TeamsDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        /*
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val candidateDao = database.candidateDao()

                    // Delete all content here.
                    candidateDao.deleteAll()

                    // Add sample words.
                    var candidate = Candidate(
                        1,
                        "agus",
                        "videla",
                        "email",
                        "male",
                        24,
                        "engineer",
                        "this sucks",
                        "heelo"
                    )
                    candidateDao.insert(candidate)
                    candidate = Candidate(
                        2,
                        "john",
                        "ramirez",
                        "this",
                        "that",
                        43,
                        "djflaskdjf",
                        "another thing",
                        "always forevere"
                    )
                    candidateDao.insert(candidate)
                }
            }
        }*/
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TeamsDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TeamsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamsDatabase::class.java,
                    "teams_database"
                )
                    .addCallback(TeamsDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
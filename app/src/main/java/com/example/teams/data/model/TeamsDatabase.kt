package com.example.teams.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teams.data.model.dao.CandidateDao
import com.example.teams.data.model.dao.TeamDao
import com.example.teams.data.model.entities.Candidate
import com.example.teams.data.model.entities.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Candidate::class, Team::class], version = 7, exportSchema = false)
abstract class TeamsDatabase : RoomDatabase() {

    abstract fun candidateDao(): CandidateDao
    abstract fun teamDao(): TeamDao


    private class TeamsDatabaseCallback(
        private val scope: CoroutineScope,
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val candidateDao = database.candidateDao()
                    val teamDao = database.teamDao()

                    candidateDao.deleteAll()
                    teamDao.deleteAll()

                    var team = Team(
                        1,
                        "Dynamic Duo",
                        "Pair Programming Legends",
                        2
                    )
                    teamDao.insert(team)
                    team = Team(
                        2,
                        "Dynamic Trio",
                        "Developers.",
                        3)
                    teamDao.insert(team)
                    var candidate = Candidate(
                        1,
                        "Ursa",
                        "Moorcroft",
                        "umoorcroft0@nytimes.com",
                        "Female",
                        20,
                        "VP Quality Control",
                        "Multi-lateral responsive software",
                        "ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur in libero ut massa volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in leo maecenas pulvinar lobortis est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim in tempor turpis nec euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc proin at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi sit amet lobortis sapien sapien non mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus",
                        "https://robohash.org/dictamolestiaetemporibus.png?size=50x50&set=set1",
                        1
                    )
                    candidateDao.insert(candidate)
                    candidate = Candidate(
                        2,
                        "Domini",
                        "Meah",
                        "dmeah1@msu.edu",
                        "Polygender",
                        28,
                        "Physical Therapy Assistant",
                        "Synchronised upward-trending installation",
                        "sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in blandit ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur in",
                        "https://robohash.org/quaedolorest.png?size=50x50&set=set1",
                        1
                    )
                    candidateDao.insert(candidate)
                }
            }
        }
    }

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
                    .addCallback(TeamsDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
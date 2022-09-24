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

                    var team = Team(1, "Kwinu", "Virtual zero administration extranet", 7)
                    teamDao.insert(team)
                    team = Team(2, "Twinder", "Managed coherent pricing structure", 8)
                    teamDao.insert(team)
                    team = Team(3, "Muxo", "Up-sized 24/7 website", 7)
                    teamDao.insert(team)
                    team = Team(4, "DabZ", "Open-architected 3rd generation capability", 7)
                    teamDao.insert(team)
                    team = Team(5, "Voomm", "Proactive impactful encryption", 2)
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
                        "https://robohash.org/dictamolestiaetemporibus.png?size=2000x2000&set=set1",
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
                        "https://robohash.org/quaedolorest.png?size=2000x2000&set=set1",
                        1
                    )
                    candidateDao.insert(candidate)
                    candidate = Candidate(3,
                        "Hefn",
                        "Zbbepebsg",
                        "hzbbepebsg0@algvzrf.pbz",
                        "Srznyr",
                        20,
                        "IC Dhnyvgl Pbageby",
                        "Zhygv-yngreny erfcbafvir fbsgjner",
                        "hygevprf cunfryyhf vq fncvra va fncvra vnphyvf pbathr ivinzhf zrghf neph nqvcvfpvat zbyrfgvr uraqerevg ng ihychgngr ivgnr avfy nrarna yrpghf cryyragrfdhr rtrg ahap qbarp dhvf bepv rtrg bepv iruvphyn pbaqvzraghz phenovghe va yvoreb hg znffn ibyhgcng pbainyyvf zbeov bqvb bqvb ryrzraghz rh vagreqhz rh gvapvqhag va yrb znrpranf chyivane ybobegvf rfg cunfryyhf fvg nzrg reng ahyyn grzchf ivinzhf va sryvf rh fncvra phefhf irfgvohyhz cebva rh zv ahyyn np ravz va grzcbe ghecvf arp rhvfzbq fpryrevfdhr dhnz ghecvf nqvcvfpvat yberz ivgnr znggvf avou yvthyn arp frz qhvf nyvdhnz pbainyyvf ahap cebva ng ghecvf n crqr cbfhrer abahzzl vagrtre aba iryvg qbarp qvnz ardhr irfgvohyhz rtrg ihychgngr hg hygevprf iry nhthr irfgvohyhz nagr vcfhz cevzvf va snhpvohf bepv yhpghf rg hygevprf cbfhrer phovyvn phenr qbarp cunergen zntan irfgvohyhz nyvdhrg hygevprf reng gbegbe fbyyvpvghqva zv fvg nzrg ybobegvf fncvra fncvra aba zv vagrtre np ardhr qhvf ovoraqhz zbeov aba dhnz arp qhv yhpghf ehgehz ahyyn gryyhf va fntvggvf qhv iry avfy qhvf np avou shfpr ynphf chehf",
                        "https://robohash.org/nihilreprehenderitquia.png?size=2000x2000&set=set1",
                        2)
                    candidateDao.insert(candidate)
                    candidate = Candidate(4,
                        "Qbzvav",
                        "Zrnu",
                        "qzrnu1@zfh.rqh",
                        "Cbyltraqre",
                        28,
                        "Culfvpny Gurencl Nffvfgnag",
                        "Flapuebavfrq hcjneq-geraqvat vafgnyyngvba",
                        "fvg nzrg ryrvsraq crqr yvoreb dhvf bepv ahyynz zbyrfgvr avou va yrpghf cryyragrfdhr ng ahyyn fhfcraqvffr cbgragv penf va chehf rh zntan ihychgngr yhpghf phz fbpvvf angbdhr crangvohf rg zntavf qvf cneghevrag zbagrf anfprghe evqvphyhf zhf ivinzhf irfgvohyhz fntvggvf fncvra phz fbpvvf angbdhr crangvohf rg zntavf qvf cneghevrag zbagrf anfprghe evqvphyhf zhf rgvnz iry nhthr irfgvohyhz ehgehz ehgehz ardhr nrarna nhpgbe tenivqn frz cenrfrag vq znffn vq avfy irarangvf ynpvavn nrarna fvg nzrg whfgb zbeov hg bqvb penf zv crqr znyrfhnqn va vzcreqvrg rg pbzzbqb ihychgngr whfgb va oynaqvg hygevprf ravz yberz vcfhz qbybe fvg nzrg pbafrpgrghre nqvcvfpvat ryvg cebva vagreqhz znhevf aba yvthyn cryyragrfdhr hygevprf cunfryyhf vq fncvra va fncvra vnphyvf pbathr ivinzhf zrghf neph nqvcvfpvat zbyrfgvr uraqerevg ng ihychgngr ivgnr avfy nrarna yrpghf cryyragrfdhr rtrg ahap qbarp dhvf bepv rtrg bepv iruvphyn pbaqvzraghz phenovghe va",
                        "https://robohash.org/commodiabet.png?size=2000x2000&set=set1",
                        2)
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
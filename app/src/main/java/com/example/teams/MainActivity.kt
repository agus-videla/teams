package com.example.teams

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.teams.ui.screens.ScreenRoute
import com.example.teams.ui.screens.candidates.CandidatesScreen
import com.example.teams.ui.screens.candidates.CandidatesViewModel
import com.example.teams.ui.screens.candidateDetail.CandidateDetailScreen
import com.example.teams.ui.screens.shared.CandidatesViewModelFactory

class MainActivity : ComponentActivity() {
    private val candidatesViewModel: CandidatesViewModel by viewModels {
        CandidatesViewModelFactory((application as TeamsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = ScreenRoute.CandidatesScreen.route) {
                composable(ScreenRoute.CandidatesScreen.route) {
                    CandidatesScreen(candidatesViewModel, navController)
                }
                composable(
                    route = ScreenRoute.DetailScreen.route + "/{id}",
                    arguments = listOf(
                        navArgument("id") { type = NavType.IntType }
                    )
                ) { entry ->
                    val id = entry.arguments?.getInt("id")
                    requireNotNull(id) { "Detail Requires ID" }
                    CandidateDetailScreen(id)
                }
            }
        }
    }
}
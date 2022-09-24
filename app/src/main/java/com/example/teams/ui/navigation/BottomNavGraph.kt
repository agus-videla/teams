package com.example.teams.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.teams.ui.screens.candidateDetail.CandidateDetailScreen
import com.example.teams.ui.screens.candidateDetail.CandidateDetailViewModel
import com.example.teams.ui.screens.candidates.CandidatesScreen
import com.example.teams.ui.screens.candidates.CandidatesViewModel
import com.example.teams.ui.screens.teams.TeamsScreen
import com.example.teams.ui.screens.teams.TeamsViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.TeamsScreen.route) {
        composable(ScreenRoute.TeamsScreen.route) {
            TeamsScreen()
        }
        composable(ScreenRoute.CandidatesScreen.route) {
            CandidatesScreen(navController)
        }
        composable(
            route = ScreenRoute.CandidateDetailScreen.route + "/{id}",
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
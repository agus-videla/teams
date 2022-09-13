package com.example.teams.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.teams.ui.screens.candidateDetail.CandidateDetailScreen
import com.example.teams.ui.screens.candidateDetail.CandidateDetailViewModel
import com.example.teams.ui.screens.candidates.CandidatesScreen
import com.example.teams.ui.screens.candidates.CandidatesViewModel
import com.example.teams.ui.screens.teams.TeamsScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    candidatesViewModel: CandidatesViewModel,
    candidateDetailViewModel: CandidateDetailViewModel
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.CandidatesScreen.route) {
        composable(ScreenRoute.CandidatesScreen.route) {
            CandidatesScreen(candidatesViewModel, navController)
        }
        composable(ScreenRoute.TeamsScreen.route) {
            TeamsScreen()
        }
        composable(
            route = ScreenRoute.DetailScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id")
            requireNotNull(id) { "Detail Requires ID" }
            CandidateDetailScreen(candidateDetailViewModel, id)
        }
    }
}
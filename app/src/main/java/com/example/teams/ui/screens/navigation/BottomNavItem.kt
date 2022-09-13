package com.example.teams.ui.screens.navigation

import com.example.teams.R

sealed class BottomNavItem(val route: String, val icon: Int, val title: String) {
    object CandidatesScreen: BottomNavItem(
        route = ScreenRoute.CandidatesScreen.route,
        icon = R.drawable.candidate,
        title = "Candidate"
    )
    object TeamsScreen: BottomNavItem(
        route = ScreenRoute.TeamsScreen.route,
        icon = R.drawable.teams,
        title = "Teams"
    )
}

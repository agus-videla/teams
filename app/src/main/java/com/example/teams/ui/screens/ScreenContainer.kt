package com.example.teams.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.teams.ui.screens.candidateDetail.CandidateDetailViewModel
import com.example.teams.ui.screens.candidates.CandidatesViewModel
import com.example.teams.ui.navigation.NavGraph
import com.example.teams.ui.navigation.BottomNavItem
import com.example.teams.ui.screens.teams.TeamsViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenContainer(
    navController: NavHostController
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        NavGraph(navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.TeamsScreen,
        BottomNavItem.CandidatesScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = items.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation {
            items.forEach { item ->
                AddItem(item, currentDestination, navController)
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    item: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        label = {
            Text(text = item.title)
        },
        icon = {
          Icon(
              painter = painterResource(item.icon),
              contentDescription = "Navigation Icon"
          )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == item.route
        } == true,
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
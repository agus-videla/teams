package com.example.teams.ui.screens.candidates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.teams.data.database.entities.Candidate
import com.example.teams.data.database.entities.Team
import com.example.teams.ui.screens.candidates.UIState.Loading


@Composable
fun CandidatesScreen(navController: NavHostController, paddingValues: PaddingValues) {
    val viewModel = viewModel(CandidatesViewModel::class.java)
    val teams = viewModel.teams
    val uiState = viewModel.uiState.value
    when (uiState) {
        is Loading -> Loading()
        is UIState.Success -> CandidateList(uiState.data,
            teams.value,
            paddingValues,
            navController
        ) { id, teamid ->
            viewModel.updateTeam(id, teamid)
        }
    }
}

@Composable
fun CandidateList(
    candidates: List<Candidate>,
    teams: List<Team>,
    paddingValues: PaddingValues,
    navController: NavHostController,
    onUpdate: (Int, Int) -> Unit,
) {
    val isVisible = remember { mutableStateOf(false) }
    val selectedId = remember {
        mutableStateOf(-1)
    }
    LazyColumn(
        Modifier.padding(paddingValues)
    ) {
        itemsIndexed(candidates) { _, candidate ->
            CandidateCard(candidate = candidate, navController) { id ->
                isVisible.value = !isVisible.value
                selectedId.value = id
            }
        }
    }
    PopUp(isVisible.value, teams,
        { teamId ->
            onUpdate(selectedId.value, teamId)
        },
        {
            isVisible.value = false
        })
}

@Composable
fun Loading() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun PopUp(
    isVisible: Boolean,
    teams: List<Team>,
    onClick: (teamId: Int) -> Unit,
    onOutOfCard: () -> Unit,
) {
    if (isVisible) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onOutOfCard() }
        ) {
            Card(
                modifier = Modifier
                    .border(width = 1.dp,
                        color = Color.Blue)
                    .width(400.dp)
                    .background(Color.White)
                    .height(300.dp)
                    .padding(5.dp)
                    .clickable(enabled = false) {}

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Select Team",
                        style = MaterialTheme.typography.h3)
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(teams) { team ->
                            Card(
                                modifier = Modifier
                                    .border(width = 1.dp,
                                        color = Color.Blue)
                                    .fillMaxWidth()
                                    .clickable {
                                        onClick(team.id)
                                        onOutOfCard()
                                    }
                            ) {
                                Column {
                                    Text(
                                        text = team.name,
                                        style = MaterialTheme.typography.h5
                                    )
                                    Text(
                                        text = team.description,
                                        style = MaterialTheme.typography.h6,
                                        fontStyle = FontStyle.Italic
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
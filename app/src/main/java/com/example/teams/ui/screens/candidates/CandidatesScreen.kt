package com.example.teams.ui.screens.candidates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
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
import com.example.teams.data.database.entities.Team


@Composable
fun CandidatesScreen(navController: NavHostController) {
    val viewModel = viewModel(CandidatesViewModel::class.java)
    val candidates = viewModel.candidates
    val teams = viewModel.teams
    val isVisible = remember { mutableStateOf(false) }
    val selectedId = remember {
        mutableStateOf(-1)
    }
    LazyColumn(
    ) {
        itemsIndexed(candidates.value) { _, candidate ->
            CandidateCard(candidate = candidate, navController) { id ->
                isVisible.value = !isVisible.value
                selectedId.value = id
            }
        }
    }
    PopUp(isVisible.value, teams,
    { teamId ->
        viewModel.updateTeam(selectedId.value, teamId)
    },
    {
        isVisible.value = false
    })
}

@Composable
fun PopUp(
    isVisible: Boolean,
    teams: State<List<Team>>,
    onClick: (teamId: Int) -> Unit,
    onOutOfCard: () -> Unit
) {
    if (isVisible) {
        Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
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
                        items(teams.value) { team ->
                            Card(
                                modifier = Modifier
                                    .border(width = 1.dp,
                                        color = Color.Blue)
                                    .fillMaxWidth()
                                    .clickable {
                                        onClick(team.id)
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
package com.example.teams.ui.screens.candidates

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.teams.data.model.entities.Team


@Composable
fun CandidatesScreen(viewModel: CandidatesViewModel, navController: NavHostController) {
    val candidates = viewModel.candidates
    val teams = viewModel.teams
    val isVisible = remember { mutableStateOf(false) }
    LazyColumn {
        itemsIndexed(candidates.value) { _, candidate ->
            CandidateCard(candidate = candidate, navController) {
                isVisible.value = !isVisible.value
            }
        }
    }
    PopUp(isVisible.value, teams)
}

@Composable
fun PopUp(isVisible: Boolean, teams: State<List<Team>>) {
    if (isVisible) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .border(width = 1.dp,
                        color = Color.Blue)
                    .width(400.dp)
                    .background(Color.White)
                    .height(300.dp)
                    .padding(5.dp)
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
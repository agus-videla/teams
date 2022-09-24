package com.example.teams.ui.screens.teams

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teams.R
import com.example.teams.data.model.entities.Candidate
import com.example.teams.data.model.entities.Team
import com.example.teams.ui.screens.candidates.ProfilePicture

@Composable
fun TeamsScreen() {
    val viewModel = viewModel(TeamsViewModel::class.java)
    val state = viewModel.state
    LazyColumn(
        modifier = Modifier.padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(state.value) { team ->
            TeamCard(team = team.team, candidates = team.candidates ?: emptyList())
        }
    }
}

@Composable
fun TeamCard(team: Team, candidates: List<Candidate>) {
    Card(modifier = Modifier.border(width = 1.dp,
        shape = RoundedCornerShape(2),
        color = Color.Blue)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = team.name, style = MaterialTheme.typography.h6)
                    Text(
                        text = team.description,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = "Members: ${candidates.size}/${team.maxMembers}"
                    )
                }
            }
            LazyRow {
                items(candidates) { member ->
                    Card {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProfilePicture(pp = member.profilePicture, height = 50)
                            Text(
                                text = "${member.firstName} ${member.lastName}",
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                }
            }
        }
    }
}

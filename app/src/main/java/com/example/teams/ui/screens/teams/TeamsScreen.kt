package com.example.teams.ui.screens.teams

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import com.example.teams.data.model.entities.Candidate
import com.example.teams.data.model.entities.Team

@Composable
fun TeamsScreen(viewModel: TeamsViewModel) {
    val state = viewModel.state.collectAsState()
    LazyColumn {
        itemsIndexed(state.value) { _, team ->
            TeamCard(team = team.team, candidates = emptyList() ?: emptyList())
        }
    }
}

@Composable
fun TeamCard(team: Team, candidates: List<Candidate>) {
    Column {
        Text(
            text = "Name: ${team.name}"
        )
        Text(
            text = team.description,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "Members: 2/${team.maxMembers}"
        )
        Row {

        }
    }
}

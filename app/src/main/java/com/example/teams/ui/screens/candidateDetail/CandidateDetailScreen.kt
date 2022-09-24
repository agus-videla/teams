package com.example.teams.ui.screens.candidateDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CandidateDetailScreen(idCandidate: Int) {
    val viewModel = viewModel(
        CandidateDetailViewModel::class.java,
        factory = CandidateDetailViewModelFactory(idCandidate)
    )
    val state = viewModel.state.collectAsState()
    val candidate = state.value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Text(style = MaterialTheme.typography.h6,
                text = "Name: ${candidate.firstName} ${candidate.lastName}")
            Text(style = MaterialTheme.typography.h6,
                text = "Job: ${candidate.jobTitle}")
            Text(style = MaterialTheme.typography.h6,
                text = "Catchphrase: ${candidate.quote}")
            Text(style = MaterialTheme.typography.h6,
                text = "Age: ${candidate.age}")
            Text(style = MaterialTheme.typography.h6,
                text = "Bio: ${candidate.bio}")

        }
    }
}
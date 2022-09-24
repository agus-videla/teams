package com.example.teams

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.teams.ui.screens.ScreenContainer
import com.example.teams.ui.screens.candidates.CandidatesViewModel
import com.example.teams.ui.screens.candidateDetail.CandidateDetailViewModel
import com.example.teams.ui.screens.shared.ViewModelFactory
import com.example.teams.ui.screens.teams.TeamsViewModel

class MainActivity : ComponentActivity() {
    private val candidatesViewModel: CandidatesViewModel by viewModels {
        ViewModelFactory((application as TeamsApplication).repository)
    }
    private val candidateDetailViewModel: CandidateDetailViewModel by viewModels {
        ViewModelFactory((application as TeamsApplication).repository)
    }
    private val teamsViewModel: TeamsViewModel by viewModels {
        ViewModelFactory((application as TeamsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenContainer(
                rememberNavController(),
                candidatesViewModel,
                candidateDetailViewModel,
                teamsViewModel
            )
        }
    }
}
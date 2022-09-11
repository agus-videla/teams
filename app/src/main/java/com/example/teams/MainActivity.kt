package com.example.teams

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.teams.ui.screens.candidates.CandidatesScreen
import com.example.teams.ui.screens.candidates.CandidatesViewModel
import com.example.teams.ui.screens.shared.CandidatesViewModelFactory

class MainActivity : ComponentActivity() {
    private val candidatesViewModel: CandidatesViewModel by viewModels {
        CandidatesViewModelFactory((application as TeamsApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CandidatesScreen(candidatesViewModel)
        }
    }
}
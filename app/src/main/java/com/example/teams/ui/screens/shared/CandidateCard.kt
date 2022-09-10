package com.example.teams.ui.screens.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.teams.R
import com.example.teams.data.model.entities.Candidate

data class MockCandidate(
    val pp: Int = R.drawable.profile_picture,
    val name: String = "John",
    val surname: String = "Salchichon",
    val title: String = "Computer Engineer",
    val quote: String = "When life gives you lemons make lemonade",
)

@Composable
fun CandidateCard(candidate: Candidate) {
    Row() {
        ProfilePicture(R.drawable.profile_picture)
        BasicInfo(candidate.firstName, candidate.lastName, candidate.jobTitle, candidate.quote)
    }
}

@Composable
fun BasicInfo(name: String,
              surname: String,
              title: String,
              quote: String
) {
    Column() {
        Row {
            Text(name)
            Spacer(modifier = Modifier.width(2.dp))
            Text(surname)
        }
        Text(title)
        Text(
            text = quote,
            fontStyle = FontStyle.Italic
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeCard() {
    Row() {
        ProfilePicture(R.drawable.profile_picture)
        BasicInfo(
            "John",
            "Salchichon",
            "Computer Engineer",
            "When life gives you lemons make lemonade"
        )
    }
}

@Composable
fun ProfilePicture(pp: Int) {
    Image(
        painter = painterResource(pp),
        contentDescription = "profile"
    )
}

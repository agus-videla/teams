package com.example.teams.ui.screens.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.teams.R
import com.example.teams.data.model.entities.Candidate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.teams.ui.navigation.ScreenRoute

@Composable
fun CandidateCard(candidate: Candidate, navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        ProfilePicture(R.drawable.profile_picture,100)
        Column(
            modifier = Modifier
            .fillMaxWidth()
            .padding(end = 5.dp)
        ) {
            BasicInfo(
                name = candidate.firstName,
                surname = candidate.lastName,
                title = candidate.jobTitle,
                quote = candidate.quote,
            )
            Spacer(modifier = Modifier.width(2.dp))
            Button(modifier = Modifier.align(Alignment.End), onClick = {
                navController.navigate(ScreenRoute.CandidateDetailScreen.withArgs(candidate.id))
            }) {
                Text("Button")
            }
        }
    }
}

@Composable
fun BasicInfo(name: String, surname: String, title: String, quote: String) {
    Column {
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
    Row(
        horizontalArrangement = Arrangement.End
    ){
        ProfilePicture(R.drawable.profile_picture,100)
        Column(
            modifier = Modifier.padding(end = 5.dp)
        ) {
            BasicInfo(
                "John",
                "Salchichon",
                "Computer Engineer",
                "When life gives you lemons make lemonade"
            )
            Spacer(modifier = Modifier.width(2.dp))
            Button( onClick = { /*TODO*/ }) {
                Text("Button")
            }
        }
    }
}

@Composable
fun ProfilePicture(pp: Int,height: Int) {
    Image(
        modifier = Modifier.height(height.dp),
        painter = painterResource(pp),
        contentDescription = "profile"
    )
}

package com.example.teams.ui.screens.candidates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.teams.R
import com.example.teams.data.model.entities.Candidate
import com.example.teams.ui.navigation.ScreenRoute

@Composable
fun CandidateCard(
    candidate: Candidate,
    navController: NavHostController,
    onClick: (id: Int) -> Unit,
) {
    Card(
        Modifier.clickable {
            navController.navigate(ScreenRoute.CandidateDetailScreen.withArgs(candidate.id))
        }
    ) {
        Row {
            ProfilePicture(candidate.profilePicture, 80)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(end = 5.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                BasicInfo(
                    name = candidate.firstName,
                    surname = candidate.lastName,
                    title = candidate.jobTitle,
                    quote = candidate.quote,
                )
                Button(
                    modifier = Modifier.align(Alignment.Start),
                    onClick = { onClick(candidate.id) }
                ) {
                    Text(text = "Add to Team")
                }
            }
        }
    }
}

@Composable
fun BasicInfo(name: String, surname: String, title: String, quote: String) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
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

@Composable
fun ProfilePicture(pp: String, height: Int) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(pp)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.profile_picture),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(height.dp)
            .clip(CircleShape)
    )
}

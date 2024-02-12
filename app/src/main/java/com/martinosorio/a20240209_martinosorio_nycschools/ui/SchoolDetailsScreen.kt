package com.martinosorio.a20240209_martinosorio_nycschools.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.martinosorio.a20240209_martinosorio_nycschools.SchoolsListViewModel
import com.martinosorio.a20240209_martinosorio_nycschools.api.model.School
import com.martinosorio.a20240209_martinosorio_nycschools.api.model.Score

@Composable
fun SchoolDetailsScreen(viewModel: SchoolsListViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val schoolsUiState = viewModel.schoolsUiState.collectAsState().value
        val scoresUiState = viewModel.scoresUiState.collectAsState().value

        when (scoresUiState.status) {
            Status.SUCCESS -> {
                if (schoolsUiState.data != null && scoresUiState.data != null) {
                    SchoolDetails(school = schoolsUiState.data[0], score = scoresUiState.data[0])
                } else {
                    // TODO: Needed? Or handle differently?
                    ShowError(message = "Oops, data is bad!")
                }
            }

            Status.ERROR -> {
                ShowError(message = scoresUiState.message)
            }

            Status.LOADING -> {
                ShowLoading()
            }
        }
    }
}

@Composable
fun SchoolDetails(school: School, score: Score) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, top = 6.dp, end = 10.dp, bottom = 6.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF8850a4))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 12.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                text = school.schoolName.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = "Phone: ${school.phoneNumber}"
            )

            // TODO: Sometimes is null...
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = "Email: ${school.schoolEmail}"
            )

            Text(
                text = school.location.toString()
            )
        }
    }
}
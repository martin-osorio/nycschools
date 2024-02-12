package com.martinosorio.a20240209_martinosorio_nycschools.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavController
import com.martinosorio.a20240209_martinosorio_nycschools.SchoolsViewModel
import com.martinosorio.a20240209_martinosorio_nycschools.api.model.School

@Composable
fun SchoolsScreen(navController: NavController, viewModel: SchoolsViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val schoolsUiState = viewModel.schoolsUiState.collectAsState().value

        when (schoolsUiState.status) {
            Status.SUCCESS -> {
                if (schoolsUiState.data != null) {
                    // TODO: Improve so we don't have to keep passing these two around...?
                    ShowSchoolsList(navController = navController, viewModel = viewModel, schools = schoolsUiState.data)
                } else {
                    // TODO: Needed? Or handle differently?
                    ShowError(message = "Oops, data is bad!")
                }
            }

            Status.ERROR -> {
                ShowError(message = schoolsUiState.message)
            }

            Status.LOADING -> {
                ShowLoading()
            }
        }
    }
}

@Composable
fun ShowSchoolsList(navController: NavController, viewModel: SchoolsViewModel, schools: List<School>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // Title
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 30.dp),
                textAlign = TextAlign.Center,
                text = "NYC Schools",
                style = MaterialTheme.typography.headlineLarge
            )
        }

        // List of schools
        items(schools.size) { index ->
            School(navController = navController, viewModel = viewModel, school = schools[index])
        }
    }
}

@Composable
fun School(navController: NavController, viewModel: SchoolsViewModel, school: School) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, top = 6.dp, end = 10.dp, bottom = 6.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF8850a4))
            .clickable {
                // TODO: Can this be improved?
                school.dbn?.let { viewModel.navigateToSchoolDetails(it, navController) }
            }
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
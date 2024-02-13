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
fun SchoolListScreen(navController: NavController, viewModel: SchoolsViewModel) {
    /*
        The root of this composable.
        Checks the UiState to determine whether to show loading, error, or the list of schools.
     */

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val schoolsUiState = viewModel.schoolsUiState.collectAsState().value

        when (schoolsUiState.status) {
            Status.SUCCESS -> {
                if (schoolsUiState.data != null) {
                    // When we have a successful and valid response, show the list
                    SchoolsList(navController = navController, viewModel = viewModel, schools = schoolsUiState.data)
                } else {
                    ErrorScreen(message = "Oops, data is bad!")
                }
            }

            Status.ERROR -> {
                ErrorScreen(message = schoolsUiState.message)
            }

            Status.LOADING -> {
                // Since loading is the default UiState, this screen always shows loading until API calls have completed
                LoadingScreen()
            }
        }
    }
}

@Composable
fun SchoolsList(navController: NavController, viewModel: SchoolsViewModel, schools: List<School>) {
    /*
        The main display for this screen, a list of schools provided by the API
     */

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
    /*
        The tile for a given school with some info, and click navigation to details screen

        Not all fields are guaranteed to be present. Given more time,
        I would improve this by hiding fields for which the value is missing.
     */

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, top = 6.dp, end = 10.dp, bottom = 6.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF8850a4))
            .clickable {
                /*
                    Given more time, I would improve the navigation by bundling the DBN in the navigation,
                    this would simplify the line bellow to: navController.navigate(...)
                    This avoids needing the viewModel here.
                 */
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
                modifier = Modifier.padding(bottom = 4.dp),
                text = "Phone: ${school.phoneNumber}"
            )

            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = "Email: ${school.schoolEmail}"
            )

            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = "Website: ${school.website}"
            )

            Text(
                text = "Located in: ${school.borough}"
            )
        }
    }
}
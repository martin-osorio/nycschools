package com.martinosorio.a20240209_martinosorio_nycschools.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.martinosorio.a20240209_martinosorio_nycschools.SchoolsViewModel

@Composable
fun SchoolDetailsScreen(viewModel: SchoolsViewModel) {
    /*
        This is the screen that displays the details for a specific school once the user taps it in the list of schools.

        Given more time, this screen would be improved by:
        1.  If the getScores API fails, this screen will work but some data would be missing.
            Improve it by re-attempting the call, adding pull-to-refresh, and/or improve error messaging.
        2.  Not all fields are guaranteed to be present. Improve by hiding fields for which the value is missing.
        3.  Add intent to launch website when the user taps the website URL
     */

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val school = viewModel.getTargetSchool()
        val score = viewModel.getScore()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF8850a4))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp),
                    text = school.schoolName.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = "Phone: ${school.phoneNumber}"
                )

                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = "Email: ${school.schoolEmail}"
                )

                // Given more time, add click behavior that takes user to that website
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = "Website: ${school.website}"
                )

                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = "Located at: ${school.location}"
                )

                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = "Extracurricular activities offered:\n${school.extracurricularActivities}"
                )

                Text(
                    modifier = Modifier.padding(bottom = 30.dp),
                    text = "ELL programs offered:\n${school.ellPrograms}"
                )

                if (score != null && score.dbn != null && score.dbn!!.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp, end = 50.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(width = 2.dp, color = LightGray, shape = RoundedCornerShape(8.dp))
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 6.dp, bottom = 1.dp),
                                text = "Average SAT Scores",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge
                            )

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                text = "Out of ${score.numOfSatTestTakers} students",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleSmall
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 2.dp, start = 50.dp, end = 50.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(
                                    text = "Reading:",
                                    textAlign = TextAlign.Start,
                                )

                                Text(
                                    text = "${score.readingAvgScore}",
                                    textAlign = TextAlign.End,
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 2.dp, start = 50.dp, end = 50.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(
                                    text = "Writing:",
                                    textAlign = TextAlign.Start,
                                )

                                Text(
                                    text = "${score.writingAvgScore}",
                                    textAlign = TextAlign.End,
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp, start = 50.dp, end = 50.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(
                                    text = "Math:",
                                    textAlign = TextAlign.Start,
                                )

                                Text(
                                    text = "${score.mathAvgScore}",
                                    textAlign = TextAlign.End,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
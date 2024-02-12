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
import androidx.compose.foundation.shape.RoundedCornerShape
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
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val school = viewModel.getSchool()
        val score = viewModel.getScore()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
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
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = school.location.toString()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth().padding(start = 50.dp, end = 50.dp)
                ){
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
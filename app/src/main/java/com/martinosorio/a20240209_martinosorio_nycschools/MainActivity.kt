package com.martinosorio.a20240209_martinosorio_nycschools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martinosorio.a20240209_martinosorio_nycschools.ui.SchoolDetailsScreen
import com.martinosorio.a20240209_martinosorio_nycschools.ui.SchoolsScreen
import com.martinosorio.a20240209_martinosorio_nycschools.ui.theme.SchoolsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val schoolsViewModel: SchoolsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SchoolsTheme {
                // TODO: Can this be injected?
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavDestinations.SchoolListScreen.destination
                    ) {
                        composable(NavDestinations.SchoolListScreen.destination) {
                            SchoolsScreen(navController = navController, viewModel = schoolsViewModel)
                        }

                        composable(NavDestinations.SchoolDetailsScreen.destination) {
                            SchoolDetailsScreen(viewModel = schoolsViewModel)
                        }
                    }
                }
            }
        }
    }
}
package com.martinosorio.a20240209_martinosorio_nycschools

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.martinosorio.a20240209_martinosorio_nycschools.ui.Status
import com.martinosorio.a20240209_martinosorio_nycschools.ui.theme._20240209MartinOsorioNYCSchoolsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val schoolsListViewModel: SchoolsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        schoolsListViewModel.schools.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("moltag", "onCreate: observed schools success")
                }

                Status.ERROR -> {
                    Log.d("moltag", "onCreate: observed schools error")
                }

                Status.LOADING -> {
                    Log.d("moltag", "onCreate: observed schools loading")
                }
            }
        }

        setContent {
            _20240209MartinOsorioNYCSchoolsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _20240209MartinOsorioNYCSchoolsTheme {
        Greeting("Android")
    }
}
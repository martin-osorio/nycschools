package com.martinosorio.a20240209_martinosorio_nycschools

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.martinosorio.a20240209_martinosorio_nycschools.api.SchoolsRepository
import com.martinosorio.a20240209_martinosorio_nycschools.api.model.School
import com.martinosorio.a20240209_martinosorio_nycschools.api.model.Score
import com.martinosorio.a20240209_martinosorio_nycschools.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolsViewModel @Inject constructor(private val repository: SchoolsRepository) : ViewModel() {
    private val schoolsUiStateFlow = MutableStateFlow<UiState<List<School>>>(UiState.loading(null))
    val schoolsUiState: StateFlow<UiState<List<School>>> = schoolsUiStateFlow

    private val scoresUiStateFlow = MutableStateFlow<UiState<List<Score>>>(UiState.loading(null))
    val scoresUiState: StateFlow<UiState<List<Score>>> = scoresUiStateFlow

    private var targetDbn = ""

    init {
        getSchools()
        getScores()
    }

    private fun getSchools() = viewModelScope.launch {
        schoolsUiStateFlow.value = UiState.loading(null)

        try {
            repository.getSchools().let {
                if (it.isSuccessful) {
                    schoolsUiStateFlow.value = UiState.success(it.body())
                    Log.d("moltag", "getSchools: (${it.body()?.size}) \n${it.body()}")
                } else {
                    schoolsUiStateFlow.value = UiState.error(it.body().toString(), null)
                }
            }
        } catch (e: Exception) {
            schoolsUiStateFlow.value = UiState.error(e.toString(), null)
        }
    }

    private fun getScores() = viewModelScope.launch {
        scoresUiStateFlow.value = UiState.loading(null)

        try {
            repository.getScores().let {
                if (it.isSuccessful) {
                    scoresUiStateFlow.value = UiState.success(it.body())
                    Log.d("moltag", "getScores: (${it.body()?.size}) \n${it.body()}")
                } else {
                    scoresUiStateFlow.value = UiState.error(it.body().toString(), null)
                }
            }
        } catch (e: Exception) {
            schoolsUiStateFlow.value = UiState.error(e.toString(), null)
        }
    }

    fun navigateToSchoolDetails(dbn: String, navController: NavController) {
        targetDbn = dbn
        navController.navigate(NavDestinations.SchoolDetailsScreen.destination)
    }

    private fun getSchool(dbn: String): School {
        if (schoolsUiState.value.data != null) {
            schoolsUiState.value.data!!.forEach {
                if (it.dbn == dbn) {
                    return it
                }
            }
        }

        return School()
    }

    fun getSchool(): School {
        return getSchool(targetDbn)
    }

    private fun getScore(dbn: String): Score {
        if (scoresUiState.value.data != null) {
            scoresUiState.value.data!!.forEach {
                if (it.dbn == dbn) {
                    return it
                }
            }
        }

        return Score()
    }

    fun getScore(): Score {
        return getScore(targetDbn)
    }
}
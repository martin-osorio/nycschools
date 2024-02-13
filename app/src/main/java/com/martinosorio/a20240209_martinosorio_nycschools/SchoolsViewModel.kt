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
    /*
        ViewModel for our MainActivity and both our SchoolListScreen and SchoolDetailsScreen.

        I used MutableStateFlow as I tend to prefer it overall.
        However, LiveData could be a simpler and better solution for a simple state like this.

        Given more time I would:
        1.  Improve state change handling. Currently I have prevented this activity from handling its state changes.
        2.  Improve networking. Currently, both our API calls are performed on initialization.
            The UI state depends on the result of the getSchools call only.
            If the getScores call fails, the UI will not show the error, and simply hide those fields.
        3.  When the user taps a school, this viewModel remembers which school it is,
            then the details screen asks this viewModel for the relevant school and scores based on it.
            Improve this by not storing the target at all, but including it in the navigation.
     */

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

    private fun getTargetSchoolByDBN(dbn: String): School {
        if (schoolsUiState.value.data != null) {
            schoolsUiState.value.data!!.forEach {
                if (it.dbn == dbn) {
                    return it
                }
            }
        }

        return School()
    }

    fun getTargetSchool(): School {
        return getTargetSchoolByDBN(targetDbn)
    }

    private fun getScoreByDBN(dbn: String): Score {
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
        return getScoreByDBN(targetDbn)
    }

    fun navigateToSchoolDetails(dbn: String, navController: NavController) {
        targetDbn = dbn
        navController.navigate(NavDestinations.SchoolDetailsScreen.destination)
    }
}
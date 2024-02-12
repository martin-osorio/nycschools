package com.martinosorio.a20240209_martinosorio_nycschools

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class SchoolsListViewModel @Inject constructor(private val repository: SchoolsRepository) : ViewModel() {
    private val schoolsUiStateFlow = MutableStateFlow<UiState<List<School>>>(UiState.loading(null))
    val schoolsUiState: StateFlow<UiState<List<School>>> = schoolsUiStateFlow

    private val scoresUiStateFlow = MutableStateFlow<UiState<List<Score>>>(UiState.loading(null))
    val scoresUiState: StateFlow<UiState<List<Score>>> = scoresUiStateFlow

    init {
        getSchools()
        getScores()
    }

    private fun getSchools() = viewModelScope.launch {
        schoolsUiStateFlow.value = UiState.loading(null)

        repository.getSchools().let {
            if (it.isSuccessful) {
                schoolsUiStateFlow.value = UiState.success(it.body())
            } else {
                schoolsUiStateFlow.value = UiState.error(it.body().toString(), null)
            }
        }
    }

    private fun getScores() = viewModelScope.launch {
        scoresUiStateFlow.value = UiState.loading(null)

        repository.getScores().let {
            if (it.isSuccessful) {
                scoresUiStateFlow.value = UiState.success(it.body())
            } else {
                scoresUiStateFlow.value = UiState.error(it.body().toString(), null)
            }
        }
    }

    public fun navigateToSchoolDetails(dbn: String) {
        // TODO: Can this be optimized?

        var school = School()
        if (schoolsUiState.value.data != null) {
            schoolsUiState.value.data!!.forEach {
                if (it.dbn == dbn) {
                    school = it
                }
            }
        }

        var score = Score()
        if (scoresUiState.value.data != null) {
            scoresUiState.value.data!!.forEach {
                if (it.dbn == dbn) {
                    score = it
                }
            }
        }

        if (school.dbn != null && score.dbn != null && school.dbn == score.dbn) {
            // TODO: Navigate
            Log.d("moltag", "navigateToSchoolDetails: navigate")
        }
    }
}
package com.martinosorio.a20240209_martinosorio_nycschools

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martinosorio.a20240209_martinosorio_nycschools.api.SchoolsRepository
import com.martinosorio.a20240209_martinosorio_nycschools.api.model.School
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

    init {
        getSchools()
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
}
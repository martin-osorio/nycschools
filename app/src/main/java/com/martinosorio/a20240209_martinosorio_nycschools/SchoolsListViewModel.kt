package com.martinosorio.a20240209_martinosorio_nycschools

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolsListViewModel @Inject constructor(private val repository: SchoolsRepository) : ViewModel() {
    private val _schools = MutableLiveData<UiState<String>>()
    val schools : LiveData<UiState<String>>
        get() = _schools

    init {
        getSchools()
    }

    private fun getSchools() = viewModelScope.launch {
        _schools.postValue(UiState.loading(null))

        repository.getSchools().let {
            if (it.isSuccessful){
                _schools.postValue(UiState.success(it.body()))
            } else {
                _schools.postValue(UiState.error(it.body().toString(), null))
            }
        }
    }
}
package com.martinosorio.a20240209_martinosorio_nycschools.ui

/*
    Simple UiState object. Used to pass both the result of the API calls and the response if successfully
 */
class UiState<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): UiState<T> {
            return UiState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): UiState<T> {
            return UiState(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): UiState<T> {
            return UiState(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
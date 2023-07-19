package com.tafreshiali.data_state

sealed interface DataState<out T> {

    data class Error(
        val errorMessage: String
    ) : DataState<Nothing>

    data class Data<T>(
        val data: T? = null
    ) : DataState<T>

    object Loading : DataState<Nothing>
}
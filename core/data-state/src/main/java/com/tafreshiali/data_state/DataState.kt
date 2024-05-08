package com.tafreshiali.data_state

sealed interface DataState<out T> {
    data class Error(val errorMessage: String) : DataState<Nothing>
    data class Data<T>(val data: T) : DataState<T>
    data object Loading : DataState<Nothing>
}
package com.tafreshiali.app_state_manager

sealed class LoadingState {
    data object Loading : LoadingState()
    data object Idle : LoadingState()
}
package com.tafreshiali.app_state_manager

sealed class LoadingState {
    object Loading : LoadingState()
    object Idle : LoadingState()
}
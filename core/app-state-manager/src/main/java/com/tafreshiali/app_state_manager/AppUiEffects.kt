package com.tafreshiali.app_state_manager

sealed class AppUiEffects: BaseUiEffects {
    data class UpdateLoadingState(val state: Boolean) : AppUiEffects()
    data class UpdateErrorState(val state: Boolean) : AppUiEffects()
    data class ShowSnackBar(val message: String) : AppUiEffects()
}





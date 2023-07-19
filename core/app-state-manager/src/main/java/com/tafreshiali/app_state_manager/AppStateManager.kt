package com.tafreshiali.app_state_manager

import com.tafreshiali.coroutines_dispatchers.qualifier.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppStateManager @Inject constructor(@ApplicationScope private val applicationScope: CoroutineScope) {

    private val _appUiEffects = MutableSharedFlow<BaseUiEffects>()
    val appUiEffects = _appUiEffects.asSharedFlow()

    fun emitSuspendAppUiEffect(uiEffect: BaseUiEffects) {
        applicationScope.launch {
            _appUiEffects.emit(value = uiEffect)
        }
    }

    fun emitAppEvent(event: BaseUiEffects) {
        _appUiEffects.tryEmit(value = event)
    }


}
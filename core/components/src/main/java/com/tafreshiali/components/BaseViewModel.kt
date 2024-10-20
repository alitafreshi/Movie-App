package com.tafreshiali.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * GOAL = TO HAVE GENERIC PATTERN THAT CONTAINS ALL SCENARIOS
 * @param [ViewState] [Events] [UiEffects] these are generic unique types for each screen
 * also all the related states for the whole app handles here (like canceling requests / retrying requests etc) */

abstract class BaseViewModel<ViewState, Events, UiEffects> :
    ViewModel() {

    private val initialViewState: ViewState by lazy { initNewViewState() }

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(initialViewState)
    val viewState = _viewState.asStateFlow()

    private val _uiEffects = MutableSharedFlow<UiEffects>()
    private val uiEffects = _uiEffects.asSharedFlow()

    protected fun getCurrentViewStateOrNew(): ViewState = viewState.value

    fun setViewState(viewState: ViewState) {
        _viewState.value = viewState
    }

    fun observeEffects(): SharedFlow<UiEffects> = uiEffects

    fun emitSuspendEffect(effect: UiEffects) {
        viewModelScope.launch {
            _uiEffects.emit(value = effect)
        }
    }

    abstract fun onTriggerEvent(event: Events)

    abstract fun initNewViewState(): ViewState
}

package com.tafreshiali.presentation

import com.tafreshiali.app_state_manager.LoadingState
import com.tafreshiali.domain.model.Result

data class HomeViewState(
    val bannerUrl: String = "",
    val trendingMovies: List<Result> = emptyList(),
    val loadingState: LoadingState = LoadingState.Idle
)
package com.tafreshiali.presentation

import com.tafreshiali.app_state_manager.LoadingState
import com.tafreshiali.domain.model.intro.MovieIntroItem

data class HomeViewState(
    val sliderImageList: List<MovieIntroItem> = emptyList(),
    val trendingMovies: List<MovieIntroItem> = emptyList(),
    val loadingState: LoadingState = LoadingState.Idle
)
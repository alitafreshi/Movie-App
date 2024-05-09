package com.tafreshiali.presentation

import com.tafreshiali.domain.model.intro.MovieIntroItem

data class HomeViewState(
    val homeState: HomeState = HomeState.Loading,
    val sliderImageList: List<MovieIntroItem> = emptyList(),
    val trendingMovies: List<MovieIntroItem> = emptyList(),
    val topRatedMovies: List<MovieIntroItem> = emptyList(),
) {
    sealed interface HomeState {
        data object Idle : HomeState
        data object Loading : HomeState
        data class Error(val errorMessage: String) : HomeState
    }
}
package com.tafreshiali.presentation

sealed class HomeEvents {
    data object GetTrendingMovies : HomeEvents()
    data object OnRetryClick : HomeEvents()
}

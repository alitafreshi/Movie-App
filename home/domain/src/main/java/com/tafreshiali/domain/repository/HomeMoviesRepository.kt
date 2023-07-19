package com.tafreshiali.domain.repository

import com.tafreshiali.domain.model.TrendingMoviesResponse

interface HomeMoviesRepository {
    suspend fun getTrendingMovies(
        type: String = "movie",
        time: String = "day"
    ): TrendingMoviesResponse
}
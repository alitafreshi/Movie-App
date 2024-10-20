package com.tafreshiali.domain.repository

import com.tafreshiali.domain.model.intro.BaseMovieIntro

interface HomeMoviesRepository {
    suspend fun getTrendingMovies(type: String = "movie", time: String = "day"): BaseMovieIntro
    suspend fun getTopRatedMovies(page: String = "1"): BaseMovieIntro
}
package com.tafreshiali.domain.model.intro

data class BaseMovieIntro(
    val dates: UpComingMoviesDate?,
    val page: Int?,
    val moviesIntro: List<MovieIntroItem>,
    val totalPages: Int?,
    val totalResults: Int?
)
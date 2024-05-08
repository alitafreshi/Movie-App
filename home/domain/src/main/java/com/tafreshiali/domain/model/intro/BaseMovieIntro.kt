package com.tafreshiali.domain.model.intro

import com.tafreshiali.data.remote.model.intro.BaseMovieIntroResponse
import com.tafreshiali.domain.model.genre.MovieGenresItem

data class BaseMovieIntro(
    val dates: UpComingMoviesDate?,
    val page: Int?,
    val moviesIntro: List<MovieIntroItem>,
    val totalPages: Int?,
    val totalResults: Int?
)

fun BaseMovieIntroResponse.toBaseMovieIntro(genreList: List<MovieGenresItem>): BaseMovieIntro? {
    if (moviesIntro.isNullOrEmpty()) return null

    return BaseMovieIntro(
        dates = dates?.toUpComingMoviesDate(),
        page = page,
        moviesIntro = moviesIntro.toMovieIntroItems(genreList),
        totalPages = totalPages,
        totalResults = totalResults
    )
}
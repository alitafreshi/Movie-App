package com.tafreshiali.domain.model.intro

import com.tafreshiali.data.remote.model.intro.BaseMovieItemIntroResponse
import com.tafreshiali.domain.model.genre.MovieGenresItem

data class MovieIntroItem(
    val genreList: List<MovieGenresItem>,
    val id: Int,
    val originalTitle: String,
    val posterPath: String,
)

fun List<BaseMovieItemIntroResponse?>?.toMovieIntroItems(genreList: List<MovieGenresItem>): List<MovieIntroItem> =
    this?.mapNotNull {
        it?.let { itemResponse ->
            if (
                itemResponse.id == null ||
                itemResponse.originalTitle.isNullOrEmpty() ||
                itemResponse.posterPath.isNullOrEmpty()
            ) return@mapNotNull null

            MovieIntroItem(
                genreList = genreList,
                id = itemResponse.id!!,
                originalTitle = itemResponse.originalTitle!!,
                posterPath = itemResponse.posterPath!!
            )
        }
    } ?: emptyList()

package com.tafreshiali.domain.model.genre

import com.tafreshiali.data.remote.model.genre.GenreItemResponse

data class MovieGenresItem(val id: Int, val name: String)

fun List<GenreItemResponse?>.toMovieGenresItem(): List<MovieGenresItem> = mapNotNull {
    it?.let { response -> MovieGenresItem(response.id, response.name) }
}


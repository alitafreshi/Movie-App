package com.tafreshiali.data.remote.model.genre


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.tafreshiali.domain.model.genre.MovieGenresItem

@Keep
data class MovieGenresResponse(
    @SerializedName("genres")
    val genreList: List<GenreItemResponse?>
)
fun List<GenreItemResponse?>.toMovieGenresList(): List<MovieGenresItem> = mapNotNull {
    it?.let { response -> MovieGenresItem(response.id, response.name) }
}
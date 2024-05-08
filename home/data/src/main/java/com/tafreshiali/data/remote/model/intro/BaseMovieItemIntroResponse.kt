package com.tafreshiali.data.remote.model.intro


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.tafreshiali.domain.model.genre.MovieGenresItem
import com.tafreshiali.domain.model.intro.MovieIntroItem

@Keep
data class BaseMovieItemIntroResponse(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
)

fun List<BaseMovieItemIntroResponse?>?.toMovieIntroItems(genreSourceList: List<MovieGenresItem>): List<MovieIntroItem> =
    this?.mapNotNull {
        it?.let { itemResponse ->
            if (
                itemResponse.id == null ||
                itemResponse.genreIds.isNullOrEmpty() ||
                itemResponse.originalTitle.isNullOrEmpty() ||
                itemResponse.posterPath.isNullOrEmpty() ||
                itemResponse.overview.isNullOrEmpty()
            ) return@mapNotNull null

            MovieIntroItem(
                genreList = itemResponse.genreIds.findGenreListById(genreSourceList),
                id = itemResponse.id,
                originalTitle = itemResponse.originalTitle,
                posterPath = itemResponse.posterPath,
                overview = itemResponse.overview
            )
        }
    } ?: emptyList()

fun List<Int>.findGenreListById(genreSourceList: List<MovieGenresItem>): String {
    val genreMap = genreSourceList.associateBy { it.id }
    if (genreMap.isEmpty()) return ""
    return mapNotNull { genreMap[it] }.mapIndexed { index, genre ->
        if (index < size - 1) "${genre.name}, " else genre.name
    }.joinToString("")
}

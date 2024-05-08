package com.tafreshiali.data.remote.model.intro


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.tafreshiali.domain.model.genre.MovieGenresItem
import com.tafreshiali.domain.model.intro.BaseMovieIntro

@Keep
data class BaseMovieIntroResponse(
    @SerializedName("dates")
    val dates: UpComingMoviesDateResponse?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val moviesIntro: List<BaseMovieItemIntroResponse?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)
@Throws(IllegalArgumentException::class)
fun BaseMovieIntroResponse.toBaseMovieIntro(genreList: List<MovieGenresItem>): BaseMovieIntro {
    if (moviesIntro.isNullOrEmpty()) throw IllegalArgumentException("moviesIntro is null or empty")

    return BaseMovieIntro(
        dates = dates?.toUpComingMoviesDate(),
        page = page,
        moviesIntro = moviesIntro.toMovieIntroItems(genreList),
        totalPages = totalPages,
        totalResults = totalResults
    )
}
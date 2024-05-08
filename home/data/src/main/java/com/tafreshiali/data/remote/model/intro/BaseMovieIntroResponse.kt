package com.tafreshiali.data.remote.model.intro


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

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
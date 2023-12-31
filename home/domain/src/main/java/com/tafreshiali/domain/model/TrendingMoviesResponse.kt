package com.tafreshiali.domain.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class TrendingMoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
package com.tafreshiali.data.remote.model.intro


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.tafreshiali.domain.model.intro.UpComingMoviesDate

@Keep
data class UpComingMoviesDateResponse(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)
fun UpComingMoviesDateResponse.toUpComingMoviesDate() = UpComingMoviesDate(maximum, minimum)
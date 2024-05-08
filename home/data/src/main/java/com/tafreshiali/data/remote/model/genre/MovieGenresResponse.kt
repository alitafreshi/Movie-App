package com.tafreshiali.data.remote.model.genre


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MovieGenresResponse(
    @SerializedName("genres")
    val genreList: List<GenreItemResponse?>
)
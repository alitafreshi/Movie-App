package com.tafreshiali.data.remote.model.genre


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
@Keep
data class GenreItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
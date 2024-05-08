package com.tafreshiali.domain.model.intro

import com.tafreshiali.data.remote.model.intro.UpComingMoviesDateResponse

data class UpComingMoviesDate(val maximum: String, val minimum: String)

fun UpComingMoviesDateResponse.toUpComingMoviesDate() = UpComingMoviesDate(maximum, minimum)

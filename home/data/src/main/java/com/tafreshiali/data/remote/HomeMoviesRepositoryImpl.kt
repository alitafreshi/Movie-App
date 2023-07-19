package com.tafreshiali.data.remote

import com.tafreshiali.domain.model.TrendingMoviesResponse
import com.tafreshiali.domain.repository.HomeMoviesRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.prepareGet
import io.ktor.http.appendEncodedPathSegments
import javax.inject.Inject


class HomeMoviesRepositoryImpl @Inject constructor(private val httpClient: HttpClient) : HomeMoviesRepository {

    override suspend fun getTrendingMovies(type: String, time: String): TrendingMoviesResponse =
        httpClient.prepareGet {
            url {
                appendEncodedPathSegments("trending", type, time)
            }
        }.body()
}
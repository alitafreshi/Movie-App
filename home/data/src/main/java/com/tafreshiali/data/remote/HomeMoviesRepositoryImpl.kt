package com.tafreshiali.data.remote

import com.tafreshiali.data.remote.model.genre.MovieGenresResponse
import com.tafreshiali.data.remote.model.genre.toMovieGenresList
import com.tafreshiali.data.remote.model.intro.BaseMovieIntroResponse
import com.tafreshiali.data.remote.model.intro.toBaseMovieIntro
import com.tafreshiali.domain.model.genre.MovieGenresItem
import com.tafreshiali.domain.model.intro.BaseMovieIntro
import com.tafreshiali.domain.repository.HomeMoviesRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.prepareGet
import io.ktor.http.appendEncodedPathSegments
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class HomeMoviesRepositoryImpl @Inject constructor(private val httpClient: HttpClient) :
    HomeMoviesRepository {
    override suspend fun getTrendingMovies(type: String, time: String): BaseMovieIntro {
        return coroutineScope {
            val trendingMovies: Deferred<BaseMovieIntroResponse> = async {
                httpClient.prepareGet {
                    url {
                        appendEncodedPathSegments("trending", type, time)
                    }
                }.body()
            }
            val movieGenres: Deferred<List<MovieGenresItem>> = async { getGenres() }
            trendingMovies.await().toBaseMovieIntro(movieGenres.await())
        }
    }

    private suspend fun getGenres(): List<MovieGenresItem> {
        val response: MovieGenresResponse = httpClient.prepareGet {
            url {
                appendEncodedPathSegments("genre/movie/list")
            }
        }.body()
        return response.genreList.toMovieGenresList()
    }
}
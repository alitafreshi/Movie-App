package com.tafreshiali.domain.interactors

import com.tafreshiali.data_state.DataState
import com.tafreshiali.domain.model.intro.BaseMovieIntro
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetHomeMoviesUseCase @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) {
    operator fun invoke(): Flow<DataState<HomeMovies>> =
        getTrendingMoviesUseCase().combine(getTopRatedMoviesUseCase()) { trending, topRated ->
            when {
                trending is DataState.Loading || topRated is DataState.Loading -> DataState.Loading
                trending is DataState.Error -> DataState.Error(errorMessage = trending.errorMessage)
                topRated is DataState.Error -> DataState.Error(errorMessage = topRated.errorMessage)
                trending is DataState.Data && topRated is DataState.Data -> DataState.Data(
                    HomeMovies(topRated.data, trending.data)
                )

                else -> throw IllegalStateException("Unknown State")
            }
        }.catch { throwable ->
            emit(DataState.Error(throwable.message ?: "Some Thing Went Wrong"))
        }

    data class HomeMovies(val topRatedMovies: BaseMovieIntro, val trendingMovies: BaseMovieIntro)
}
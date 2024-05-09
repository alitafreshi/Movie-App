package com.tafreshiali.domain.interactors

import com.tafreshiali.data_state.DataState
import com.tafreshiali.domain.model.intro.BaseMovieIntro
import com.tafreshiali.domain.repository.HomeMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val homeMoviesRepository: HomeMoviesRepository
) {
    operator fun invoke(): Flow<DataState<BaseMovieIntro>> = flow {
        val result = homeMoviesRepository.getTopRatedMovies()
        if (result.moviesIntro.isEmpty()) {
            emit(DataState.Error(errorMessage = "movie list is empty"))
            return@flow
        }
        emit(DataState.Data(result))
    }.onStart { emit(DataState.Loading) }
        .catch { emit(DataState.Error(it.localizedMessage ?: "An unexpected error occurred")) }
}
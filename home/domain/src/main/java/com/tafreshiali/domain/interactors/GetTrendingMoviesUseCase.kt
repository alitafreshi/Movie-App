package com.tafreshiali.domain.interactors

import com.tafreshiali.data_state.DataState
import com.tafreshiali.domain.model.Result
import com.tafreshiali.domain.model.TrendingMoviesResponse
import com.tafreshiali.domain.repository.HomeMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetTrendingMoviesUseCase @Inject constructor(private val homeMoviesRepository: HomeMoviesRepository) {
    operator fun invoke(): Flow<DataState<List<Result>>> =
        flow {
            val result = homeMoviesRepository.getTrendingMovies().results
            if (result.isEmpty()){
                emit(DataState.Error(errorMessage = "movie list is empty"))
                return@flow
            }
            emit(DataState.Data(result))
        }.onStart { emit(DataState.Loading) }
            .catch { emit(DataState.Error(it.localizedMessage ?: "An unexpected error occurred")) }
}
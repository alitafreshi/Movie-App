package com.tafreshiali.domain.interactors

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.tafreshiali.data.remote.HomeMoviesRepositoryImpl
import com.tafreshiali.data_state.DataState
import com.tafreshiali.domain.model.Result
import com.tafreshiali.domain.model.TrendingMoviesResponse
import com.tafreshiali.domain.repository.HomeMoviesRepository
import io.ktor.client.HttpClient
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetTrendingMoviesUseCaseTest {

    //System Under Test (SUT)
    private lateinit var getTrendingMoviesUseCase: GetTrendingMoviesUseCase

    private val homeMoviesRepository: HomeMoviesRepository = mockk(relaxed = true)

    private val mockMovies = listOf(
        Result(
            adult = false,
            backdropPath = "corrumpit",
            genreIds = listOf(),
            id = 6297,
            mediaType = "brute",
            originalLanguage = "noluisse",
            originalTitle = "dicat",
            overview = "antiopam",
            popularity = 4.5,
            posterPath = "luctus",
            releaseDate = "quo",
            title = "novum",
            video = false,
            voteAverage = 6.7,
            voteCount = 9373
        )
    )

    @Before
    fun setUp() {
        getTrendingMoviesUseCase = GetTrendingMoviesUseCase(homeMoviesRepository)
    }

    @Test
    fun `get trending movies and verify loading state in on Start`() = runTest {
        coEvery { homeMoviesRepository.getTrendingMovies() } returns TrendingMoviesResponse(
            page = 8171,
            results = listOf(),
            totalPages = 5186,
            totalResults = 4327
        )

        getTrendingMoviesUseCase().test {
            val emission = awaitItem()
            assertThat(emission).isEqualTo(DataState.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `get trending movies and verify that movie list is null or empty should emit error state`() =
        runTest {
            coEvery { homeMoviesRepository.getTrendingMovies() } returns TrendingMoviesResponse(
                page = 8171,
                results = listOf(),
                totalPages = 5186,
                totalResults = 4327
            )

            getTrendingMoviesUseCase().test {
                val loadingState = awaitItem()
                assertThat(loadingState).isEqualTo(DataState.Loading)
                //because results is empty
                val errorState = awaitItem()
                assertThat(errorState).isEqualTo(DataState.Error("movie list is empty"))
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `get trending movies and verify that movie list isn't null or empty and emit data`() =
        runTest {
            coEvery { homeMoviesRepository.getTrendingMovies() } returns TrendingMoviesResponse(
                page = 8171,
                results = mockMovies,
                totalPages = 5186,
                totalResults = 4327
            )

            getTrendingMoviesUseCase().test {
                val loadingState = awaitItem()
                assertThat(loadingState).isEqualTo(DataState.Loading)
                val data = awaitItem()
                assertThat(data).isNotEqualTo(DataState.Error("movie list is empty"))
                assertThat(data).isEqualTo(DataState.Data(mockMovies))
                cancelAndConsumeRemainingEvents()
            }
        }
}
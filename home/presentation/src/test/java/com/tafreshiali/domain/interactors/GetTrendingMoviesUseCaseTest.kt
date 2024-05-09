package com.tafreshiali.domain.interactors

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.tafreshiali.data.remote.model.intro.BaseMovieIntroResponse
import com.tafreshiali.data_state.DataState
import com.tafreshiali.domain.model.intro.BaseMovieIntro
import com.tafreshiali.domain.model.intro.MovieIntroItem
import com.tafreshiali.domain.repository.HomeMoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetTrendingMoviesUseCaseTest {

    //System Under Test (SUT)
    private lateinit var getTrendingMoviesUseCase: GetTrendingMoviesUseCase

    private val homeMoviesRepository: HomeMoviesRepository = mockk(relaxed = true)

    private val mockMovies = BaseMovieIntro(
        page = 8171,
        moviesIntro = listOf(
            MovieIntroItem(
                genreList = "natum",
                id = 5612,
                originalTitle = "melius",
                posterPath = "graece",
                overview = "disputationi"
            )
        ),
        totalPages = 5186,
        totalResults = 4327,
        dates = null
    )


    @Before
    fun setUp() {
        getTrendingMoviesUseCase = GetTrendingMoviesUseCase(homeMoviesRepository)
    }

    @Test
    fun `get trending movies and verify loading state in on Start`() = runTest {
        coEvery { homeMoviesRepository.getTrendingMovies() } returns BaseMovieIntro(
            page = null,
            moviesIntro = listOf(),
            totalPages = null,
            totalResults = null,
            dates = null
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
            coEvery { homeMoviesRepository.getTrendingMovies() } returns BaseMovieIntro(
                page = null,
                moviesIntro = listOf(),
                totalPages = null,
                totalResults = null,
                dates = null
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
            coEvery { homeMoviesRepository.getTrendingMovies() } returns mockMovies

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
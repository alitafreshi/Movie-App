package com.tafreshiali.domain.interactors

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.tafreshiali.data_state.DataState
import com.tafreshiali.domain.model.intro.BaseMovieIntro
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetHomeMoviesUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)


    //Dependencies for SUT
    @RelaxedMockK
    lateinit var getTrendingMoviesUseCase: GetTrendingMoviesUseCase

    @RelaxedMockK
    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    //System Under Test (SUT)
    private lateinit var getHomeMoviesUseCase: GetHomeMoviesUseCase

    @Before
    fun setUp() {
        getHomeMoviesUseCase =
            GetHomeMoviesUseCase(getTrendingMoviesUseCase, getTopRatedMoviesUseCase)
    }

    @Test
    fun `get home movies and verify loading state in on Start`() = runTest {
        // Given
        val expectedState = DataState.Loading

        every { getTrendingMoviesUseCase() } returns flow<DataState<BaseMovieIntro>> {

        }.onStart { emit(DataState.Loading) }

        every { getTopRatedMoviesUseCase() } returns flow<DataState<BaseMovieIntro>> {

        }.onStart { emit(DataState.Loading) }

        getHomeMoviesUseCase().test {
            val emission = awaitItem()
            assertThat(emission).isEqualTo(expectedState)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `get home movies , when trending movies received but top rated movies still in loading state, then verify loading state`() =
        runTest {
            every { getTrendingMoviesUseCase() } returns flow<DataState<BaseMovieIntro>> {
                emit(
                    DataState.Data(
                        BaseMovieIntro(
                            dates = null,
                            page = null,
                            moviesIntro = listOf(),
                            totalPages = null,
                            totalResults = null
                        )
                    )
                )
            }.onStart { emit(DataState.Loading) }

            every { getTopRatedMoviesUseCase() } returns flow<DataState<BaseMovieIntro>> {
                emit(DataState.Loading)
            }.onStart { emit(DataState.Loading) }

            getHomeMoviesUseCase().test {
                assertThat(awaitItem()).isEqualTo(DataState.Loading)


            }
        }
}
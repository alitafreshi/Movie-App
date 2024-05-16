package com.tafreshiali.presentation

import com.google.common.truth.Truth.assertThat
import com.tafreshiali.common.MainDispatcherRule
import com.tafreshiali.data_state.DataState
import com.tafreshiali.domain.interactors.GetHomeMoviesUseCase
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@MockKExtension.ConfirmVerification
@MockKExtension.CheckUnnecessaryStub
class HomeViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    //System Under Test (SUT)
    private lateinit var homeViewModel: HomeViewModel

    //Dependencies for SUT
    @RelaxedMockK
    lateinit var getHomeMoviesUseCase: GetHomeMoviesUseCase

    @Test
    fun `get trending movies verify loading state`() = runTest {
        // Given
        val expectedState = HomeViewState(homeState = HomeViewState.HomeState.Loading)
        coEvery { getHomeMoviesUseCase() } returns flow<DataState<GetHomeMoviesUseCase.HomeMovies>> {

        }.onStart {
            emit(DataState.Loading)
        }
        /**
         * As We Launch The GetTrendingMovies Events In The Viewmodel Init Block
         * We Couldn't Initiate Viewmodel In @Before Because At First We Need To Define The Answer
         * For Our UseCase
         * */
        homeViewModel = HomeViewModel(getHomeMoviesUseCase)

        /**
         * As We Launch The GetTrendingMovies Events In The Viewmodel Init There Is No Need
         * To Trigger It Again
         * */
        // When
        //homeViewModel.onTriggerEvent(HomeEvents.GetTrendingMovies)


        // Then
        assertThat(expectedState).isEqualTo(homeViewModel.viewState.value)
    }

 /*   @Test
    fun `get trending movies verify error state`() = runTest {
        // Given
        val expectedState = HomeViewState(loadingState = LoadingState.Idle)
        coEvery { getTrendingMoviesUseCase() } returns flow {
            emit(DataState.Error("Test error"))
        }

        *//**
         * As We Launch The GetTrendingMovies Events In The Viewmodel Init Block
         * We Couldn't Initiate Viewmodel In @Before Because At First We Need To Define The Answer
         * For Our UseCase
         * *//*
        homeViewModel = HomeViewModel(getTrendingMoviesUseCase)

        *//**
         * As We Launch The GetTrendingMovies Events In The Viewmodel Init There Is No Need
         * To Trigger It Again
         * *//*
        // When
        // homeViewModel.onTriggerEvent(HomeEvents.GetTrendingMovies)

        // Then
        assertThat(expectedState).isEqualTo(homeViewModel.viewState.value)
    }


    @Test
    fun `get trending movies verify success state when movies list isn't null or empty`() {

        val expectedState = HomeViewState(
            bannerUrl = "luctus",
            trendingMovies = listOf(
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
            ),
            loadingState = LoadingState.Idle
        )

        coEvery { getTrendingMoviesUseCase() } returns flow {
            emit(
                DataState.Data(
                    listOf(
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
                )
            )
        }

        *//**
         * As We Launch The GetTrendingMovies Events In The Viewmodel Init Block
         * We Couldn't Initiate Viewmodel In @Before Because At First We Need To Define The Answer
         * For Our UseCase
         * *//*
        homeViewModel = HomeViewModel(getTrendingMoviesUseCase)

        *//**
         * As We Launch The GetTrendingMovies Events In The Viewmodel Init There Is No Need
         * To Trigger It Again
         * *//*
        // When
        // homeViewModel.onTriggerEvent(HomeEvents.GetTrendingMovies)


        // Then
        assertThat(expectedState).isEqualTo(homeViewModel.viewState.value)
    }*/
}
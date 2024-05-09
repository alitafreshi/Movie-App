package com.tafreshiali.presentation

import androidx.lifecycle.viewModelScope
import com.tafreshiali.components.BaseViewModel
import com.tafreshiali.data_state.DataState
import com.tafreshiali.domain.interactors.GetTrendingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase) :
    BaseViewModel<HomeViewState, HomeEvents, Unit>() {
    init {
        onTriggerEvent(event = HomeEvents.GetTrendingMovies)
    }

    override fun initNewViewState(): HomeViewState = HomeViewState()

    override fun onTriggerEvent(event: HomeEvents) {
        when (event) {
            HomeEvents.GetTrendingMovies -> {
                getTrendingMovies()
            }

            HomeEvents.OnRetryClick -> {
                getTrendingMovies()
            }
        }
    }

    private fun getTrendingMovies() {
        getTrendingMoviesUseCase().onEach { dataState ->
            when (dataState) {
                DataState.Loading -> setViewState(
                    viewState = getCurrentViewStateOrNew().copy(
                        homeState = HomeViewState.HomeState.Loading
                    )
                )

                is DataState.Data -> {
                    val sliderImageList = dataState.data.moviesIntro.slice(0.until(4))
                    val trendingMovies =
                        dataState.data.moviesIntro.slice(4.until(dataState.data.moviesIntro.size))
                    setViewState(
                        viewState = getCurrentViewStateOrNew().copy(
                            homeState = HomeViewState.HomeState.Idle,
                            sliderImageList = sliderImageList,
                            trendingMovies = trendingMovies
                        )
                    )
                }

                is DataState.Error -> setViewState(
                    viewState = getCurrentViewStateOrNew().copy(
                        homeState = HomeViewState.HomeState.Error(errorMessage = dataState.errorMessage)
                    )
                )
            }
        }.launchIn(viewModelScope)
    }

}
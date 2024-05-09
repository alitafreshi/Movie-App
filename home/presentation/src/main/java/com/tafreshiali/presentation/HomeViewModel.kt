package com.tafreshiali.presentation

import androidx.lifecycle.viewModelScope
import com.tafreshiali.components.BaseViewModel
import com.tafreshiali.data_state.DataState
import com.tafreshiali.domain.interactors.GetHomeMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getHomeMoviesUseCase: GetHomeMoviesUseCase) :
    BaseViewModel<HomeViewState, HomeEvents, Unit>() {
    init {
        getHomeMovies()
    }

    override fun initNewViewState(): HomeViewState = HomeViewState()

    override fun onTriggerEvent(event: HomeEvents) {
        when (event) {
            HomeEvents.OnRetryClick -> {
                getHomeMovies()
            }
        }
    }

    private fun getHomeMovies() {
        getHomeMoviesUseCase().onEach { dataState ->
            when (dataState) {
                DataState.Loading -> setViewState(
                    viewState = getCurrentViewStateOrNew().copy(
                        homeState = HomeViewState.HomeState.Loading
                    )
                )

                is DataState.Data -> {
                    val sliderImageList =
                        dataState.data.trendingMovies.moviesIntro.slice(0.until(4))
                    val trendingMovies =
                        dataState.data.trendingMovies.moviesIntro.slice(4.until(dataState.data.trendingMovies.moviesIntro.size))

                    val topRatedMovies = dataState.data.topRatedMovies.moviesIntro

                    setViewState(
                        viewState = getCurrentViewStateOrNew().copy(
                            homeState = HomeViewState.HomeState.Idle,
                            sliderImageList = sliderImageList,
                            trendingMovies = trendingMovies,
                            topRatedMovies = topRatedMovies
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
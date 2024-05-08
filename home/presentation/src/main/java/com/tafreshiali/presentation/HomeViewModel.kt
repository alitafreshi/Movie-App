package com.tafreshiali.presentation

import androidx.lifecycle.viewModelScope
import com.tafreshiali.app_state_manager.LoadingState
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

            }
        }
    }

    private fun getTrendingMovies() {
        val x = getTrendingMoviesUseCase().onEach { dataState ->
            when (dataState) {
                is DataState.Data -> {
                    val sliderImageList = dataState.data.moviesIntro.slice(0.until(4))
                    val trendingMovies =
                        dataState.data.moviesIntro.slice(4.until(dataState.data.moviesIntro.size))
                    setViewState(
                        viewState = getCurrentViewStateOrNew().copy(
                            sliderImageList = sliderImageList,
                            trendingMovies = trendingMovies,
                            loadingState = LoadingState.Idle
                        )
                    )
                }

                is DataState.Error -> setViewState(
                    viewState = getCurrentViewStateOrNew().copy(
                        loadingState = LoadingState.Idle
                    )
                )

                DataState.Loading -> setViewState(
                    viewState = getCurrentViewStateOrNew().copy(
                        loadingState = LoadingState.Loading
                    )
                )
            }
        } .launchIn(viewModelScope)
    }

}
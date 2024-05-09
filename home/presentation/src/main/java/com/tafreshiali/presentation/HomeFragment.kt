package com.tafreshiali.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.tafreshiali.ui_kit.ErrorScreenComponent
import com.tafreshiali.ui_kit.ErrorScreenComponentType
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by hiltNavGraphViewModels(R.id.home_nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(context = requireContext()).apply {
        setContent {
            val homeViewState = homeViewModel.viewState.collectAsState().value
            when (homeViewState.homeState) {
                is HomeViewState.HomeState.Idle -> HomeScreen(
                    homeViewState = homeViewState,
                    homeEvents = homeViewModel::onTriggerEvent
                )

                is HomeViewState.HomeState.Loading -> HomeScreenLoadingComponent()

                is HomeViewState.HomeState.Error ->
                    ErrorScreenComponent(
                        errorScreenComponentType = ErrorScreenComponentType.RetryAble(
                            title = "Sorry, we can’t find movies :’(",
                            button = {
                                Button(
                                    onClick = { homeViewModel.onTriggerEvent(HomeEvents.OnRetryClick) },
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .padding(vertical = 16.dp, horizontal = 20.dp)
                                ) {
                                    Text(
                                        text = "Retry",
                                        style = AppTheme.typography.bodySmallBold
                                    )
                                }
                            }
                        )
                    )
            }
        }
    }
}
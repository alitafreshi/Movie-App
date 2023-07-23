package com.tafreshiali.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tafreshiali.components.BaseScreenContainer
import com.tafreshiali.presentation.components.BannerComponent
import com.tafreshiali.presentation.components.HomeImageSliderComponent
import com.tafreshiali.presentation.components.HomeSectionHeaderComponent

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewState: HomeViewState,
    homeEvents: (HomeEvents) -> Unit
) {
    BaseScreenContainer(
        topAppBarNavigationIcon = {
            Icon(imageVector = Icons.Rounded.Home, contentDescription = "Home")
        },
        topAppBarActions = {
            Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Menu")
        }
    ) { paddingValues ->
        LazyColumn(modifier = modifier.padding(horizontal = 15.dp), contentPadding = paddingValues) {
            item {
                BannerComponent(
                    bannerUrl = homeViewState.bannerUrl,
                    bannerTitle = "Watch This Beautiful movie and send back your feed back"
                )
            }
            item {
                HomeSectionHeaderComponent(key = "Popular", value = "view all")
            }

            item {
                HomeImageSliderComponent(imageUrls = homeViewState.trendingMovies)
            }
        }
    }

}
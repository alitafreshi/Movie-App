package com.tafreshiali.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.presentation.components.HomeMainImageSliderComponent
import com.tafreshiali.presentation.components.RecommendedMovieSectionComponent
import com.tafreshiali.presentation.components.TopRatedMovieSectionComponent
import com.tafreshiali.ui_kit.BaseScreenContainer
import com.tafreshiali.ui_kit.BottomBarComponent
import com.tafreshiali.ui_kit.BottomNavItem
import com.tafreshiali.ui_kit.R
import com.tafreshiali.ui_kit.UserProfileContainer
import com.tafreshiali.ui_kit.design_system.ui.theme.primaryLight
import com.tafreshiali.ui_kit.R as uiKitRes

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewState: HomeViewState,
    homeEvents: (HomeEvents) -> Unit
) {
    BaseScreenContainer(
        topAppBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                UserProfileContainer(name = "Mohit", profileDescription = "Let's watch a movie")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        imageVector = ImageVector.vectorResource(uiKitRes.drawable.ic_search_topbar),
                        contentDescription = "ic_search_top_bar"
                    )
                    Spacer(modifier = Modifier.size(width = 16.dp, height = 1.dp))
                    Image(
                        imageVector = ImageVector.vectorResource(uiKitRes.drawable.ic_bell_topbar),
                        contentDescription = "ic_bell_top_bar"
                    )
                }
            }
        },
        bottomBar = {
            BottomBarComponent(
                items = listOf(
                    BottomNavItem(
                        deeplink = "HomeIcon",
                        icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_home_navigation)),
                        isSelected = true,
                        selectedColor = primaryLight,
                        unSelectedColor = Color.Gray
                    ),
                    BottomNavItem(
                        deeplink = "SearchIcon",
                        icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_search_navigation)),
                        isSelected = false,
                        selectedColor = primaryLight,
                        unSelectedColor = Color.Gray
                    ),
                    BottomNavItem(
                        deeplink = "DownloadIcon",
                        icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_download_navigation)),
                        isSelected = false,
                        selectedColor = primaryLight,
                        unSelectedColor = Color.Gray
                    ),
                    BottomNavItem(
                        deeplink = "ProfileIcon",
                        icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_profile_navigation)),
                        isSelected = false,
                        selectedColor = primaryLight,
                        unSelectedColor = Color.Gray
                    )
                ),
                onItemSelected = { selectedBottomNavItem ->
                    Log.d("BottomBarComponent", "Selected NavItem Is :$selectedBottomNavItem")
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding() + 20.dp
            )
        ) {
            item {
                HomeMainImageSliderComponent(imageUrls = homeViewState.trendingMovies.take(4))
            }

            item {
                RecommendedMovieSectionComponent(recommendedMovies = homeViewState.trendingMovies)
            }

            item {
                TopRatedMovieSectionComponent(topRatedMovies = homeViewState.topRatedMovies)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(homeViewState = HomeViewState(), homeEvents = {})
}
package com.tafreshiali.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.presentation.components.MovieItemShimmerComponent
import com.tafreshiali.ui_kit.BaseScreenContainer
import com.tafreshiali.ui_kit.MovieCategoryHeaderShimmerComponent
import com.tafreshiali.ui_kit.animations.shimmerEffect
import com.tafreshiali.ui_kit.design_system.ui.theme.grayscale10ContainerLight

//TODO Replace All grayscale10ContainerLight with AppTheme.colorSchema.shimmerEffect
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenLoadingComponent(modifier: Modifier = Modifier) {
    BaseScreenContainer(
        topAppBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(
                                color = grayscale10ContainerLight,
                                shape = CircleShape
                            )
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Box(
                            modifier = Modifier
                                .size(width = 40.dp, height = 15.dp)
                                .background(
                                    color = grayscale10ContainerLight,
                                    shape = RoundedCornerShape(3.dp)
                                )
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .size(width = 100.dp, height = 15.dp)
                                .background(
                                    color = grayscale10ContainerLight,
                                    shape = RoundedCornerShape(3.dp)
                                )
                                .shimmerEffect()
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = grayscale10ContainerLight,
                                shape = CircleShape
                            )
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.size(width = 16.dp, height = 1.dp))
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = grayscale10ContainerLight,
                                shape = CircleShape
                            )
                            .shimmerEffect()
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = modifier, contentPadding = paddingValues) {
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .padding(start = 20.dp, end = 20.dp, top = 24.dp)
                            .background(
                                color = grayscale10ContainerLight,
                                shape = MaterialTheme.shapes.large
                            )
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        repeat(4) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 2.dp)
                                    .size(6.dp)
                                    .background(
                                        color = grayscale10ContainerLight,
                                        shape = MaterialTheme.shapes.large
                                    )
                            )
                        }
                    }
                }
            }

            item {
                Column(Modifier.padding(top = 24.dp)) {
                    MovieCategoryHeaderShimmerComponent()

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(15) {
                            MovieItemShimmerComponent()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun HomeScreenLoadingComponentPreview() {
    HomeScreenLoadingComponent()
}
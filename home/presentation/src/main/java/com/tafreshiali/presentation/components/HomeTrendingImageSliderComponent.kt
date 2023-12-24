package com.tafreshiali.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.tafreshiali.domain.model.Result
import com.tafreshiali.presentation.HomeConstance
import kotlin.math.absoluteValue

@ExperimentalFoundationApi
@Composable
fun HomeImageSliderComponent(modifier: Modifier = Modifier, imageUrls: List<Result>) {
    val horizontalPadding = 10
    val itemWidth = 270
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val contentPadding =
        PaddingValues(horizontal = (screenWidth - itemWidth - horizontalPadding).dp)


    val pagerState = rememberPagerState()

    HorizontalPager(
        modifier = Modifier.semantics { this.contentDescription = "trending_movies_list" },
        pageCount = imageUrls.size,
        state = pagerState,
        contentPadding = contentPadding
    ) { index ->
        val pageOffset = (
                (pagerState.currentPage - index) + pagerState
                    .currentPageOffsetFraction
                ).absoluteValue
        ImageSlideComponent(
            imageUrl = imageUrls[index].posterPath,
            pageOffset = pageOffset
        )
    }
}

@Composable
private fun ImageSlideComponent(
    modifier: Modifier = Modifier,
    imageUrl: String,
    pageOffset: Float,
) {
    SubcomposeAsyncImage(
        model = "${HomeConstance.IMAGE_BASE_URL}/w780$imageUrl",
        contentScale = ContentScale.Crop,
        contentDescription = "popular_movie_item",
        modifier = modifier
            .graphicsLayer {
                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
            .clip(shape = MaterialTheme.shapes.large)
    ) {
        when (painter.state) {
            AsyncImagePainter.State.Empty -> {}

            is AsyncImagePainter.State.Error -> {}

            is AsyncImagePainter.State.Loading -> {}

            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun HomeImageSliderComponentPreview() {
    HomeImageSliderComponent(
        modifier = Modifier.fillMaxSize(),
        imageUrls = listOf()
    )
}
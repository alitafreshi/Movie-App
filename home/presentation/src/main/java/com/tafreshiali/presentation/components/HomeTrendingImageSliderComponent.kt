package com.tafreshiali.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.tafreshiali.domain.model.Result
import com.tafreshiali.presentation.HomeConstance
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.sin

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
        modifier = modifier,
        pageCount = imageUrls.size,
        state = pagerState,
        contentPadding = contentPadding
    ) { index ->
        val pageOffset = (
                (pagerState.currentPage - index) + pagerState
                    .currentPageOffsetFraction
                ).absoluteValue
        val curvedAngle = calculateCurvedAngle(scrollOffset = pageOffset)
        val angle = if (index < pagerState.currentPage) {
            curvedAngle + 2 * PI.toFloat() / imageUrls.size * index // Previous items in the first quadrant
        } else {
            curvedAngle + 2 * PI.toFloat() / imageUrls.size * (index - imageUrls.size) // Next items in the second quadrant
        }

        val rotationZ = if (index < pagerState.currentPage) {
            (-angle).toDegrees().coerceIn(-10f, 0f) // Previous items rotate in the first quadrant
        } else {
            (-angle).toDegrees().coerceIn(0f, 10f) // Next items rotate in the second quadrant
        }


        //val angle = curvedAngle + 2 * PI.toFloat() / imageUrls.size * index

        ImageSlideComponent(
            imageUrl = imageUrls[index].posterPath,
            pageOffset = pageOffset,
            angle = rotationZ,
            isCurrentItem = !(index < pagerState.currentPage || index > pagerState.currentPage)
        )
    }
}

@Composable
private fun ImageSlideComponent(
    modifier: Modifier = Modifier,
    imageUrl: String,
    pageOffset: Float,
    angle: Float,
    isCurrentItem: Boolean
) {
    SubcomposeAsyncImage(
        model = "${HomeConstance.IMAGE_BASE_URL}/w780$imageUrl",
        contentScale = ContentScale.Crop,
        contentDescription = "Home Slider",
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
                /* alpha = lerp(
                     start = 0.5f,
                     stop = 1f,
                     fraction = 1f - pageOffset.coerceIn(0f, 1f)
                 )*/
                rotationZ =
                    lerp(start = angle, stop = 0f, fraction = 1f - pageOffset.coerceIn(0f, 1f))
            }
            .zIndex(if (isCurrentItem) 2f else 0f)
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

fun calculateCurvedAngle(scrollOffset: Float): Float {
    val curveFactor = 0.2f // Adjust this value to control the curvature
    val angle = scrollOffset * curveFactor
    return sin(angle)
}

fun Float.toDegrees(): Float = (this * 180f / PI).toFloat()

@ExperimentalFoundationApi
@Preview
@Composable
fun HomeImageSliderComponentPreview() {
    HomeImageSliderComponent(
        modifier = Modifier.fillMaxSize(),
        imageUrls = listOf()
    )
}
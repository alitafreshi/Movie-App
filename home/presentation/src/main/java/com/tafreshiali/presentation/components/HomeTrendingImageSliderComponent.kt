package com.tafreshiali.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.tafreshiali.domain.model.Result
import com.tafreshiali.presentation.HomeConstance

@ExperimentalFoundationApi
@Composable
fun HomeImageSliderComponent(modifier: Modifier = Modifier, imageUrls: List<Result>) {
    val pagerState = rememberPagerState()
    HorizontalPager(modifier = modifier, pageCount = imageUrls.size, state = pagerState) { index ->
        ImageSlideComponent(imageUrl = imageUrls[index].posterPath)
    }
}

@Composable
private fun ImageSlideComponent(modifier: Modifier = Modifier, imageUrl: String) {
    SubcomposeAsyncImage(
        model = "${HomeConstance.IMAGE_BASE_URL}/original$imageUrl",
        contentScale = ContentScale.Crop,
        contentDescription = "Home Slider",
        modifier = modifier
            .fillMaxSize()
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
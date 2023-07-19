package com.tafreshiali.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
fun BannerComponent(modifier: Modifier, bannerUrl: String) {
//TODO Add Container Like Surface For Rounded Corners
    SubcomposeAsyncImage(
        model = bannerUrl,
        contentDescription = "Home Banner",
        modifier = modifier
    ) {
        when (painter.state) {
            AsyncImagePainter.State.Empty -> TODO("Make The Banner Gone")
            is AsyncImagePainter.State.Error -> TODO("Make Them Banner Gone")
            is AsyncImagePainter.State.Loading -> TODO("Shimmer Effect")
            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
        }
    }
}
package com.tafreshiali.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
fun BannerComponent(modifier: Modifier = Modifier, bannerUrl: String, bannerTitle: String) {
    //TODO Add Container Like Surface For Rounded Corners
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)

    ) {
        val (bannerCloseButton, bannerContainer, bannerDescription, bannerButton) = createRefs()
        BannerContainer(
            modifier = Modifier.constrainAs(ref = bannerContainer) {
                top.linkTo(anchor = parent.top)
                bottom.linkTo(anchor = parent.bottom)
                end.linkTo(anchor = parent.end)
                start.linkTo(anchor = parent.start)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }, bannerUrl = bannerUrl
        )

        IconButton(
            modifier = Modifier
                .size(20.dp)
                .constrainAs(ref = bannerCloseButton) {
                    top.linkTo(anchor = parent.top, margin = 10.dp)
                    end.linkTo(anchor = parent.end, margin = 10.dp)
                },
            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.DarkGray),
            onClick = { }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close Button",
                tint = Color.LightGray
            )
        }

        Text(
            text = bannerTitle,
            maxLines = 2,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(ref = bannerDescription) {
                top.linkTo(anchor = parent.top, margin = 20.dp)
                start.linkTo(anchor = parent.start, margin = 15.dp)
                end.linkTo(anchor = bannerCloseButton.start, margin = 15.dp)
                width = Dimension.fillToConstraints
            })


        Button(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.constrainAs(ref = bannerButton) {
                top.linkTo(anchor = bannerDescription.bottom, margin = 15.dp)
                bottom.linkTo(anchor = parent.bottom, margin = 15.dp)
                start.linkTo(anchor = parent.start, margin = 15.dp)
            },
            onClick = { /*TODO*/ }) {
            Text(text = "Get premium", style = MaterialTheme.typography.labelMedium)
        }

    }
}


@Composable
private fun BannerContainer(modifier: Modifier = Modifier, bannerUrl: String) {

    Surface(modifier = modifier, shape = MaterialTheme.shapes.medium, color = Color.LightGray) {
        SubcomposeAsyncImage(
            model = bannerUrl,
            contentDescription = "Home Banner",
            modifier = Modifier.fillMaxSize()
        ) {
            when (painter.state) {
                AsyncImagePainter.State.Empty -> {
                    /*TODO("Make The Banner Gone")*/
                }
                is AsyncImagePainter.State.Error -> {
/*
                    TODO("Make Them Banner Gone")
*/
                }
                is AsyncImagePainter.State.Loading -> {
                    /*TODO("Shimmer Effect")*/
                }
                is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            }
        }
    }
}


@Preview
@Composable
fun BannerComponentPreview() {
    BannerComponent(
        bannerUrl = "",
        bannerTitle = "Watch This Beautiful Movie About Me And You"
    )
}
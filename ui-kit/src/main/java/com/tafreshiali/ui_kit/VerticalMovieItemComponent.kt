package com.tafreshiali.ui_kit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.ui_kit.animations.shimmerEffect
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme
import com.tafreshiali.ui_kit.design_system.ui.theme.grayscale10ContainerLight

@Composable
fun VerticalMovieItemComponent(
    modifier: Modifier = Modifier,
    movieTitle: String,
    movieGenre: String,
    moviePoster: String
) {
    Column(modifier = modifier.width(120.dp), horizontalAlignment = Alignment.Start) {
        ImageComponent(
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .clip(RoundedCornerShape(10.dp)),
            imageUrl = moviePoster,
            contentDescription = "image_recommended_movie_item"
        )
        Text(
            text = movieTitle,
            style = AppTheme.typography.bodyMediumBold,
            modifier = Modifier.padding(top = 6.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = movieGenre,
            style = AppTheme.typography.bodyExtraSmallRegular,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun VerticalMovieItemComponentPreview() {
    VerticalMovieItemComponent(
        movieGenre = "",
        movieTitle = "metus",
        moviePoster = "hendrerit"
    )
}


@Composable
fun VerticalMovieItemShimmerComponent() {
    Column(modifier = Modifier.width(120.dp), horizontalAlignment = Alignment.Start) {
        Box(
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .background(color = grayscale10ContainerLight, shape = RoundedCornerShape(10.dp))
                .shimmerEffect()

        )

        Box(
            modifier = Modifier
                .size(width = 70.dp, height = 15.dp)
                .padding(top = 6.dp)
                .background(
                    color = grayscale10ContainerLight,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .size(width = 50.dp, height = 12.dp)
                .padding(top = 6.dp)
                .background(
                    color = grayscale10ContainerLight,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .shimmerEffect()
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun VerticalMovieItemShimmerComponentPreview() {
    VerticalMovieItemShimmerComponent()
}
package com.tafreshiali.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.tafreshiali.domain.model.intro.MovieIntroItem
import com.tafreshiali.presentation.HomeConstance
import com.tafreshiali.ui_kit.ImageComponent
import com.tafreshiali.ui_kit.MovieCategoryHeaderComponent
import com.tafreshiali.ui_kit.animations.shimmerEffect
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme
import com.tafreshiali.ui_kit.design_system.ui.theme.grayscale10ContainerLight

@Composable
fun RecommendedMovieSectionComponent(
    modifier: Modifier = Modifier,
    movieList: List<MovieIntroItem>
) {
    Column(modifier = modifier.padding(top = 24.dp)) {
        MovieCategoryHeaderComponent(categoryTitle = "Recommended for you")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movieList, key = {
                it.id
            }) { movieItem ->
                MovieItemComponent(movie = movieItem)
            }
        }
    }
}

@Composable
fun MovieItemComponent(modifier: Modifier = Modifier, movie: MovieIntroItem) {
    Column(modifier = modifier.width(120.dp), horizontalAlignment = Alignment.Start) {
        ImageComponent(
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .clip(RoundedCornerShape(10.dp)),
            imageUrl = "${HomeConstance.IMAGE_BASE_URL}/original${movie.posterPath}",
            contentDescription = "image_recommended_movie_item"
        )
        Text(
            text = movie.originalTitle,
            style = AppTheme.typography.bodyMediumBold,
            modifier = Modifier.padding(top = 6.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = movie.genreList,
            style = AppTheme.typography.bodyExtraSmallRegular,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun MovieItemComponentPreview() {
    MovieItemComponent(
        movie = MovieIntroItem(
            genreList = "",
            id = 8945,
            originalTitle = "metus",
            posterPath = "hendrerit",
            overview = "tibique"
        )
    )
}


@Composable
fun MovieItemShimmerComponent() {
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
                .background(color = grayscale10ContainerLight, shape = MaterialTheme.shapes.extraSmall)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .size(width = 50.dp, height = 12.dp)
                .padding(top = 6.dp)
                .background(color = grayscale10ContainerLight, shape = MaterialTheme.shapes.extraSmall)
                .shimmerEffect()
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun MovieItemShimmerComponentPreview() {
    MovieItemShimmerComponent()
}





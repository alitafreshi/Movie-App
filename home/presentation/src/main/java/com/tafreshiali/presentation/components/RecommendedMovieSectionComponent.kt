package com.tafreshiali.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.domain.model.Result
import com.tafreshiali.presentation.HomeConstance
import com.tafreshiali.ui_kit.ImageComponent
import com.tafreshiali.ui_kit.MovieCategoryHeaderComponent
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme

@Composable
fun RecommendedMovieSectionComponent(modifier: Modifier = Modifier, movieList: List<Result>) {
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
fun MovieItemComponent(modifier: Modifier = Modifier, movie: Result) {
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
        //TODO We Have get add genreIds
        Text(
            text = "Romance, Drama",
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
        movie = Result(
            adult = false,
            backdropPath = "lorem",
            genreIds = listOf(),
            id = 3601,
            mediaType = "accusata",
            originalLanguage = "cursus",
            originalTitle = "nonumy",
            overview = "elitr",
            popularity = 4.5,
            posterPath = "postulant",
            releaseDate = "quis",
            title = "placerat",
            video = false,
            voteAverage = 6.7,
            voteCount = 4767
        )
    )
}





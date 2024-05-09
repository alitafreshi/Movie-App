package com.tafreshiali.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tafreshiali.domain.model.intro.MovieIntroItem
import com.tafreshiali.presentation.HomeConstance
import com.tafreshiali.ui_kit.MovieCategoryHeaderComponent
import com.tafreshiali.ui_kit.VerticalMovieItemComponent

@Composable
fun TopRatedMovieSectionComponent(
    modifier: Modifier = Modifier,
    topRatedMovies: List<MovieIntroItem>
) {
    Column(modifier = modifier.padding(top = 24.dp)) {
        MovieCategoryHeaderComponent(categoryTitle = "Top Rated")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(topRatedMovies, key = {
                it.id
            }) { movieItem ->
                VerticalMovieItemComponent(
                    movieTitle = movieItem.originalTitle,
                    movieGenre = movieItem.genreList,
                    moviePoster = "${HomeConstance.IMAGE_BASE_URL}/original${movieItem.posterPath}"
                )
            }
        }
    }
}
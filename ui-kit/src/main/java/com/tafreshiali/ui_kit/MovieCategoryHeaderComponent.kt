package com.tafreshiali.ui_kit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.ui_kit.animations.shimmerEffect
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme
import com.tafreshiali.ui_kit.design_system.ui.theme.grayscale10ContainerLight


@Composable
fun MovieCategoryHeaderComponent(
    modifier: Modifier = Modifier,
    categoryTitle: String,
    moreTitle: String = "See All"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
    ) {
        Text(text = categoryTitle, style = AppTheme.typography.bodyMediumBold)
        Text(
            text = moreTitle,
            style = AppTheme.typography.bodySmallMedium.copy(color = AppTheme.colorScheme.primary)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCategoryHeaderComponentPreview() {
    MovieCategoryHeaderComponent(categoryTitle = "Recommended for you")
}


@Composable
fun MovieCategoryHeaderShimmerComponent(
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 150.dp, height = 15.dp)
                .background(
                    color = grayscale10ContainerLight,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .shimmerEffect()
        )

        Box(
            modifier = Modifier
                .size(width = 50.dp, height = 15.dp)
                .background(
                    color = grayscale10ContainerLight,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .shimmerEffect()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCategoryHeaderShimmerComponentPreview() {
    MovieCategoryHeaderShimmerComponent()
}

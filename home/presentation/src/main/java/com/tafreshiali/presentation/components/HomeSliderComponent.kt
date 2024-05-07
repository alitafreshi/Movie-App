package com.tafreshiali.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.tafreshiali.domain.model.Result
import com.tafreshiali.presentation.HomeConstance
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeMainImageSliderComponent(
    modifier: Modifier = Modifier,
    imageUrls: List<Result>,
    indicatorColor: Color = AppTheme.colorScheme.primary,
) {

    val pagerState = rememberPagerState(pageCount = { imageUrls.size })
    Column(
        modifier = modifier.padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .height(160.dp),
            state = pagerState
        ) { page ->
            ImageSlideComponent(item = imageUrls[page])
        }
        DotsIndicator(
            dotCount = pagerState.pageCount,
            type = ShiftIndicatorType(
                dotsGraphic = DotGraphic(
                    color = indicatorColor,
                    size = 6.dp
                )
            ),
            dotSpacing = 4.dp,
            pagerState = pagerState
        )
    }
}

@Composable
private fun ImageSlideComponent(
    modifier: Modifier = Modifier,
    item: Result
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .clip(shape = MaterialTheme.shapes.large),
    ) {
        val boxWithConstraintsScope = this
        SubcomposeAsyncImage(
            model = "${HomeConstance.IMAGE_BASE_URL}/original${item.posterPath}",
            contentScale = ContentScale.Crop,
            contentDescription = "home_main_slider",
        ) {
            when (painter.state) {
                AsyncImagePainter.State.Empty -> {}

                is AsyncImagePainter.State.Error -> {}

                is AsyncImagePainter.State.Loading -> {}

                is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            }
        }

        Column(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        colorStops =
                        arrayOf(
                            0.4f to AppTheme.colorScheme.primary,
                            1.0f to Color.Transparent
                        )
                    )
                )
                .size(boxWithConstraintsScope.maxWidth)
        ) {

            var maxDescriptionLineCount by remember {
                mutableIntStateOf(2)
            }

            Text(
                text = item.originalTitle,
                style = AppTheme.typography.bodyExtraLargeBold.copy(color = AppTheme.colorScheme.onPrimary),
                modifier = Modifier
                    .width(boxWithConstraintsScope.maxWidth / 2)
                    .padding(top = 15.dp, start = 16.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = {
                    maxDescriptionLineCount = if (it.lineCount < 2) 3 else 2
                }
            )

            Text(
                text = item.overview,
                style = AppTheme.typography.bodyExtraSmallRegular.copy(color = AppTheme.colorScheme.onPrimary),
                modifier = Modifier
                    .width(boxWithConstraintsScope.maxWidth / 2)
                    .padding(top = 8.dp, start = 16.dp),
                maxLines = maxDescriptionLineCount,
                overflow = TextOverflow.Ellipsis,
            )

            Button(
                onClick = { },
                colors = ButtonColors(
                    containerColor = AppTheme.colorScheme.onPrimary,
                    contentColor = AppTheme.colorScheme.primary,
                    disabledContentColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified
                ),
                modifier = Modifier
                    .requiredHeight(45.dp)
                    .padding(top = 12.dp, start = 16.dp)
            ) {
                Text(text = "Watch Now", style = AppTheme.typography.bodyExtraSmallBold)
            }
        }
    }
}
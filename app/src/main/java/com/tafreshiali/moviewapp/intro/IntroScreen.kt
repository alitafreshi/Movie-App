package com.tafreshiali.moviewapp.intro

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme
import com.tafreshiali.ui_kit.design_system.ui.theme.backgroundLight
import com.tafreshiali.ui_kit.design_system.ui.theme.grayscale10Light
import com.tafreshiali.ui_kit.design_system.ui.theme.grayscale50Light
import com.tafreshiali.ui_kit.design_system.ui.theme.grayscale70Light
import com.tafreshiali.ui_kit.design_system.ui.theme.primaryLight
import com.tafreshiali.ui_kit.design_system.ui.theme.secondaryLight
import com.tafreshiali.ui_kit.design_system.ui.theme.surfaceVariantLight
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import kotlinx.coroutines.launch

data class IntroItem(@DrawableRes val imageRes: Int, val title: String, val description: String)


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroSliderComponent(modifier: Modifier = Modifier, items: List<IntroItem>) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    val isLastPage by remember { derivedStateOf { pagerState.currentPage == items.lastIndex } }
    val animScope = rememberCoroutineScope()
    val density = LocalDensity.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        HorizontalPager(
            modifier = Modifier
                .padding(bottom = 16.dp), state = pagerState, userScrollEnabled = false
        ) { page ->

            if (isLastPage) {
                IntroLastItemComponent(item = items[page])
            } else
                IntroItemComponent(item = items[page])
        }

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp)
            .requiredHeight(52.dp), onClick = {
            if (!isLastPage) {
                animScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }) {
            Text(
                text = if (isLastPage) "Get Started" else "Continue",
                style = AppTheme.typography.bodyLargeSemiBold.copy(
                    color = AppTheme.colorScheme.onPrimary,
                    textMotion = TextMotion.Animated
                )
            )
        }

        AnimatedVisibility(
            visible = isLastPage,
            enter = slideInVertically {
                with(density) {
                    -40.dp.roundToPx()
                }
            } + expandVertically(expandFrom = Alignment.Bottom) + fadeIn(initialAlpha = 0.3f),
        ) {
            TextButton(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .requiredHeight(50.dp), onClick = { /*TODO*/ }) {
                Text(
                    text = "Sign In",
                    style = AppTheme.typography.bodyLargeSemiBold.copy(color = grayscale50Light)
                )
            }
        }

        AnimatedVisibility(visible = !isLastPage, exit = slideOutVertically() + fadeOut()) {
            DotsIndicator(
                modifier = Modifier
                    .padding(top = 41.dp),
                dotCount = pagerState.pageCount,
                type = ShiftIndicatorType(
                    dotsGraphic = DotGraphic(
                        color = primaryLight,
                        size = 6.dp
                    )
                ),
                dotSpacing = 4.dp,
                pagerState = pagerState
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun IntroSliderComponentPreview() {
    IntroSliderComponent(
        items = listOf(
            IntroItem(
                imageRes = com.tafreshiali.ui_kit.R.drawable.intro_1,
                title = "zdljfbnfljknkjldgbfdbfkbflkblfkbfjkb",
                description = "zdfjkkjdfbjkdfkjdfkdjfbkjdfnbkjnasd;ldfb;dglmbldbaldklbklsmdkbblmblkgmbkfgmbkmblkbmlksfblkfmblfkbmlfkmblfkblskblkmmfglbkgfslkflkf"
            ),
            IntroItem(
                imageRes = com.tafreshiali.ui_kit.R.drawable.intro_2,
                title = "zdljfbnfljknkjldgbfdbfkbflkblfkbfjkb",
                description = "zdfjkkjdfbjkdfkjdfkdjfbkjdfnbkjnasd;ldfb;dglmbldbaldklbklsmdkbblmblkgmbkfgmbkmblkbmlksfblkfmblfkbmlfkmblfkblskblkmmfglbkgfslkflkf"
            ),
            IntroItem(
                imageRes = com.tafreshiali.ui_kit.R.drawable.intro_3,
                title = "zdljfbnfljknkjldgbfdbfkbflkblfkbfjkb",
                description = "zdfjkkjdfbjkdfkjdfkdjfbkjdfnbkjnasd;ldfb;dglmbldbaldklbklsmdkbblmblkgmbkfgmbkmblkbmlksfblkfmblfkbmlfkmblfkblskblkmmfglbkgfslkflkf"
            )
        )
    )
}

@Composable
private fun IntroItemComponent(modifier: Modifier = Modifier, item: IntroItem) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = "intro_image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(3 / 4f)
                .clip(RoundedCornerShape(20.dp))
        )
        Text(
            text = item.title,
            style = AppTheme.typography.h5Bold.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp)
        )
        Text(
            text = item.description,
            style = AppTheme.typography.bodyMediumMedium.copy(
                color = grayscale70Light,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun IntroItemComponentPreview() {
    IntroItemComponent(
        item = IntroItem(
            imageRes = com.tafreshiali.ui_kit.R.drawable.intro_1,
            title = "zdljfbnfljknkjldgbfdbfkbflkblfkbfjkb",
            description = "zdfjkkjdfbjkdfkjdfkdjfbkjdfnbkjnasd;ldfb;dglmbldbaldklbklsmdkbblmblkgmbkfgmbkmblkbmlksfblkfmblfkbmlfkmblfkblskblkmmfglbkgfslkflkf"
        )
    )
}


@Composable
private fun IntroLastItemComponent(modifier: Modifier = Modifier, item: IntroItem) {

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .aspectRatio(3 / 4f)
                .clip(RoundedCornerShape(20.dp))
                .background(color = surfaceVariantLight), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = "intro_image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .graphicsLayer { translationY = 100f },
                alignment = Alignment.TopCenter
            )
        }
        Text(
            text = item.title,
            style = AppTheme.typography.h5Bold.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp)
        )
        Text(
            text = item.description,
            style = AppTheme.typography.bodyMediumMedium.copy(
                color = grayscale70Light,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun IntroLastItemComponentPreview() {
    IntroLastItemComponent(
        item = IntroItem(
            imageRes = com.tafreshiali.ui_kit.R.drawable.intro_3,
            title = "zdljfbnfljknkjldgbfdbfkbflkblfkbfjkb",
            description = "zdfjkkjdfbjkdfkjdfkdjfbkjdfnbkjnasd;ldfb;dglmbldbaldklbklsmdkbblmblkgmbkfgmbkmblkbmlksfblkfmblfkbmlfkmblfkblskblkmmfglbkgfslkflkf"
        )
    )
}
package com.tafreshiali.ui_kit

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme
import com.tafreshiali.ui_kit.design_system.ui.theme.primaryLight
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

data class BottomNavItem(
    val deeplink: String,
    val icon: VectorPainter,
    val isSelected: Boolean = false,
    val selectedColor: Color,
    val unSelectedColor: Color
)

@Composable
fun BottomBarComponent(
    modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    onItemSelected: (BottomNavItem) -> Unit
) {
    //UI STUFF
    val navItems = rememberSaveable { mutableStateOf(items) }
    var touchedOffset by remember { mutableStateOf(Offset.Unspecified) }
    var sectionWidth by remember { mutableFloatStateOf(0f) }
    var sectionHeight by remember { mutableFloatStateOf(0f) }
    var centerOfEachSection by remember { mutableFloatStateOf(0f) }


    //Animation Stuff
    val offsetStartX = remember { Animatable(0f) }
    val offsetEndX = remember { Animatable(0f) }

    //Should Launch Once For Initial Setup
    LaunchedEffect(key1 = true) {
        navItems.value.forEachIndexed { index, bottomNavItem ->
            if (bottomNavItem.isSelected) {
                launchAnimation(
                    offsetStartX = offsetStartX,
                    offsetEndX = offsetEndX,
                    index = index,
                    sectionWidth = sectionWidth,
                    centerOfEachSection = centerOfEachSection,
                    bottomNavItem = bottomNavItem
                )
            }
        }
    }

    //Should Launch When touchedOffset changes
    LaunchedEffect(key1 = touchedOffset) {
        if (touchedOffset != Offset.Unspecified) {
            navItems.value = navItems.value.mapIndexed { index, bottomNavItem ->
                return@mapIndexed if (
                    touchedOffset.x in (index * sectionWidth)..((index * sectionWidth) + sectionWidth)
                ) {
                    launchAnimation(
                        offsetStartX = offsetStartX,
                        offsetEndX = offsetEndX,
                        index = index,
                        sectionWidth = sectionWidth,
                        centerOfEachSection = centerOfEachSection,
                        bottomNavItem = bottomNavItem
                    )
                    bottomNavItem.copy(isSelected = true)
                } else {
                    bottomNavItem.copy(isSelected = false)
                }
            }
        }
    }

    LaunchedEffect(key1 = navItems.value) {
        val selectedItem = navItems.value.find { it.isSelected }
        selectedItem?.let(onItemSelected)
    }

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(74.dp)
            .background(color = AppTheme.colorScheme.onPrimary)
            .shadow(1.dp)
            .pointerInput(true) {
                detectTapGestures(onTap = {
                    touchedOffset = it
                })
            }
            .drawWithCache {
                sectionWidth = (size.width / navItems.value.size)
                sectionHeight = size.height / 2
                centerOfEachSection = sectionWidth / 2

                onDrawBehind {
                    navItems.value.forEachIndexed { index, bottomNavItem ->
                        translate(
                            left = ((index * sectionWidth) + centerOfEachSection) - (bottomNavItem.icon.intrinsicSize.width / 2),
                            top = sectionHeight - bottomNavItem.icon.intrinsicSize.height / 2
                        ) {
                            with(bottomNavItem.icon) {
                                draw(
                                    size = bottomNavItem.icon.intrinsicSize,
                                    colorFilter = ColorFilter.tint(if (bottomNavItem.isSelected) bottomNavItem.selectedColor else bottomNavItem.unSelectedColor)
                                )
                            }
                        }

                        if (bottomNavItem.isSelected) {
                            drawLine(
                                color = bottomNavItem.selectedColor,
                                start = Offset(
                                    offsetStartX.value,
                                    (bottomNavItem.icon.intrinsicSize.height * 2) + 6.dp.toPx()
                                ),
                                end = Offset(
                                    offsetEndX.value,
                                    (bottomNavItem.icon.intrinsicSize.height * 2) + 6.dp.toPx()
                                ),
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        }
                    }
                }
            }
    ) {}
}

private suspend fun launchAnimation(
    offsetStartX: Animatable<Float, AnimationVector1D>,
    offsetEndX: Animatable<Float, AnimationVector1D>,
    index: Int,
    sectionWidth: Float,
    centerOfEachSection: Float,
    bottomNavItem: BottomNavItem
) = coroutineScope {
    val startAnimation = async {
        offsetStartX.animateTo(
            targetValue = ((index * sectionWidth) + centerOfEachSection) - (bottomNavItem.icon.intrinsicSize.width / 2),
            animationSpec = tween(
                durationMillis = 200,
                delayMillis = 0
            )
        )
    }
    val ednAnimation = async {
        offsetEndX.animateTo(
            targetValue = ((index * sectionWidth) + centerOfEachSection) - (bottomNavItem.icon.intrinsicSize.width / 2) + bottomNavItem.icon.intrinsicSize.width,
            animationSpec = tween(
                durationMillis = 200,
                delayMillis = 0
            )
        )
    }
    awaitAll(startAnimation, ednAnimation)
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun BottomBarComponentPreview() {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        BottomBarComponent(
            items = listOf(
                BottomNavItem(
                    deeplink = "HomeIcon",
                    icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_home_navigation)),
                    isSelected = true,
                    selectedColor = primaryLight,
                    unSelectedColor = Color.Gray
                ),
                BottomNavItem(
                    deeplink = "SearchIcon",
                    icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_search_navigation)),
                    isSelected = false,
                    selectedColor = primaryLight,
                    unSelectedColor = Color.Gray
                ),
                BottomNavItem(
                    deeplink = "DownloadIcon",
                    icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_download_navigation)),
                    isSelected = false,
                    selectedColor = primaryLight,
                    unSelectedColor = Color.Gray
                ),
                BottomNavItem(
                    deeplink = "ProfileIcon",
                    icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_profile_navigation)),
                    isSelected = false,
                    selectedColor = primaryLight,
                    unSelectedColor = Color.Gray
                )
            ),
            onItemSelected = {}
        )
    }
}
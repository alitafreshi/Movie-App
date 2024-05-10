package com.tafreshiali.ui_kit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme
import com.tafreshiali.ui_kit.design_system.ui.theme.primaryLight

data class BottomNavItem(
    val icon: VectorPainter,
    val isSelected: Boolean = false,
    val selectedColor: Color,
    val unSelectedColor: Color
)

@Composable
fun BottomBarComponent(modifier: Modifier = Modifier, items: List<BottomNavItem>) {
    val navItems = rememberSaveable { mutableStateOf(items) }

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(74.dp)
            .shadow(1.dp)
            .drawWithCache {
                val sectionWidth = (size.width / navItems.value.size)
                val sectionHeight = size.height / 2
                val centerOfEachSection = sectionWidth / 2
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
                                    ((index * sectionWidth) + centerOfEachSection) - (bottomNavItem.icon.intrinsicSize.width / 2),
                                    (bottomNavItem.icon.intrinsicSize.height * 2) + 6.dp.toPx()
                                ),
                                end = Offset(
                                    ((index * sectionWidth) + centerOfEachSection) - (bottomNavItem.icon.intrinsicSize.width / 2) + bottomNavItem.icon.intrinsicSize.width,
                                    (bottomNavItem.icon.intrinsicSize.height * 2) + 6.dp.toPx()
                                ),
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        }
                    }
                }
            }
    ) {

    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun BottomBarComponentPreview() {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        BottomBarComponent(
            items = listOf(
                BottomNavItem(
                    icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_home_navigation)),
                    isSelected = false,
                    selectedColor = primaryLight,
                    unSelectedColor = Color.Gray
                ),
                BottomNavItem(
                    icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_search_navigation)),
                    isSelected = false,
                    selectedColor = primaryLight,
                    unSelectedColor = Color.Gray
                ), BottomNavItem(
                    icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_download_navigation)),
                    isSelected = false,
                    selectedColor = primaryLight,
                    unSelectedColor = Color.Gray
                ),
                BottomNavItem(
                    icon = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_profile_navigation)),
                    isSelected = true,
                    selectedColor = primaryLight,
                    unSelectedColor = Color.Gray
                )
            )
        )
    }
}
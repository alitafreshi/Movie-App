package com.tafreshiali.ui_kit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme
import com.tafreshiali.ui_kit.design_system.ui.theme.primaryLight

@Composable
fun BottomBarComponent(modifier: Modifier = Modifier) {

    val homeIcon =
        rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_home_navigation))

    val searchIcon =
        rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_search_navigation))

    val downloadIcon =
        rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_download_navigation))

    val profileIcon =
        rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_profile_navigation))

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(74.dp)
            .shadow(1.dp)
            .drawWithCache {
                val sectionWidth = (size.width / 4)
                val sectionHeight = size.height / 2
                val centerOfEachSection = sectionWidth / 2

                onDrawBehind {

                   /* drawRect(
                        color = Color.Black,
                        topLeft = Offset(
                            x = 0f,
                            y = sectionHeight - homeIcon.intrinsicSize.height / 2
                        ),
                        size = Size(sectionWidth, sectionHeight - homeIcon.intrinsicSize.height / 2)
                    )

                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(
                            x = sectionWidth,
                            y = sectionHeight - searchIcon.intrinsicSize.height / 2
                        ),
                        size = Size(
                            sectionWidth,
                            sectionHeight - searchIcon.intrinsicSize.height / 2
                        )
                    )

                    drawRect(
                        color = Color.Red,
                        topLeft = Offset(
                            x = sectionWidth * 2,
                            y = sectionHeight - searchIcon.intrinsicSize.height / 2
                        ),
                        size = Size(
                            sectionWidth,
                            sectionHeight - searchIcon.intrinsicSize.height / 2
                        )
                    )

                    drawRect(
                        color = Color.Green,
                        topLeft = Offset(
                            x = sectionWidth * 3,
                            y = sectionHeight - searchIcon.intrinsicSize.height / 2
                        ),
                        size = Size(
                            sectionWidth,
                            sectionHeight - searchIcon.intrinsicSize.height / 2
                        )
                    )*/

                    //HOME ICON
                    translate(
                        centerOfEachSection - (homeIcon.intrinsicSize.width / 2),
                        sectionHeight - homeIcon.intrinsicSize.height / 2
                    ) {
                        with(homeIcon) {
                            draw(homeIcon.intrinsicSize)
                        }
                    }

                    drawLine(
                        color = primaryLight,
                        start = Offset(
                            centerOfEachSection - (homeIcon.intrinsicSize.width / 2),
                            (homeIcon.intrinsicSize.width * 2) + 6.dp.toPx()
                        ),
                        end = Offset(
                            centerOfEachSection - (homeIcon.intrinsicSize.width / 2) + homeIcon.intrinsicSize.width,
                            (homeIcon.intrinsicSize.width * 2) + 6.dp.toPx()
                        ),
                        strokeWidth = 2.dp.toPx(),
                        cap = StrokeCap.Round
                    )


                    //SEARCH ICON
                    translate(
                        (sectionWidth + centerOfEachSection) - (searchIcon.intrinsicSize.width / 2),
                        sectionHeight - searchIcon.intrinsicSize.height / 2
                    ) {
                        with(searchIcon) {
                            draw(searchIcon.intrinsicSize)
                        }
                    }

                    drawLine(
                        color = primaryLight,
                        start = Offset(
                            (sectionWidth + centerOfEachSection) - (searchIcon.intrinsicSize.width / 2),
                            (homeIcon.intrinsicSize.width * 2) + 6.dp.toPx()
                        ),
                        end = Offset(
                            (sectionWidth + centerOfEachSection) - (searchIcon.intrinsicSize.width / 2) +searchIcon.intrinsicSize.width ,
                            (homeIcon.intrinsicSize.width * 2) + 6.dp.toPx()
                        ),
                        strokeWidth = 2.dp.toPx(),
                        cap = StrokeCap.Round
                    )

                    //DOWNLOAD ICON
                    translate(
                        (sectionWidth * 2 + centerOfEachSection) - (downloadIcon.intrinsicSize.width / 2),
                        sectionHeight - downloadIcon.intrinsicSize.height / 2
                    ) {
                        with(downloadIcon) {
                            draw(downloadIcon.intrinsicSize)
                        }
                    }

                    //PROFILE ICON
                    translate(
                        (sectionWidth * 3 + centerOfEachSection) - (profileIcon.intrinsicSize.width / 2),
                        sectionHeight - profileIcon.intrinsicSize.height / 2
                    ) {
                        with(profileIcon) {
                            draw(profileIcon.intrinsicSize)
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
        BottomBarComponent()
    }
}
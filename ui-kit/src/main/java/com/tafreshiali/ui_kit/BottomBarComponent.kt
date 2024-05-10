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
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
                val centerOfEachSection = sectionWidth / 2
                val sectionHeight = size.height / 2

                val centerX1 = centerOfEachSection
                val centerX2 = centerOfEachSection * 2
                val centerX3 = centerOfEachSection * 3
                val centerX4 = centerOfEachSection * 4



                onDrawBehind {

                    //HOME ICON
                    translate(
                        centerX1 - (homeIcon.intrinsicSize.width / 2),
                        sectionHeight - homeIcon.intrinsicSize.height / 2
                    ) {
                        with(homeIcon) {
                            draw(homeIcon.intrinsicSize)
                        }
                    }

                    //SEARCH ICON
                    translate(
                        centerX2 - (searchIcon.intrinsicSize.width / 2),
                        sectionHeight - searchIcon.intrinsicSize.height / 2
                    ) {
                        with(searchIcon) {
                            draw(searchIcon.intrinsicSize)
                        }
                    }

                    //DOWNLOAD ICON
                    translate(
                        centerX3 - (downloadIcon.intrinsicSize.width / 2),
                        sectionHeight - downloadIcon.intrinsicSize.height / 2
                    ) {
                        with(downloadIcon) {
                            draw(downloadIcon.intrinsicSize)
                        }
                    }

                    //PROFILE ICON
                    translate(
                        centerX4 - (profileIcon.intrinsicSize.width / 2),
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
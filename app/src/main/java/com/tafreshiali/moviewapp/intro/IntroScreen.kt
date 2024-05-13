package com.tafreshiali.moviewapp.intro

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.tafreshiali.moviewapp.R
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme
import com.tafreshiali.ui_kit.design_system.ui.theme.grayscale70Light

data class IntroItem(@DrawableRes val imageRes: Int, val title: String, val description: String)

@Composable
private fun IntroItemComponent(modifier: Modifier = Modifier, item: IntroItem) {
    Column(modifier = modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
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
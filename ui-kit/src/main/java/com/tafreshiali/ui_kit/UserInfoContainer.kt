package com.tafreshiali.ui_kit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme

@Composable
fun UserProfileContainer(
    modifier: Modifier = Modifier,
    name: String,
    profileDescription: String
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        CircularImageProfile()
        UserInfoContainer(
            modifier = Modifier.padding(start = 8.dp),
            name = name,
            profileDescription = profileDescription
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserProfileContainerPreview() {
    UserProfileContainer(name = "Mohit", profileDescription = "Let's watch a movie")
}

@Composable
private fun CircularImageProfile(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "user_profile_image",
        modifier = modifier
            .size(44.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
private fun CircularImageProfilePreview() {
    CircularImageProfile()
}

@Composable
fun UserInfoContainer(name: String, profileDescription: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Hi, $name",
            style = AppTheme.typography.bodyMediumBold.copy(textAlign = TextAlign.Start)
        )
        Text(
            text = profileDescription,
            style = AppTheme.typography.bodySmallRegular.copy(textAlign = TextAlign.Start)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserInfoContainerPreview() {
    UserInfoContainer(name = "Mohit", profileDescription = "Let's watch a movie")
}
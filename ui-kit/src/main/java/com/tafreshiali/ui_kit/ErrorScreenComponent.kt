package com.tafreshiali.ui_kit

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme

sealed class ErrorScreenComponentType(@DrawableRes open val icon: Int, open val title: String) {
    data class RetryAble(
        @DrawableRes override val icon: Int = R.drawable.ic_smothing_went_wrong,
        override val title: String,
        val button: @Composable () -> Unit
    ) : ErrorScreenComponentType(icon = icon, title = title)

    data class NotFound(
        @DrawableRes override val icon: Int,
        override val title: String,
        val description: String
    ) : ErrorScreenComponentType(icon = icon, title = title)
}

@Composable
fun ErrorScreenComponent(
    modifier: Modifier = Modifier,
    errorScreenComponentType: ErrorScreenComponentType
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (errorScreenComponentType) {
            is ErrorScreenComponentType.RetryAble -> RetryAbleErrorScreenComponent(
                errorScreenComponentType
            )

            is ErrorScreenComponentType.NotFound -> NotFoundErrorScreenComponent(
                errorScreenComponentType
            )
        }
    }
}


@Composable
private fun NotFoundErrorScreenComponent(type: ErrorScreenComponentType.NotFound) {
    Image(
        imageVector = ImageVector.vectorResource(id = type.icon),
        contentDescription = "error_state_icon"
    )
    Text(
        text = type.title,
        style = AppTheme.typography.bodyExtraLargeBold.copy(
            fontSize = 20.sp,
            lineHeight = 28.sp,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, end = 20.dp, start = 20.dp)
    )

    Text(
        text = type.description,
        style = AppTheme.typography.bodyMediumRegular.copy(textAlign = TextAlign.Center),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, end = 20.dp, start = 20.dp)
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun NotFoundErrorScreenComponentPreview() {
    ErrorScreenComponent(
        modifier = Modifier.fillMaxSize(),
        errorScreenComponentType = ErrorScreenComponentType.NotFound(
            icon = R.drawable.ic_smothing_went_wrong,
            title = "Sorry, we can’t find the movie :’(",
            description = "Lorem ipsum dolor sit amet, consectetur adipisci elit, sed do eiusmod"
        )
    )
}

@Composable
private fun RetryAbleErrorScreenComponent(type: ErrorScreenComponentType.RetryAble) {
    Image(
        imageVector = ImageVector.vectorResource(id = type.icon),
        contentDescription = "error_state_icon"
    )
    Text(
        text = type.title,
        style = AppTheme.typography.bodyExtraLargeBold.copy(
            fontSize = 20.sp,
            lineHeight = 28.sp
        ),
        modifier = Modifier.padding(top = 24.dp, end = 20.dp, start = 20.dp)
    )
    type.button()
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun RetryAbleErrorScreenComponentPreview() {
    ErrorScreenComponent(
        modifier = Modifier.fillMaxSize(),
        errorScreenComponentType = ErrorScreenComponentType.RetryAble(
            icon = R.drawable.ic_smothing_went_wrong,
            title = "Sorry, we can’t find the movie :’(",
            button = {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(
                            top = 16.dp,
                            start = 20.dp,
                            end = 20.dp
                        )
                ) {
                    Text(text = "Retry", style = AppTheme.typography.bodySmallRegular)
                }
            })
    )
}
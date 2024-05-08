package com.tafreshiali.ui_kit

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.tafreshiali.ui_kit.animations.shimmerEffect
import com.tafreshiali.ui_kit.design_system.ui.theme.AppTheme
import com.tafreshiali.ui_kit.design_system.ui.theme.grayscale10ContainerLight
import com.tafreshiali.ui_kit.design_system.ui.theme.onPrimaryLight
import com.tafreshiali.ui_kit.design_system.ui.theme.primaryLight

data class ImageComponentErrorStateProperties(
    val errorBgColor: Color = grayscale10ContainerLight,
    val errorDescription: String = "Something Went Wrong",
    val errorButtonColor: Color = onPrimaryLight,
    val errorButtonContentColor: Color = primaryLight,
    val errorButtonTitle: String = "Retry",
    @DrawableRes val errorIconDrawable: Int = R.drawable.ic_smothing_went_wrong,
    val errorIconSize: Dp = 45.dp
)

sealed interface ImageComponentState {
    data object Success : ImageComponentState
    data object Error : ImageComponentState
    data object Loading : ImageComponentState
}

@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    errorStateProperties: ImageComponentErrorStateProperties = ImageComponentErrorStateProperties(),
    imageUrl: String?,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.Fit,
    onStateChange: (ImageComponentState) -> Unit = {}
) {
    var retryHash by remember { mutableIntStateOf(0) }
    val request = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .setParameter("retry_hash", retryHash)
        .build()

    if (!imageUrl.isNullOrEmpty()) {
        SubcomposeAsyncImage(
            modifier = modifier,
            model = request,
            contentDescription = contentDescription,
            contentScale = contentScale
        ) {
            when (painter.state) {
                is AsyncImagePainter.State.Error -> {
                    onStateChange(ImageComponentState.Error)
                    ImagePreviewErrorState(
                        modifier = modifier,
                        errorStateProperties = errorStateProperties,
                        onRetryClick = {
                            retryHash++
                        })
                }

                is AsyncImagePainter.State.Loading -> {
                    onStateChange(ImageComponentState.Loading)
                    ImagePreviewLoadingState(modifier = modifier)
                }

                is AsyncImagePainter.State.Success -> {
                    onStateChange(ImageComponentState.Success)
                    SubcomposeAsyncImageContent()
                }

                is AsyncImagePainter.State.Empty -> {}
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun ImagePreviewComponentPreview() {
    ImageComponent(
        modifier = Modifier,
        imageUrl = null,
        contentDescription = "ImagePreviewComponentPreview"
    )
}

@Composable
private fun ImagePreviewErrorState(
    modifier: Modifier = Modifier,
    errorStateProperties: ImageComponentErrorStateProperties,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(color = errorStateProperties.errorBgColor)
                .padding(15.dp)
        ),
        contentAlignment = Alignment.Center,
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(errorStateProperties.errorIconDrawable),
                    contentDescription = "image_error_state",
                    modifier = Modifier.size(errorStateProperties.errorIconSize)
                )

                Text(
                    text = errorStateProperties.errorDescription,
                    style = AppTheme.typography.bodySmallMedium
                )

                Button(
                    onClick = onRetryClick,
                    colors = ButtonColors(
                        containerColor = AppTheme.colorScheme.onPrimary,
                        contentColor = AppTheme.colorScheme.primary,
                        disabledContentColor = Color.Unspecified,
                        disabledContainerColor = Color.Unspecified
                    ),
                    modifier = Modifier
                        .requiredHeight(45.dp)
                        .padding(top = 12.dp)
                ) {
                    Text(
                        text = errorStateProperties.errorButtonTitle,
                        style = AppTheme.typography.bodyExtraSmallBold
                    )
                }
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun ImagePreviewErrorStatePreview() {
    ImagePreviewErrorState(
        modifier = Modifier,
        errorStateProperties = ImageComponentErrorStateProperties(),
        onRetryClick = {})
}

@Composable
private fun ImagePreviewLoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxSize()
                .background(color = grayscale10ContainerLight)
                .shimmerEffect()
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun ImagePreviewLoadingStatePreview() {
    ImagePreviewLoadingState()
}
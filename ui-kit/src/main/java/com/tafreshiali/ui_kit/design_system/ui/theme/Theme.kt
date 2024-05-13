package com.tafreshiali.ui_kit.design_system.ui.theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

private val LightColorScheme = AppColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    error = errorLight,
    onError = onErrorLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    success = successLight,
    onSuccess = onSuccessLight,
    warning = warningLight,
    onWarning = onWarningLight,
    statusBar = statusBarColor,
    shimmerLoadingColor = grayscale10ContainerLight
)

private val typography = AppTypography(
    h1Bold = TextStyle(
        fontFamily = Mulish,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 56.sp
    ),
    h2Bold = TextStyle(
        fontFamily = Mulish,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 18.sp
    ),
    h3Bold = TextStyle(
        fontFamily = Mulish,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp
    ),
    h4Bold = TextStyle(
        fontFamily = Mulish,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 32.sp
    ),
    h5Bold = TextStyle(
        fontFamily = Mulish,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 28.sp
    ),
    h6Bold = TextStyle(
        fontFamily = Mulish,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 26.sp
    ),
    h1SemiBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 48.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 56.sp
    ),
    h2SemiBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 40.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 18.sp
    ),
    h3SemiBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 40.sp
    ),
    h4SemiBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp
    ),
    h5SemiBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp
    ),
    h6SemiBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 26.sp
    ),
    h1Medium = TextStyle(
        fontFamily = Mulish,
        fontSize = 48.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 56.sp
    ),
    h2Medium = TextStyle(
        fontFamily = Mulish,
        fontSize = 40.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 18.sp
    ),
    h3Medium = TextStyle(
        fontFamily = Mulish,
        fontSize = 32.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 40.sp
    ),
    h4Medium = TextStyle(
        fontFamily = Mulish,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 32.sp
    ),
    h5Medium = TextStyle(
        fontFamily = Mulish,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 28.sp
    ),
    h6Medium = TextStyle(
        fontFamily = Mulish,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 26.sp
    ),
    bodyExtraLargeBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 26.sp
    ),
    bodyExtraLargeSemiBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 26.sp
    ),
    bodyExtraLargeMedium = TextStyle(
        fontFamily = Mulish,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 26.sp
    ),
    bodyExtraLargeRegular = TextStyle(
        fontFamily = Mulish,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 26.sp
    ),
    bodyLargeBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp
    ),
    bodyLargeSemiBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp
    ),
    bodyLargeMedium = TextStyle(
        fontFamily = Mulish,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp
    ),
    bodyLargeRegular = TextStyle(
        fontFamily = Mulish,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp
    ),
    bodyMediumBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.sp
    ),
    bodyMediumSemiBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 22.sp
    ),
    bodyMediumMedium = TextStyle(
        fontFamily = Mulish,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 22.sp
    ),
    bodyMediumRegular = TextStyle(
        fontFamily = Mulish,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.sp
    ),
    bodySmallBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 20.sp
    ),
    bodySmallSemiBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp
    ),
    bodySmallMedium = TextStyle(
        fontFamily = Mulish,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp
    ),
    bodySmallRegular = TextStyle(
        fontFamily = Mulish,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    ),
    bodyExtraSmallBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 18.sp
    ),
    bodyExtraSmallSemiBold = TextStyle(
        fontFamily = Mulish,
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 18.sp
    ),
    bodyExtraSmallMedium = TextStyle(
        fontFamily = Mulish,
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 18.sp
    ),
    bodyExtraSmallRegular = TextStyle(
        fontFamily = Mulish,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp
    )
)

@Composable
fun AppTheme(
    //darkTheme: Boolean = isSystemInDarkTheme()
    darkTheme: Boolean = true,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val view = LocalView.current

    CompositionLocalProvider(
        LocalAppColorScheme provides LightColorScheme,
        LocalAppTypography provides typography,
        content = content
    )

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = LightColorScheme.statusBar.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
}

object AppTheme {
    val colorScheme: AppColorScheme @Composable get() = LocalAppColorScheme.current
    val typography: AppTypography @Composable get() = LocalAppTypography.current

}
package com.tafreshiali.ui_kit.design_system.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Immutable
data class AppColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val success: Color,
    val onSuccess: Color,
    val error: Color,
    val onError: Color,
    val surface: Color,
    val onSurface: Color,
    val warning: Color,
    val onWarning: Color,
    val statusBar: Color,
    val shimmerLoadingColor: Color
)

@Immutable
data class AppTypography(
    val h1Bold: TextStyle,
    val h2Bold: TextStyle,
    val h3Bold: TextStyle,
    val h4Bold: TextStyle,
    val h5Bold: TextStyle,
    val h6Bold: TextStyle,
    val h1SemiBold: TextStyle,
    val h2SemiBold: TextStyle,
    val h3SemiBold: TextStyle,
    val h4SemiBold: TextStyle,
    val h5SemiBold: TextStyle,
    val h6SemiBold: TextStyle,
    val h1Medium: TextStyle,
    val h2Medium: TextStyle,
    val h3Medium: TextStyle,
    val h4Medium: TextStyle,
    val h5Medium: TextStyle,
    val h6Medium: TextStyle,
    val bodyExtraLargeBold: TextStyle,
    val bodyExtraLargeSemiBold: TextStyle,
    val bodyExtraLargeMedium: TextStyle,
    val bodyExtraLargeRegular: TextStyle,
    val bodyLargeBold: TextStyle,
    val bodyLargeSemiBold: TextStyle,
    val bodyLargeMedium: TextStyle,
    val bodyLargeRegular: TextStyle,
    val bodyMediumBold: TextStyle,
    val bodyMediumSemiBold: TextStyle,
    val bodyMediumMedium: TextStyle,
    val bodyMediumRegular: TextStyle,
    val bodySmallBold: TextStyle,
    val bodySmallSemiBold: TextStyle,
    val bodySmallMedium: TextStyle,
    val bodySmallRegular: TextStyle,
    val bodyExtraSmallBold: TextStyle,
    val bodyExtraSmallSemiBold: TextStyle,
    val bodyExtraSmallMedium: TextStyle,
    val bodyExtraSmallRegular: TextStyle
)


val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        tertiary = Color.Unspecified,
        onTertiary = Color.Unspecified,
        success = Color.Unspecified,
        onSuccess = Color.Unspecified,
        error = Color.Unspecified,
        onError = Color.Unspecified,
        surface = Color.Unspecified,
        onSurface = Color.Unspecified,
        warning = Color.Unspecified,
        onWarning = Color.Unspecified,
        statusBar = Color.Unspecified,
        shimmerLoadingColor = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        h1Bold = TextStyle.Default,
        h2Bold = TextStyle.Default,
        h3Bold = TextStyle.Default,
        h4Bold = TextStyle.Default,
        h5Bold = TextStyle.Default,
        h6Bold = TextStyle.Default,
        h1SemiBold = TextStyle.Default,
        h2SemiBold = TextStyle.Default,
        h3SemiBold = TextStyle.Default,
        h4SemiBold = TextStyle.Default,
        h5SemiBold = TextStyle.Default,
        h6SemiBold = TextStyle.Default,
        h1Medium = TextStyle.Default,
        h2Medium = TextStyle.Default,
        h3Medium = TextStyle.Default,
        h4Medium = TextStyle.Default,
        h5Medium = TextStyle.Default,
        h6Medium = TextStyle.Default,
        bodyExtraLargeBold = TextStyle.Default,
        bodyExtraLargeSemiBold = TextStyle.Default,
        bodyExtraLargeMedium = TextStyle.Default,
        bodyExtraLargeRegular = TextStyle.Default,
        bodyLargeBold = TextStyle.Default,
        bodyLargeSemiBold = TextStyle.Default,
        bodyLargeMedium = TextStyle.Default,
        bodyLargeRegular = TextStyle.Default,
        bodyMediumBold = TextStyle.Default,
        bodyMediumSemiBold = TextStyle.Default,
        bodyMediumMedium = TextStyle.Default,
        bodyMediumRegular = TextStyle.Default,
        bodySmallBold = TextStyle.Default,
        bodySmallSemiBold = TextStyle.Default,
        bodySmallMedium = TextStyle.Default,
        bodySmallRegular = TextStyle.Default,
        bodyExtraSmallBold = TextStyle.Default,
        bodyExtraSmallSemiBold = TextStyle.Default,
        bodyExtraSmallMedium = TextStyle.Default,
        bodyExtraSmallRegular = TextStyle.Default
    )
}
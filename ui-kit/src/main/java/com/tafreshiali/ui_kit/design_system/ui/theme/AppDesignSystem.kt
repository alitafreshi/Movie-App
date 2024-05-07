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
    val onWarning: Color
)
@Immutable
data class AppTypography(
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
        onWarning = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
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
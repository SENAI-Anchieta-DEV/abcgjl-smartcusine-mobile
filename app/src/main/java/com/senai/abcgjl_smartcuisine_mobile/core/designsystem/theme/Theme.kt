package com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.AppTypography
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.backgroundDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.backgroundDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.backgroundDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.backgroundLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.backgroundLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.backgroundLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorContainerDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorContainerDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorContainerDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorContainerLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorContainerLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorContainerLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.errorLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseOnSurfaceDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseOnSurfaceDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseOnSurfaceDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseOnSurfaceLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseOnSurfaceLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseOnSurfaceLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inversePrimaryDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inversePrimaryDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inversePrimaryDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inversePrimaryLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inversePrimaryLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inversePrimaryLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseSurfaceDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseSurfaceDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseSurfaceDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseSurfaceLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseSurfaceLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.inverseSurfaceLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onBackgroundDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onBackgroundDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onBackgroundDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onBackgroundLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onBackgroundLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onBackgroundLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorContainerDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorContainerDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorContainerDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorContainerLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorContainerLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorContainerLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onErrorLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryContainerDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryContainerDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryContainerDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryContainerLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryContainerLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryContainerLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onPrimaryLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryContainerDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryContainerDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryContainerDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryContainerLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryContainerLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryContainerLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSecondaryLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceVariantDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceVariantDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceVariantDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceVariantLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceVariantLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onSurfaceVariantLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryContainerDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryContainerDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryContainerDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryContainerLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryContainerLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryContainerLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.onTertiaryLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineVariantDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineVariantDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineVariantDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineVariantLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineVariantLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.outlineVariantLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryContainerDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryContainerDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryContainerDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryContainerLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryContainerLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryContainerLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.primaryLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.scrimDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.scrimDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.scrimDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.scrimLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.scrimLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.scrimLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryContainerDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryContainerDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryContainerDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryContainerLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryContainerLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryContainerLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.secondaryLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceBrightDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceBrightDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceBrightDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceBrightLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceBrightLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceBrightLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighestDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighestDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighestDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighestLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighestLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerHighestLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowestDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowestDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowestDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowestLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowestLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceContainerLowestLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceDimDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceDimDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceDimDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceDimLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceDimLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceDimLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceVariantDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceVariantDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceVariantDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceVariantLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceVariantLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.surfaceVariantLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryContainerDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryContainerDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryContainerDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryContainerLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryContainerLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryContainerLightMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryDark
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryDarkHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryDarkMediumContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryLight
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryLightHighContrast
import com.senai.abcgjl_smartcuisine_mobile.core.designsystem.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun SmartCuisineTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}


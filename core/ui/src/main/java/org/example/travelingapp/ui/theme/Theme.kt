package org.example.travelingapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = meridian_light_primary,
    onPrimary = meridian_light_onPrimary,
    primaryContainer = meridian_light_primaryContainer,
    onPrimaryContainer = meridian_light_onPrimaryContainer,
    secondary = meridian_light_secondary,
    onSecondary = meridian_light_onSecondary,
    secondaryContainer = meridian_light_secondaryContainer,
    onSecondaryContainer = meridian_light_onSecondaryContainer,
    tertiary = meridian_light_tertiary,
    onTertiary = meridian_light_onTertiary,
    tertiaryContainer = meridian_light_tertiaryContainer,
    onTertiaryContainer = meridian_light_onTertiaryContainer,
    background = meridian_light_background,
    onBackground = meridian_light_onBackground,
    surface = meridian_light_surface,
    onSurface = meridian_light_onSurface,
    surfaceVariant = meridian_light_surfaceVariant,
    onSurfaceVariant = meridian_light_onSurfaceVariant,
    outline = meridian_light_outline,
    outlineVariant = meridian_light_outlineVariant,
    error = meridian_light_error,
    onError = meridian_light_onError,
    errorContainer = meridian_light_errorContainer,
    onErrorContainer = meridian_light_onErrorContainer,
    inverseSurface = meridian_light_inverseSurface,
    inverseOnSurface = meridian_light_inverseOnSurface,
    inversePrimary = meridian_light_inversePrimary,
    surfaceTint = meridian_light_surfaceTint
)

private val DarkColorScheme = darkColorScheme(
    primary = meridian_dark_primary,
    onPrimary = meridian_dark_onPrimary,
    primaryContainer = meridian_dark_primaryContainer,
    onPrimaryContainer = meridian_dark_onPrimaryContainer,
    secondary = meridian_dark_secondary,
    onSecondary = meridian_dark_onSecondary,
    secondaryContainer = meridian_dark_secondaryContainer,
    onSecondaryContainer = meridian_dark_onSecondaryContainer,
    tertiary = meridian_dark_tertiary,
    onTertiary = meridian_dark_onTertiary,
    tertiaryContainer = meridian_dark_tertiaryContainer,
    onTertiaryContainer = meridian_dark_onTertiaryContainer,
    background = meridian_dark_background,
    onBackground = meridian_dark_onBackground,
    surface = meridian_dark_surface,
    onSurface = meridian_dark_onSurface,
    surfaceVariant = meridian_dark_surfaceVariant,
    onSurfaceVariant = meridian_dark_onSurfaceVariant,
    outline = meridian_dark_outline,
    outlineVariant = meridian_dark_outlineVariant,
    error = meridian_dark_error,
    onError = meridian_dark_onError,
    errorContainer = meridian_dark_errorContainer,
    onErrorContainer = meridian_dark_onErrorContainer,
    inverseSurface = meridian_dark_inverseSurface,
    inverseOnSurface = meridian_dark_inverseOnSurface,
    inversePrimary = meridian_dark_inversePrimary,
    surfaceTint = meridian_dark_surfaceTint
)

@Composable
fun TravelingAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val travelColors = if (darkTheme) DarkTravelColors else LightTravelColors

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = false
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    CompositionLocalProvider(LocalTravelColors provides travelColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

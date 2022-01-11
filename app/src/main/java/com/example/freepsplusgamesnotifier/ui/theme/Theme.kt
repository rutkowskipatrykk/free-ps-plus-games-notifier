package com.example.freepsplusgamesnotifier.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = nero,
    primaryVariant = black,
    secondary = niagara,
    secondaryVariant = pineGreen,
    background = nero,
    surface = nightRider,
    onPrimary = mortar,
    onSecondary = whiteSmoke,
    onBackground = whiteSmoke,
    onSurface = black,
)

private val LightColorPalette = lightColors(
    primary = freeSpeechBlue,
    primaryVariant = freeSpeechBlue,
    secondary = niagara,
    secondaryVariant = pineGreen,
    background = whiteSmoke,
    surface = whiteSmokeDark,
    onPrimary = whiteSmoke,
    onSecondary = whiteSmoke,
    onBackground = nero,
    onSurface = nero,
)

@Composable
fun FreePsPlusGamesNotifierTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
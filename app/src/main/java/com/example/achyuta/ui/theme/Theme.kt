package com.example.achyuta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Gold,
    secondary = PrimaryBlue,
    tertiary = Gold,

    background = Background,
    surface = CardBackground,

    onPrimary = Background,
    onSecondary = WhiteText,
    onTertiary = Background,

    onBackground = WhiteText,
    onSurface = WhiteText
)

private val LightColorScheme = lightColorScheme(
    primary = Gold,
    secondary = PrimaryBlue,
    tertiary = Gold,

    background = Background,
    surface = CardBackground,

    onPrimary = Background,
    onSecondary = WhiteText,
    onTertiary = Background,

    onBackground = WhiteText,
    onSurface = WhiteText
)

@Composable
fun AchyutaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (darkTheme) DarkColorScheme
        else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
package com.wyao.dribbbojetpackcompose.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColorPalette = DribbboColors(
    background = white2,
    textPrimary = black3,
)

private val DarkColorPalette = DribbboColors(
    background = black2,
    textPrimary = white4,
)

private val LocalDribbboColor = compositionLocalOf { LightColorPalette }

@Stable
object DribbboTheme {
    val colors: DribbboColors
        @Composable
        get() = LocalDribbboColor.current

    enum class Theme {
        Light,
        Dark
    }
}

@Stable
class DribbboColors(
    background: Color,
    textPrimary: Color
)
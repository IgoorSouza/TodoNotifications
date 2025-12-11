package com.igor.todonotifications.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BackgroundStartColor = Color(0xFF8EC5FC)
val BackgroundEndColor = Color(0xFFE0C3FC)
val PrimaryColor = Color(0xFF6C63FF)
val TextDarkColor = Color(0xFF333333)
val TextSecondaryColor = Color(0xFF555555)
val DividerColor = Color(0xFFE0E0E0)

// Dark Colors (New Custom Colors)
val DarkBackgroundStartColor = Color(0xFF1E3C72)
val DarkBackgroundEndColor = Color(0xFF2A5298)
val DarkPrimaryColor = Color(0xFF81C784)
val DarkTextDarkColor = Color(0xFFF0F0F0)
val DarkTextSecondaryColor = Color(0xFFB0B0B0)
val DarkDividerColor = Color(0xFF404040)

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

object AppColors {
    @Composable
    fun backgroundStart(darkTheme: Boolean) = if (darkTheme) DarkBackgroundStartColor else BackgroundStartColor
    @Composable
    fun backgroundEnd(darkTheme: Boolean) = if (darkTheme) DarkBackgroundEndColor else BackgroundEndColor
    @Composable
    fun primary(darkTheme: Boolean) = if (darkTheme) DarkPrimaryColor else PrimaryColor
    @Composable
    fun textDark(darkTheme: Boolean) = if (darkTheme) DarkTextDarkColor else TextDarkColor
    @Composable
    fun textSecondary(darkTheme: Boolean) = if (darkTheme) DarkTextSecondaryColor else TextSecondaryColor
    @Composable
    fun cardBackground(darkTheme: Boolean) = if (darkTheme) Color(0xFF2C2C2C) else Color.White
    @Composable
    fun divider(darkTheme: Boolean) = if (darkTheme) DarkDividerColor else DividerColor
}
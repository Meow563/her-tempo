package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
  primary = MauvePlum,
  onPrimary = Color.White,
  primaryContainer = BlushRoseLight,
  onPrimaryContainer = MauvePlumDark,
  secondary = SageGreen,
  onSecondary = Color.White,
  secondaryContainer = SageGreenLight,
  onSecondaryContainer = SageGreenText,
  tertiary = BlushRose,
  onTertiary = Color.White,
  background = CreamBackground,
  onBackground = TextDark,
  surface = Color.White,
  onSurface = TextDark,
  surfaceVariant = SoftPaper,
  onSurfaceVariant = TextMuted,
  outline = CardGlassBorder
)

@Composable
fun MyApplicationTheme(
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colorScheme = LightColorScheme,
    typography = Typography,
    content = content
  )
}


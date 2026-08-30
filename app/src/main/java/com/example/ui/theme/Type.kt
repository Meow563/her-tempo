package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
  displayLarge = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Bold,
    fontSize = 32.sp,
    lineHeight = 38.sp,
    letterSpacing = (-0.5).sp,
    color = TextDark
  ),
  headlineLarge = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.SemiBold,
    fontSize = 28.sp,
    lineHeight = 34.sp,
    letterSpacing = (-0.2).sp,
    color = TextDark
  ),
  headlineMedium = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.SemiBold,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp,
    color = TextDark
  ),
  headlineSmall = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Medium,
    fontSize = 19.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp,
    color = TextDark
  ),
  titleLarge = TextStyle(
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp,
    color = TextDark
  ),
  titleMedium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 15.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.15.sp,
    color = TextDark
  ),
  bodyLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.2.sp,
    color = TextDark
  ),
  bodyMedium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.25.sp,
    color = TextDark
  ),
  labelLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.SemiBold,
    fontSize = 13.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.3.sp,
    color = TextDark
  ),
  labelMedium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.4.sp,
    color = TextMuted
  ),
  labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 10.sp,
    lineHeight = 14.sp,
    letterSpacing = 0.4.sp,
    color = TextMuted
  )
)

package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BlushRoseLight
import com.example.ui.theme.BlushRosePill
import com.example.ui.theme.CardGlassBackground
import com.example.ui.theme.CardGlassBorder
import com.example.ui.theme.PillRoseGradientEnd
import com.example.ui.theme.PillRoseGradientStart
import com.example.ui.theme.PillSageGradientEnd
import com.example.ui.theme.PillSageGradientStart
import com.example.ui.theme.SageGreenBadge
import com.example.ui.theme.SageGreenLight
import com.example.ui.theme.TextDark

/**
 * Frosted glass card with rounded corners, subtle translucent background and delicate border.
 */
@Composable
fun FrostedGlassCard(
  modifier: Modifier = Modifier,
  cornerRadius: Dp = 26.dp,
  backgroundColor: Color = CardGlassBackground,
  borderColor: Color = CardGlassBorder,
  elevation: Dp = 6.dp,
  content: @Composable BoxScope.() -> Unit
) {
  val shape = RoundedCornerShape(cornerRadius)
  Box(
    modifier = modifier
      .shadow(
        elevation = elevation,
        shape = shape,
        ambientColor = Color(0x14402D34),
        spotColor = Color(0x14402D34)
      )
      .clip(shape)
      .background(backgroundColor)
      .border(BorderStroke(1.2.dp, borderColor), shape = shape)
  ) {
    Box(
      modifier = Modifier.padding(20.dp),
      content = content
    )
  }
}

enum class PillStyle {
  SAGE,
  ROSE
}

/**
 * Gradient detail pill matching "Today's Details" items in the screenshot.
 */
@Composable
fun CycleDetailPill(
  title: String,
  iconContent: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  style: PillStyle = PillStyle.SAGE,
  testTag: String = "detail_pill"
) {
  val (gradientBrush, badgeBgColor) = when (style) {
    PillStyle.SAGE -> Pair(
      Brush.horizontalGradient(
        colors = listOf(
          PillSageGradientStart,
          Color(0xF0FAFCF9),
          PillSageGradientEnd
        )
      ),
      SageGreenLight.copy(alpha = 0.85f)
    )
    PillStyle.ROSE -> Pair(
      Brush.horizontalGradient(
        colors = listOf(
          PillRoseGradientStart,
          Color(0xFDF8F8),
          PillRoseGradientEnd
        )
      ),
      BlushRoseLight.copy(alpha = 0.85f)
    )
  }

  val pillShape = RoundedCornerShape(22.dp)

  Row(
    modifier = modifier
      .fillMaxWidth()
      .testTag(testTag)
      .shadow(
        elevation = 2.dp,
        shape = pillShape,
        ambientColor = Color(0x10000000),
        spotColor = Color(0x10000000)
      )
      .border(
        BorderStroke(1.dp, Color.White.copy(alpha = 0.85f)),
        shape = pillShape
      )
      .background(
        brush = gradientBrush,
        shape = pillShape
      )
      .padding(horizontal = 10.dp, vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    // Circle Badge Icon Container
    Box(
      modifier = Modifier
        .size(34.dp)
        .clip(CircleShape)
        .background(badgeBgColor),
      contentAlignment = Alignment.Center
    ) {
      iconContent()
    }

    Spacer(modifier = Modifier.width(12.dp))

    // Text Description
    Text(
      text = title,
      color = TextDark,
      fontSize = 15.sp,
      fontWeight = FontWeight.Normal,
      lineHeight = 20.sp,
      modifier = Modifier.weight(1f)
    )
  }
}

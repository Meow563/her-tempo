package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.MauveDrop
import com.example.ui.theme.SageGreen
import com.example.ui.theme.TextDark

/**
 * Period droplet icon matching the plum waterdrop in the calendar screenshot.
 */
@Composable
fun PeriodDropIcon(
  modifier: Modifier = Modifier,
  size: Dp = 14.dp,
  tint: Color = MauveDrop
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    val path = Path().apply {
      moveTo(w * 0.5f, h * 0.05f)
      // Right curve to round bottom
      cubicTo(
        w * 0.88f, h * 0.45f,
        w * 0.95f, h * 0.72f,
        w * 0.5f, h * 0.95f
      )
      // Left curve back to top point
      cubicTo(
        w * 0.05f, h * 0.72f,
        w * 0.12f, h * 0.45f,
        w * 0.5f, h * 0.05f
      )
      close()
    }
    drawPath(path = path, color = tint, style = Fill)
  }
}

/**
 * Fertile / Ovulation sprout icon with stem and 2 leaves matching the screenshot.
 */
@Composable
fun SproutIcon(
  modifier: Modifier = Modifier,
  size: Dp = 14.dp,
  tint: Color = SageGreen
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Stem
    drawLine(
      color = tint,
      start = Offset(w * 0.5f, h * 0.95f),
      end = Offset(w * 0.5f, h * 0.35f),
      strokeWidth = w * 0.14f,
      cap = StrokeCap.Round
    )

    // Left leaf
    val leftLeaf = Path().apply {
      moveTo(w * 0.5f, h * 0.55f)
      cubicTo(
        w * 0.15f, h * 0.45f,
        w * 0.05f, h * 0.22f,
        w * 0.18f, h * 0.12f
      )
      cubicTo(
        w * 0.38f, h * 0.18f,
        w * 0.48f, h * 0.35f,
        w * 0.5f, h * 0.55f
      )
      close()
    }
    drawPath(path = leftLeaf, color = tint, style = Fill)

    // Right leaf
    val rightLeaf = Path().apply {
      moveTo(w * 0.5f, h * 0.50f)
      cubicTo(
        w * 0.85f, h * 0.42f,
        w * 0.95f, h * 0.20f,
        w * 0.82f, h * 0.10f
      )
      cubicTo(
        w * 0.62f, h * 0.16f,
        w * 0.52f, h * 0.32f,
        w * 0.5f, h * 0.50f
      )
      close()
    }
    drawPath(path = rightLeaf, color = tint, style = Fill)
  }
}

/**
 * Single leaf icon (as seen on day 28 in the screenshot).
 */
@Composable
fun SingleLeafIcon(
  modifier: Modifier = Modifier,
  size: Dp = 14.dp,
  tint: Color = SageGreen
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    val leafPath = Path().apply {
      moveTo(w * 0.2f, h * 0.85f)
      cubicTo(
        w * 0.05f, h * 0.45f,
        w * 0.35f, h * 0.1f,
        w * 0.85f, h * 0.15f
      )
      cubicTo(
        w * 0.95f, h * 0.65f,
        w * 0.6f, h * 0.95f,
        w * 0.2f, h * 0.85f
      )
      close()
    }
    drawPath(path = leafPath, color = tint, style = Fill)
  }
}

/**
 * Outlined Water Drop Icon for detail pill circle badge
 */
@Composable
fun OutlinedDropIcon(
  modifier: Modifier = Modifier,
  size: Dp = 18.dp,
  tint: Color = TextDark
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    val path = Path().apply {
      moveTo(w * 0.5f, h * 0.12f)
      cubicTo(
        w * 0.86f, h * 0.48f,
        w * 0.92f, h * 0.72f,
        w * 0.5f, h * 0.92f
      )
      cubicTo(
        w * 0.08f, h * 0.72f,
        w * 0.14f, h * 0.48f,
        w * 0.5f, h * 0.12f
      )
      close()
    }
    drawPath(
      path = path,
      color = tint,
      style = Stroke(width = w * 0.09f, cap = StrokeCap.Round, join = StrokeJoin.Round)
    )
  }
}

/**
 * Outlined Sprout / Plant Icon for detail pill circle badge
 */
@Composable
fun OutlinedSproutIcon(
  modifier: Modifier = Modifier,
  size: Dp = 18.dp,
  tint: Color = TextDark
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height
    val strokeWidth = w * 0.09f

    // Center stem
    drawLine(
      color = tint,
      start = Offset(w * 0.5f, h * 0.90f),
      end = Offset(w * 0.5f, h * 0.35f),
      strokeWidth = strokeWidth,
      cap = StrokeCap.Round
    )

    // Left leaf outline
    val leftPath = Path().apply {
      moveTo(w * 0.5f, h * 0.55f)
      cubicTo(
        w * 0.15f, h * 0.48f,
        w * 0.08f, h * 0.25f,
        w * 0.22f, h * 0.15f
      )
      cubicTo(
        w * 0.40f, h * 0.20f,
        w * 0.48f, h * 0.38f,
        w * 0.5f, h * 0.55f
      )
    }
    drawPath(
      path = leftPath,
      color = tint,
      style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )

    // Right leaf outline
    val rightPath = Path().apply {
      moveTo(w * 0.5f, h * 0.50f)
      cubicTo(
        w * 0.85f, h * 0.44f,
        w * 0.92f, h * 0.22f,
        w * 0.78f, h * 0.12f
      )
      cubicTo(
        w * 0.60f, h * 0.18f,
        w * 0.52f, h * 0.35f,
        w * 0.5f, h * 0.50f
      )
    }
    drawPath(
      path = rightPath,
      color = tint,
      style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )
  }
}

/**
 * Outlined Calendar Icon for detail pill circle badge
 */
@Composable
fun OutlinedCalendarIcon(
  modifier: Modifier = Modifier,
  size: Dp = 18.dp,
  tint: Color = TextDark
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height
    val strokeWidth = w * 0.09f

    // Outer rect
    drawRoundRect(
      color = tint,
      topLeft = Offset(w * 0.15f, h * 0.22f),
      size = androidx.compose.ui.geometry.Size(w * 0.7f, h * 0.66f),
      cornerRadius = androidx.compose.ui.geometry.CornerRadius(w * 0.15f, h * 0.15f),
      style = Stroke(width = strokeWidth)
    )

    // Header divider line
    drawLine(
      color = tint,
      start = Offset(w * 0.15f, h * 0.42f),
      end = Offset(w * 0.85f, h * 0.42f),
      strokeWidth = strokeWidth
    )

    // Left hook
    drawLine(
      color = tint,
      start = Offset(w * 0.32f, h * 0.10f),
      end = Offset(w * 0.32f, h * 0.26f),
      strokeWidth = strokeWidth,
      cap = StrokeCap.Round
    )

    // Right hook
    drawLine(
      color = tint,
      start = Offset(w * 0.68f, h * 0.10f),
      end = Offset(w * 0.68f, h * 0.26f),
      strokeWidth = strokeWidth,
      cap = StrokeCap.Round
    )
  }
}

/**
 * Ovulation / Cycle Phase timer icon
 */
@Composable
fun OvulationPhaseIcon(
  modifier: Modifier = Modifier,
  size: Dp = 28.dp,
  tint: Color = MauveDrop
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height
    val strokeWidth = w * 0.14f

    // Outer arc ring (about 270 degrees)
    drawArc(
      color = tint.copy(alpha = 0.5f),
      startAngle = -45f,
      sweepAngle = 270f,
      useCenter = false,
      topLeft = Offset(w * 0.12f, h * 0.12f),
      size = androidx.compose.ui.geometry.Size(w * 0.76f, h * 0.76f),
      style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )

    // Filled quadrant wedge
    val wedgePath = Path().apply {
      moveTo(w * 0.5f, h * 0.5f)
      lineTo(w * 0.88f, h * 0.5f)
      arcTo(
        rect = androidx.compose.ui.geometry.Rect(w * 0.12f, h * 0.12f, w * 0.88f, h * 0.88f),
        startAngleDegrees = 0f,
        sweepAngleDegrees = -90f,
        forceMoveTo = false
      )
      close()
    }
    drawPath(path = wedgePath, color = tint, style = Fill)
  }
}

/**
 * Pregnancy silhouette icon with baby bump and heart
 */
@Composable
fun PregnancySilhouetteIcon(
  modifier: Modifier = Modifier,
  size: Dp = 28.dp,
  tint: Color = MauveDrop
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Head
    drawCircle(
      color = tint,
      radius = w * 0.14f,
      center = Offset(w * 0.58f, h * 0.18f)
    )

    // Body with baby bump
    val bodyPath = Path().apply {
      moveTo(w * 0.52f, h * 0.32f)
      // Chest
      cubicTo(
        w * 0.40f, h * 0.38f,
        w * 0.30f, h * 0.48f,
        w * 0.32f, h * 0.62f
      )
      // Baby bump curve outward
      cubicTo(
        w * 0.22f, h * 0.72f,
        w * 0.32f, h * 0.90f,
        w * 0.56f, h * 0.92f
      )
      // Bottom & Back curve
      cubicTo(
        w * 0.76f, h * 0.92f,
        w * 0.82f, h * 0.75f,
        w * 0.78f, h * 0.60f
      )
      // Back arch to neck
      cubicTo(
        w * 0.74f, h * 0.45f,
        w * 0.66f, h * 0.36f,
        w * 0.52f, h * 0.32f
      )
      close()
    }
    drawPath(path = bodyPath, color = tint, style = Fill)

    // Small white heart inside bump
    val heartPath = Path().apply {
      val hw = w * 0.12f
      val hh = h * 0.12f
      val hx = w * 0.54f
      val hy = h * 0.64f

      moveTo(hx, hy + hh * 0.7f)
      cubicTo(
        hx - hw * 0.8f, hy + hh * 0.2f,
        hx - hw * 0.6f, hy - hh * 0.4f,
        hx, hy - hh * 0.1f
      )
      cubicTo(
        hx + hw * 0.6f, hy - hh * 0.4f,
        hx + hw * 0.8f, hy + hh * 0.2f,
        hx, hy + hh * 0.7f
      )
      close()
    }
    drawPath(path = heartPath, color = Color.White, style = Fill)
  }
}

/**
 * Wellness Lotus 3-petal icon
 */
@Composable
fun WellnessLotusIcon(
  modifier: Modifier = Modifier,
  size: Dp = 28.dp,
  tint: Color = MauveDrop
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Center circular bud / central leaf
    drawCircle(
      color = tint,
      radius = w * 0.11f,
      center = Offset(w * 0.5f, h * 0.32f)
    )

    // Center base leaf / petal
    val centerPetal = Path().apply {
      moveTo(w * 0.5f, h * 0.44f)
      cubicTo(
        w * 0.38f, h * 0.54f,
        w * 0.42f, h * 0.82f,
        w * 0.5f, h * 0.88f
      )
      cubicTo(
        w * 0.58f, h * 0.82f,
        w * 0.62f, h * 0.54f,
        w * 0.5f, h * 0.44f
      )
      close()
    }
    drawPath(path = centerPetal, color = tint, style = Fill)

    // Left petal
    val leftPetal = Path().apply {
      moveTo(w * 0.44f, h * 0.54f)
      cubicTo(
        w * 0.14f, h * 0.40f,
        w * 0.08f, h * 0.64f,
        w * 0.40f, h * 0.82f
      )
      close()
    }
    drawPath(path = leftPetal, color = tint, style = Fill)

    // Right petal
    val rightPetal = Path().apply {
      moveTo(w * 0.56f, h * 0.54f)
      cubicTo(
        w * 0.86f, h * 0.40f,
        w * 0.92f, h * 0.64f,
        w * 0.60f, h * 0.82f
      )
      close()
    }
    drawPath(path = rightPetal, color = tint, style = Fill)
  }
}

/**
 * Google multi-color G icon
 */
@Composable
fun GoogleLogoIcon(
  modifier: Modifier = Modifier,
  size: Dp = 22.dp
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height
    val strokeWidth = w * 0.18f

    // Red arc (top-left)
    drawArc(
      color = Color(0xFFEA4335),
      startAngle = -150f,
      sweepAngle = 90f,
      useCenter = false,
      topLeft = Offset(w * 0.1f, h * 0.1f),
      size = androidx.compose.ui.geometry.Size(w * 0.8f, h * 0.8f),
      style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )

    // Yellow arc (bottom-left)
    drawArc(
      color = Color(0xFFFBBC05),
      startAngle = 120f,
      sweepAngle = 90f,
      useCenter = false,
      topLeft = Offset(w * 0.1f, h * 0.1f),
      size = androidx.compose.ui.geometry.Size(w * 0.8f, h * 0.8f),
      style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )

    // Green arc (bottom)
    drawArc(
      color = Color(0xFF34A853),
      startAngle = 30f,
      sweepAngle = 90f,
      useCenter = false,
      topLeft = Offset(w * 0.1f, h * 0.1f),
      size = androidx.compose.ui.geometry.Size(w * 0.8f, h * 0.8f),
      style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )

    // Blue arc & horizontal bar
    drawArc(
      color = Color(0xFF4285F4),
      startAngle = -60f,
      sweepAngle = 90f,
      useCenter = false,
      topLeft = Offset(w * 0.1f, h * 0.1f),
      size = androidx.compose.ui.geometry.Size(w * 0.8f, h * 0.8f),
      style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )
    drawLine(
      color = Color(0xFF4285F4),
      start = Offset(w * 0.5f, h * 0.5f),
      end = Offset(w * 0.88f, h * 0.5f),
      strokeWidth = strokeWidth,
      cap = StrokeCap.Round
    )
  }
}

/**
 * Apple silhouette icon
 */
@Composable
fun AppleLogoIcon(
  modifier: Modifier = Modifier,
  size: Dp = 22.dp,
  tint: Color = Color.Black
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // Leaf
    val leafPath = Path().apply {
      moveTo(w * 0.52f, h * 0.18f)
      cubicTo(
        w * 0.54f, h * 0.08f,
        w * 0.65f, h * 0.05f,
        w * 0.68f, h * 0.05f
      )
      cubicTo(
        w * 0.68f, h * 0.15f,
        w * 0.58f, h * 0.22f,
        w * 0.52f, h * 0.18f
      )
      close()
    }
    drawPath(path = leafPath, color = tint, style = Fill)

    // Apple body with bite on right side
    val bodyPath = Path().apply {
      moveTo(w * 0.5f, h * 0.30f)
      // Top left shoulder
      cubicTo(w * 0.40f, h * 0.22f, w * 0.22f, h * 0.32f, w * 0.20f, h * 0.50f)
      // Left side down to base
      cubicTo(w * 0.18f, h * 0.68f, w * 0.32f, h * 0.94f, w * 0.44f, h * 0.94f)
      // Bottom dip
      cubicTo(w * 0.50f, h * 0.94f, w * 0.54f, h * 0.88f, w * 0.62f, h * 0.88f)
      // Bottom right
      cubicTo(w * 0.70f, h * 0.88f, w * 0.76f, h * 0.94f, w * 0.82f, h * 0.94f)
      // Right side up with bite indent
      cubicTo(w * 0.90f, h * 0.80f, w * 0.94f, h * 0.68f, w * 0.86f, h * 0.58f)
      // Bite circle cut
      cubicTo(w * 0.82f, h * 0.52f, w * 0.82f, h * 0.44f, w * 0.86f, h * 0.40f)
      // Top right shoulder
      cubicTo(w * 0.78f, h * 0.30f, w * 0.62f, h * 0.24f, w * 0.50f, h * 0.30f)
      close()
    }
    drawPath(path = bodyPath, color = tint, style = Fill)
  }
}

/**
 * Clean linear Drop icon with subtle inner arc
 */
@Composable
fun WelcomeLinearDropIcon(
  modifier: Modifier = Modifier,
  size: Dp = 26.dp,
  tint: Color = Color.White
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height
    val strokeWidth = w * 0.08f

    val dropPath = Path().apply {
      moveTo(w * 0.5f, h * 0.16f)
      cubicTo(
        w * 0.5f, h * 0.16f,
        w * 0.18f, h * 0.52f,
        w * 0.18f, h * 0.68f
      )
      cubicTo(
        w * 0.18f, h * 0.88f,
        w * 0.82f, h * 0.88f,
        w * 0.82f, h * 0.68f
      )
      cubicTo(
        w * 0.82f, h * 0.52f,
        w * 0.5f, h * 0.16f,
        w * 0.5f, h * 0.16f
      )
      close()
    }
    drawPath(path = dropPath, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))
  }
}

/**
 * Linear Blooming Flower / Sunburst Icon for Ovulation Tracker
 */
@Composable
fun OutlinedFlowerSunIcon(
  modifier: Modifier = Modifier,
  size: Dp = 26.dp,
  tint: Color = Color.White
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height
    val strokeWidth = w * 0.07f

    // Center flower head circle
    drawCircle(
      color = tint,
      radius = w * 0.12f,
      center = Offset(w * 0.5f, h * 0.38f),
      style = Stroke(width = strokeWidth)
    )

    // 8 Petals / Sunburst rays around center
    val petalCount = 8
    val cx = w * 0.5f
    val cy = h * 0.38f
    val rInner = w * 0.16f
    val rOuter = w * 0.28f

    for (i in 0 until petalCount) {
      val angle = Math.toRadians((i * 45.0)).toFloat()
      val x1 = cx + kotlin.math.cos(angle) * rInner
      val y1 = cy + kotlin.math.sin(angle) * rInner
      val x2 = cx + kotlin.math.cos(angle) * rOuter
      val y2 = cy + kotlin.math.sin(angle) * rOuter
      drawLine(
        color = tint,
        start = Offset(x1, y1),
        end = Offset(x2, y2),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
      )
    }

    // Stem
    drawLine(
      color = tint,
      start = Offset(w * 0.5f, h * 0.54f),
      end = Offset(w * 0.5f, h * 0.88f),
      strokeWidth = strokeWidth,
      cap = StrokeCap.Round
    )

    // Left leaf
    val leftLeaf = Path().apply {
      moveTo(w * 0.5f, h * 0.72f)
      cubicTo(w * 0.34f, h * 0.64f, w * 0.30f, h * 0.72f, w * 0.5f, h * 0.78f)
    }
    drawPath(path = leftLeaf, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))

    // Right leaf
    val rightLeaf = Path().apply {
      moveTo(w * 0.5f, h * 0.68f)
      cubicTo(w * 0.66f, h * 0.60f, w * 0.70f, h * 0.68f, w * 0.5f, h * 0.74f)
    }
    drawPath(path = rightLeaf, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))
  }
}

/**
 * Linear Mother Silhouette with Baby Bump & Heart
 */
@Composable
fun OutlinedPregnancyIcon(
  modifier: Modifier = Modifier,
  size: Dp = 26.dp,
  tint: Color = Color.White
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height
    val strokeWidth = w * 0.07f

    // Head
    drawCircle(
      color = tint,
      radius = w * 0.11f,
      center = Offset(w * 0.54f, h * 0.22f),
      style = Stroke(width = strokeWidth)
    )

    // Body outline
    val bodyPath = Path().apply {
      moveTo(w * 0.48f, h * 0.34f)
      cubicTo(w * 0.38f, h * 0.42f, w * 0.32f, h * 0.52f, w * 0.34f, h * 0.64f)
      cubicTo(w * 0.26f, h * 0.74f, w * 0.36f, h * 0.88f, w * 0.58f, h * 0.88f)
      cubicTo(w * 0.74f, h * 0.88f, w * 0.78f, h * 0.76f, w * 0.74f, h * 0.62f)
      cubicTo(w * 0.70f, h * 0.48f, w * 0.62f, h * 0.38f, w * 0.48f, h * 0.34f)
    }
    drawPath(path = bodyPath, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))

    // Mini heart inside bump
    val heartPath = Path().apply {
      val hw = w * 0.08f
      val hh = h * 0.08f
      val hx = w * 0.54f
      val hy = h * 0.65f

      moveTo(hx, hy + hh * 0.6f)
      cubicTo(hx - hw * 0.7f, hy + hh * 0.2f, hx - hw * 0.5f, hy - hh * 0.3f, hx, hy - hh * 0.1f)
      cubicTo(hx + hw * 0.5f, hy - hh * 0.3f, hx + hw * 0.7f, hy + hh * 0.2f, hx, hy + hh * 0.6f)
      close()
    }
    drawPath(path = heartPath, color = tint, style = Fill)
  }
}

/**
 * Linear Leaf with Growth Chart Bar Icon
 */
@Composable
fun OutlinedHealthInsightsIcon(
  modifier: Modifier = Modifier,
  size: Dp = 26.dp,
  tint: Color = Color.White
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height
    val strokeWidth = w * 0.07f

    // Leaf outline on left
    val leafPath = Path().apply {
      moveTo(w * 0.18f, h * 0.65f)
      cubicTo(w * 0.18f, h * 0.35f, w * 0.45f, h * 0.30f, w * 0.45f, h * 0.30f)
      cubicTo(w * 0.45f, h * 0.30f, w * 0.45f, h * 0.65f, w * 0.18f, h * 0.65f)
      close()
    }
    drawPath(path = leafPath, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))
    // Leaf vein
    drawLine(
      color = tint,
      start = Offset(w * 0.18f, h * 0.65f),
      end = Offset(w * 0.40f, h * 0.35f),
      strokeWidth = strokeWidth,
      cap = StrokeCap.Round
    )

    // Chart baseline
    drawLine(
      color = tint,
      start = Offset(w * 0.48f, h * 0.82f),
      end = Offset(w * 0.86f, h * 0.82f),
      strokeWidth = strokeWidth,
      cap = StrokeCap.Round
    )

    // Bars
    drawLine(
      color = tint,
      start = Offset(w * 0.56f, h * 0.82f),
      end = Offset(w * 0.56f, h * 0.62f),
      strokeWidth = strokeWidth * 1.2f,
      cap = StrokeCap.Round
    )
    drawLine(
      color = tint,
      start = Offset(w * 0.68f, h * 0.82f),
      end = Offset(w * 0.68f, h * 0.48f),
      strokeWidth = strokeWidth * 1.2f,
      cap = StrokeCap.Round
    )
    drawLine(
      color = tint,
      start = Offset(w * 0.80f, h * 0.82f),
      end = Offset(w * 0.80f, h * 0.35f),
      strokeWidth = strokeWidth * 1.2f,
      cap = StrokeCap.Round
    )

    // Trend arrow going up
    drawLine(
      color = tint,
      start = Offset(w * 0.50f, h * 0.50f),
      end = Offset(w * 0.85f, h * 0.22f),
      strokeWidth = strokeWidth,
      cap = StrokeCap.Round
    )
    drawLine(
      color = tint,
      start = Offset(w * 0.72f, h * 0.22f),
      end = Offset(w * 0.85f, h * 0.22f),
      strokeWidth = strokeWidth,
      cap = StrokeCap.Round
    )
    drawLine(
      color = tint,
      start = Offset(w * 0.85f, h * 0.35f),
      end = Offset(w * 0.85f, h * 0.22f),
      strokeWidth = strokeWidth,
      cap = StrokeCap.Round
    )
  }
}



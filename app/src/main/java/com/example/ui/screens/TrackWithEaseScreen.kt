package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TrackWithEaseScreen(
  onBack: () -> Unit = {},
  onNext: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFCFAF7))
  ) {
    // Scrollable container
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 48.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Top Art Banner Header & Inset Card
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(470.dp)
      ) {
        // Art image container with rounded corners
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(top = 12.dp, start = 12.dp, end = 12.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(Color(0xFFFAF7F2))
        ) {
          Image(
            painter = painterResource(id = R.drawable.personalized_waves_bg_1787988997650),
            contentDescription = "Wave background art",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
          )

          // Floating Circular Back Button
          Surface(
            onClick = onBack,
            modifier = Modifier
              .statusBarsPadding()
              .padding(start = 14.dp, top = 14.dp)
              .size(42.dp)
              .testTag("track_with_ease_back_button"),
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.88f),
            shadowElevation = 3.dp
          ) {
            Box(
              modifier = Modifier.fillMaxSize(),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF2C2228),
                modifier = Modifier.size(20.dp)
              )
            }
          }
        }

        // White Card containing the Track Wheel, overlapping the header
        Card(
          modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(350.dp)
            .shadow(
              elevation = 8.dp,
              shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 24.dp, bottomEnd = 24.dp),
              ambientColor = Color(0x20352028),
              spotColor = Color(0x20352028)
            )
            .testTag("track_wheel_card"),
          shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 24.dp, bottomEnd = 24.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .padding(16.dp),
            contentAlignment = Alignment.Center
          ) {
            // Interactive Cycle & Mood Tracking Ring
            CycleTrackingWheel(
              modifier = Modifier
                .size(260.dp)
                .testTag("cycle_tracking_wheel_canvas")
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(26.dp))

      // Content section
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 440.dp)
          .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Headline: "Track with ease."
        Text(
          text = "Track with ease.",
          fontSize = 40.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF161014),
          textAlign = TextAlign.Center,
          letterSpacing = (-0.5).sp,
          modifier = Modifier.testTag("track_ease_headline")
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Subtitle: "Log your symptoms and\nmoods in seconds."
        Text(
          text = "Log your symptoms and\nmoods in seconds.",
          fontSize = 18.sp,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF3C3037),
          textAlign = TextAlign.Center,
          lineHeight = 25.sp,
          modifier = Modifier.testTag("track_ease_subtitle")
        )

        Spacer(modifier = Modifier.height(38.dp))

        // Primary Action: "Next" Pill Button with soft glow
        Box(
          modifier = Modifier.fillMaxWidth(),
          contentAlignment = Alignment.Center
        ) {
          // Soft ambient shadow glow
          Box(
            modifier = Modifier
              .fillMaxWidth(0.92f)
              .height(52.dp)
              .offset(y = 4.dp)
              .background(
                Brush.radialGradient(
                  colors = listOf(
                    Color(0xFF5A394B).copy(alpha = 0.35f),
                    Color(0xFF5A394B).copy(alpha = 0.15f),
                    Color.Transparent
                  ),
                  radius = 300f
                ),
                shape = RoundedCornerShape(28.dp)
              )
              .blur(14.dp)
          )

          Button(
            onClick = onNext,
            modifier = Modifier
              .fillMaxWidth()
              .height(56.dp)
              .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0x335A394B),
                spotColor = Color(0x335A394B)
              )
              .testTag("track_ease_next_button"),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF5A394B)
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
          ) {
            Text(
              text = "Next",
              fontSize = 18.sp,
              fontWeight = FontWeight.Medium,
              color = Color.White
            )
          }
        }
      }
    }
  }
}

@Composable
private fun CycleTrackingWheel(modifier: Modifier = Modifier) {
  Canvas(modifier = modifier) {
    val center = Offset(size.width / 2f, size.height / 2f)
    val outerRadius = size.width * 0.44f
    val strokeWidth = 32.dp.toPx()
    val ringRadius = outerRadius - strokeWidth / 2f

    val arcRect = Size(ringRadius * 2f, ringRadius * 2f)
    val arcTopLeft = Offset(center.x - ringRadius, center.y - ringRadius)

    // Outer subtle translucent shadow ring
    drawCircle(
      color = Color(0x0C000000),
      radius = outerRadius + 2.dp.toPx(),
      center = center,
      style = Stroke(width = strokeWidth + 4.dp.toPx())
    )

    // 1. Top Section: Menstrual Flow (Deep Rose to Warm Terracotta)
    // Angles: from -100 deg to 30 deg
    drawArc(
      brush = Brush.sweepGradient(
        listOf(
          Color(0xFFB85E68),
          Color(0xFFC7786B),
          Color(0xFFD49767),
          Color(0xFFB85E68)
        ),
        center = center
      ),
      startAngle = -100f,
      sweepAngle = 130f,
      useCenter = false,
      topLeft = arcTopLeft,
      size = arcRect,
      style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )

    // 2. Right to Bottom Section: Ovulation & Follicular (Gold to Sage Green)
    // Angles: from 25 deg to 150 deg
    drawArc(
      brush = Brush.sweepGradient(
        listOf(
          Color(0xFFD49767),
          Color(0xFFCCA56A),
          Color(0xFF88A391),
          Color(0xFF6F927D),
          Color(0xFF88A391)
        ),
        center = center
      ),
      startAngle = 20f,
      sweepAngle = 135f,
      useCenter = false,
      topLeft = arcTopLeft,
      size = arcRect,
      style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )

    // 3. Bottom-Left to Top Section: Luteal / Mood (Sage to Dusty Rose)
    // Angles: from 145 deg to 270 deg
    drawArc(
      brush = Brush.sweepGradient(
        listOf(
          Color(0xFF6F927D),
          Color(0xFFC9A876),
          Color(0xFFD49285),
          Color(0xFFB85E68)
        ),
        center = center
      ),
      startAngle = 145f,
      sweepAngle = 125f,
      useCenter = false,
      topLeft = arcTopLeft,
      size = arcRect,
      style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )

    // Draw precision Node Caps & Icons inside the Ring

    // Node 1 (Top / Menstrual Period Start Node with white border)
    val angleTopNode = -95.0 * PI / 180.0
    val topNodeCenter = Offset(
      center.x + (ringRadius * cos(angleTopNode)).toFloat(),
      center.y + (ringRadius * sin(angleTopNode)).toFloat()
    )
    // Node circle
    drawCircle(
      color = Color(0xFFAF5560),
      radius = strokeWidth / 2f - 1.dp.toPx(),
      center = topNodeCenter
    )
    drawCircle(
      color = Color.White.copy(alpha = 0.95f),
      radius = strokeWidth / 2f - 1.dp.toPx(),
      center = topNodeCenter,
      style = Stroke(width = 2.dp.toPx())
    )
    // Droplet icon inside top node
    drawDroplet(topNodeCenter, 6.5.dp.toPx(), Color.White)

    // Droplet 2 (Medium flow on top right section)
    val angleDrop2 = -35.0 * PI / 180.0
    val drop2Center = Offset(
      center.x + (ringRadius * cos(angleDrop2)).toFloat(),
      center.y + (ringRadius * sin(angleDrop2)).toFloat()
    )
    drawDroplet(drop2Center, 6.dp.toPx(), Color.White.copy(alpha = 0.85f))

    // Node 2 (Right side: Smiley / Mood node)
    val angleRightNode = 25.0 * PI / 180.0
    val rightNodeCenter = Offset(
      center.x + (ringRadius * cos(angleRightNode)).toFloat(),
      center.y + (ringRadius * sin(angleRightNode)).toFloat()
    )
    drawCircle(
      color = Color(0xFFC9955A),
      radius = strokeWidth / 2f - 1.dp.toPx(),
      center = rightNodeCenter
    )
    drawCircle(
      color = Color.White.copy(alpha = 0.9f),
      radius = strokeWidth / 2f - 1.dp.toPx(),
      center = rightNodeCenter,
      style = Stroke(width = 1.5.dp.toPx())
    )
    drawSmileyFace(rightNodeCenter, 7.dp.toPx(), Color.White)

    // Node 3 (Bottom: Leaf / Wellness icon)
    val angleBottomNode = 115.0 * PI / 180.0
    val bottomNodeCenter = Offset(
      center.x + (ringRadius * cos(angleBottomNode)).toFloat(),
      center.y + (ringRadius * sin(angleBottomNode)).toFloat()
    )
    drawLeaf(bottomNodeCenter, 7.dp.toPx(), Color.White.copy(alpha = 0.95f))

    // Node 4 (Left side: Second Mood / Calm Smiley node)
    val angleLeftNode = 175.0 * PI / 180.0
    val leftNodeCenter = Offset(
      center.x + (ringRadius * cos(angleLeftNode)).toFloat(),
      center.y + (ringRadius * sin(angleLeftNode)).toFloat()
    )
    drawCircle(
      color = Color(0xFFCCA26B),
      radius = strokeWidth / 2f - 1.dp.toPx(),
      center = leftNodeCenter
    )
    drawCircle(
      color = Color.White.copy(alpha = 0.85f),
      radius = strokeWidth / 2f - 1.dp.toPx(),
      center = leftNodeCenter,
      style = Stroke(width = 1.5.dp.toPx())
    )
    drawSmileyFace(leftNodeCenter, 6.5.dp.toPx(), Color.White)
  }
}

private fun DrawScope.drawDroplet(center: Offset, size: Float, color: Color) {
  val path = Path().apply {
    moveTo(center.x, center.y - size)
    cubicTo(
      center.x + size * 0.7f, center.y - size * 0.1f,
      center.x + size * 0.8f, center.y + size * 0.6f,
      center.x, center.y + size * 0.9f
    )
    cubicTo(
      center.x - size * 0.8f, center.y + size * 0.6f,
      center.x - size * 0.7f, center.y - size * 0.1f,
      center.x, center.y - size
    )
    close()
  }
  drawPath(path, color, style = Fill)
}

private fun DrawScope.drawLeaf(center: Offset, size: Float, color: Color) {
  val path = Path().apply {
    moveTo(center.x - size * 0.7f, center.y + size * 0.7f)
    cubicTo(
      center.x - size * 0.6f, center.y - size * 0.3f,
      center.x + size * 0.1f, center.y - size * 0.8f,
      center.x + size * 0.8f, center.y - size * 0.8f
    )
    cubicTo(
      center.x + size * 0.8f, center.y + size * 0.1f,
      center.x + size * 0.3f, center.y + size * 0.6f,
      center.x - size * 0.7f, center.y + size * 0.7f
    )
    close()
  }
  drawPath(path, color, style = Fill)
}

private fun DrawScope.drawSmileyFace(center: Offset, radius: Float, color: Color) {
  // Eyes
  drawCircle(
    color = color,
    radius = 1.2.dp.toPx(),
    center = Offset(center.x - radius * 0.38f, center.y - radius * 0.22f)
  )
  drawCircle(
    color = color,
    radius = 1.2.dp.toPx(),
    center = Offset(center.x + radius * 0.38f, center.y - radius * 0.22f)
  )

  // Smile arc
  val smileRect = Size(radius * 1.0f, radius * 0.8f)
  val smileTopLeft = Offset(center.x - radius * 0.5f, center.y - radius * 0.15f)
  drawArc(
    color = color,
    startAngle = 20f,
    sweepAngle = 140f,
    useCenter = false,
    topLeft = smileTopLeft,
    size = smileRect,
    style = Stroke(width = 1.4.dp.toPx(), cap = StrokeCap.Round)
  )
}

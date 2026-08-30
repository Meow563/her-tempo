package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.CreamBackground
import com.example.ui.theme.MauvePlum
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import java.util.Locale
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun BbtLogScreen(
  onBack: () -> Unit,
  modifier: Modifier = Modifier,
  initialTemperature: Float = 97.9f,
  initialTime: String = "7:30 AM",
  dateSubtitle: String = "Today, Oct 26 - Cycle Day 14",
  onSaveBbt: (temperature: Float, time: String) -> Unit = { _, _ -> }
) {
  var temperature by remember { mutableFloatStateOf(initialTemperature) }
  var logTime by remember { mutableStateOf(initialTime) }
  var isLogged by remember { mutableStateOf(false) }

  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFAF7F2))
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Header Artwork with Back Arrow & "Basal Body Temperature" Title
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(170.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_bbt_header_bg_1787903127055),
          contentDescription = "Basal Body Temperature Header Art",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )

        // Gradient overlay for readability
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color(0x15FFFFFF),
                  Color(0x00FFFFFF),
                  Color(0x90FAF7F2)
                )
              )
            )
        )

        // Top Back Button
        IconButton(
          onClick = onBack,
          modifier = Modifier
            .statusBarsPadding()
            .padding(start = 8.dp, top = 4.dp)
            .size(48.dp)
            .testTag("bbt_back_button")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color(0xFF1E171B),
            modifier = Modifier.size(24.dp)
          )
        }

        // Title: Basal Body Temperature
        Text(
          text = "Basal Body Temperature",
          style = MaterialTheme.typography.headlineMedium.copy(
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF1E171B)
          ),
          modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 12.dp)
            .testTag("bbt_screen_title")
        )
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Date & Cycle Subtitle: "Today, Oct 26 - Cycle Day 14"
      Text(
        text = dateSubtitle,
        style = MaterialTheme.typography.titleLarge.copy(
          fontSize = 22.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF1E171B)
        ),
        modifier = Modifier.testTag("bbt_date_subtitle")
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Main Content Cards
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 520.dp)
          .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {

        // Card 1: Radial Temperature Dial Logger Card
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .shadow(
              elevation = 4.dp,
              shape = RoundedCornerShape(24.dp),
              ambientColor = Color(0x1F30232A),
              spotColor = Color(0x1F30232A)
            )
            .testTag("bbt_dial_card"),
          shape = RoundedCornerShape(24.dp),
          color = Color.White
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 24.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            // Temperature Arc Gauge with Interactive Knob
            BbtGaugeDial(
              temperature = temperature,
              onTemperatureChange = {
                temperature = it
                isLogged = false
              },
              modifier = Modifier.size(240.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Log BBT Button
            Surface(
              onClick = {
                isLogged = true
                onSaveBbt(temperature, logTime)
              },
              modifier = Modifier
                .shadow(
                  elevation = 2.dp,
                  shape = RoundedCornerShape(14.dp),
                  ambientColor = Color(0x20DE9E8E),
                  spotColor = Color(0x20DE9E8E)
                )
                .testTag("log_bbt_button"),
              shape = RoundedCornerShape(14.dp),
              color = if (isLogged) Color(0xFFE2D6D0) else Color(0xFFF3D4C5)
            ) {
              Text(
                text = if (isLogged) "✓ BBT Logged" else "Log BBT",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2C2228),
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
              )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Log Time Pill Badge (7:30 AM)
            Surface(
              onClick = {
                // Toggle sample times for testing
                logTime = if (logTime == "7:30 AM") "7:00 AM" else "7:30 AM"
              },
              shape = RoundedCornerShape(12.dp),
              color = Color(0xFFEBF1F5),
              modifier = Modifier.testTag("bbt_time_badge")
            ) {
              Text(
                text = logTime,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2B3A42),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
              )
            }
          }
        }

        // Card 2: Temperature Trends (Last 30 Days) Chart Card
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .shadow(
              elevation = 4.dp,
              shape = RoundedCornerShape(24.dp),
              ambientColor = Color(0x1F30232A),
              spotColor = Color(0x1F30232A)
            )
            .testTag("bbt_trends_card"),
          shape = RoundedCornerShape(24.dp),
          color = Color.White
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 20.dp, vertical = 20.dp)
          ) {
            Text(
              text = "Temperature Trends (Last 30 Days)",
              fontSize = 20.sp,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Normal,
              color = Color(0xFF1E171B)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Chart Canvas
            BbtTrendLineChart(
              modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .testTag("bbt_line_chart")
            )
          }
        }

        Spacer(modifier = Modifier.height(100.dp))
      }
    }
  }
}

/**
 * Custom Arc Dial showing Temperature (96.5°F - 99.0°F) with smooth drag thumb
 */
@Composable
private fun BbtGaugeDial(
  temperature: Float,
  onTemperatureChange: (Float) -> Unit,
  modifier: Modifier = Modifier
) {
  val minTemp = 96.5f
  val maxTemp = 99.0f
  val normalized = ((temperature - minTemp) / (maxTemp - minTemp)).coerceIn(0f, 1f)

  // Arc spans from 140 degrees to 400 degrees (260 degree sweep)
  val startAngle = 140f
  val sweepAngle = 260f

  val animatedAngle by animateFloatAsState(
    targetValue = startAngle + (normalized * sweepAngle),
    label = "dial_thumb_anim"
  )

  Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
  ) {
    Canvas(
      modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
          detectDragGestures { change, _ ->
            val center = Offset(size.width / 2f, size.height / 2f)
            val touch = change.position
            val dx = touch.x - center.x
            val dy = touch.y - center.y
            var angle = Math.toDegrees(atan2(dy.toDouble(), dx.toDouble())).toFloat()
            if (angle < 0) angle += 360f

            // Map angle back to temp
            var progress = (angle - startAngle) / sweepAngle
            if (progress < 0f) progress += 360f / sweepAngle
            if (progress in 0f..1f) {
              val newTemp = minTemp + (progress * (maxTemp - minTemp))
              val rounded = (Math.round(newTemp * 10f) / 10f)
              onTemperatureChange(rounded)
            }
          }
        }
    ) {
      val strokeWidth = 14.dp.toPx()
      val arcSize = Size(size.width - strokeWidth * 2, size.height - strokeWidth * 2)
      val topLeft = Offset(strokeWidth, strokeWidth)

      // Background Track (soft cream/champagne)
      drawArc(
        color = Color(0xFFF1EAE2),
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        topLeft = topLeft,
        size = arcSize,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
      )

      // Active Arc Gradient (Soft Sky Blue to Warm Rose)
      val gradientBrush = Brush.sweepGradient(
        listOf(
          Color(0xFF8EA9B6),
          Color(0xFFB1C8D2),
          Color(0xFFE4CFBE),
          Color(0xFFE8C8B5)
        ),
        center = Offset(size.width / 2f, size.height / 2f)
      )

      val activeSweep = (normalized * sweepAngle).coerceAtLeast(1f)

      drawArc(
        brush = gradientBrush,
        startAngle = startAngle,
        sweepAngle = activeSweep,
        useCenter = false,
        topLeft = topLeft,
        size = arcSize,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
      )

      // Calculate Thumb position
      val radius = (size.width - strokeWidth * 2) / 2f
      val angleRad = Math.toRadians(animatedAngle.toDouble())
      val thumbCenterX = (size.width / 2f) + (radius * cos(angleRad)).toFloat()
      val thumbCenterY = (size.height / 2f) + (radius * sin(angleRad)).toFloat()

      // Thumb Outer Drop Shadow & Border
      drawCircle(
        color = Color(0x33000000),
        radius = 15.dp.toPx(),
        center = Offset(thumbCenterX, thumbCenterY + 2.dp.toPx())
      )

      drawCircle(
        color = Color.White,
        radius = 13.dp.toPx(),
        center = Offset(thumbCenterX, thumbCenterY)
      )

      drawCircle(
        color = Color(0xFFFAF7F2),
        radius = 8.dp.toPx(),
        center = Offset(thumbCenterX, thumbCenterY)
      )
    }

    // Center Display: 97.9°F
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
      modifier = Modifier.padding(bottom = 16.dp)
    ) {
      Text(
        text = String.format(Locale.US, "%.1f°F", temperature),
        fontSize = 42.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Serif,
        color = Color(0xFF1E171B),
        letterSpacing = (-0.5).sp,
        textAlign = TextAlign.Center
      )
    }
  }
}

/**
 * 30-day BBT Trend Line Chart matching the exact visual styling
 */
@Composable
private fun BbtTrendLineChart(modifier: Modifier = Modifier) {
  // Sample 30-day realistic biphasic cycle temperatures
  val temperatures = remember {
    listOf(
      97.26f, 97.27f, 97.23f, 97.16f, 97.36f, 97.10f, 97.18f,
      97.37f, 97.33f, 97.17f, 97.31f, 97.27f, 97.38f, 97.50f,
      97.41f, 97.35f, 97.56f, 97.49f, 97.40f, 97.34f
    )
  }

  val yLabels = listOf("97.6", "97.4", "97.2", "97.0")
  val xLabels = listOf("Oct 1", "Oct 8", "Oct 15", "Oct 22")

  Column(modifier = modifier) {
    BoxWithConstraints(modifier = Modifier.weight(1f).fillMaxWidth()) {
      val chartWidth = constraints.maxWidth.toFloat()
      val chartHeight = constraints.maxHeight.toFloat()

      Canvas(modifier = Modifier.fillMaxSize()) {
        val yAxisWidth = 36.dp.toPx()
        val plotWidth = chartWidth - yAxisWidth - 8.dp.toPx()
        val plotHeight = chartHeight - 12.dp.toPx()
        val plotLeft = yAxisWidth
        val plotTop = 6.dp.toPx()

        val minY = 97.0f
        val maxY = 97.6f

        // Draw 4 horizontal grid lines
        for (i in 0..3) {
          val y = plotTop + (plotHeight / 3f) * i
          drawLine(
            color = Color(0xFFEBE3DC),
            start = Offset(plotLeft, y),
            end = Offset(plotLeft + plotWidth, y),
            strokeWidth = 1.dp.toPx()
          )
        }

        // Compute points
        val points = temperatures.mapIndexed { idx, temp ->
          val x = plotLeft + (plotWidth / (temperatures.size - 1)) * idx
          val normalizedY = ((temp - minY) / (maxY - minY)).coerceIn(0f, 1f)
          val y = plotTop + plotHeight - (normalizedY * plotHeight)
          Offset(x, y)
        }

        if (points.isNotEmpty()) {
          // Fill gradient path under the curve
          val fillPath = Path().apply {
            moveTo(points.first().x, plotTop + plotHeight)
            points.forEach { lineTo(it.x, it.y) }
            lineTo(points.last().x, plotTop + plotHeight)
            close()
          }

          drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
              colors = listOf(
                Color(0x358FA8B7),
                Color(0x058FA8B7)
              ),
              startY = plotTop,
              endY = plotTop + plotHeight
            )
          )

          // Stroke line path
          val linePath = Path().apply {
            moveTo(points.first().x, points.first().y)
            points.drop(1).forEach { lineTo(it.x, it.y) }
          }

          drawPath(
            path = linePath,
            color = Color(0xFF8FA8B7),
            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
          )

          // Data point circles
          points.forEach { pt ->
            // White fill
            drawCircle(
              color = Color.White,
              radius = 4.dp.toPx(),
              center = pt
            )
            // Slate blue border
            drawCircle(
              color = Color(0xFF8FA8B7),
              radius = 4.dp.toPx(),
              center = pt,
              style = Stroke(width = 1.5.dp.toPx())
            )
          }
        }
      }

      // Y-axis labels positioned vertically
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(top = 0.dp),
        verticalArrangement = Arrangement.SpaceBetween
      ) {
        yLabels.forEach { label ->
          Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF2C2228),
            modifier = Modifier.width(34.dp)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(4.dp))

    // X-axis Date Labels (Oct 1, Oct 8, Oct 15, Oct 22)
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(start = 36.dp, end = 8.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      xLabels.forEach { label ->
        Text(
          text = label,
          fontSize = 12.sp,
          color = Color(0xFF2C2228)
        )
      }
    }
  }
}

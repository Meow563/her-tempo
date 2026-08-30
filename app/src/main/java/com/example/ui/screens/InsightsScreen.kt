package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
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
import com.example.data.DayLogEntity
import com.example.data.UserSettingsEntity

@Composable
fun InsightsScreen(
  userSettings: UserSettingsEntity,
  dayLogs: Map<String, DayLogEntity>,
  onOpenBbtLog: () -> Unit = {},
  onOpenBirthControl: () -> Unit = {},
  onOpenArticle: () -> Unit = {},
  onOpenDiscoveryVideo: () -> Unit = {},
  onOpenPersonalizedInsights: () -> Unit = {},
  onOpenProfile: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFCFAF7))
  ) {
    // Top background wave artwork
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(310.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.personalized_waves_bg_1787988997650),
        contentDescription = "Insights Wave Header",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
      )

      // Top Action Bar with centered title and profile avatar button
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .statusBarsPadding()
          .padding(top = 10.dp, start = 20.dp, end = 20.dp)
      ) {
        // Centered "Insights" Title
        Text(
          text = "Insights",
          fontSize = 34.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF140D12),
          textAlign = TextAlign.Center,
          letterSpacing = (-0.3).sp,
          modifier = Modifier
            .align(Alignment.Center)
            .padding(top = 40.dp)
            .testTag("insights_screen_title")
        )

        // Profile Avatar Button on Top Right
        IconButton(
          onClick = onOpenProfile,
          modifier = Modifier
            .align(Alignment.TopEnd)
            .size(42.dp)
            .clip(CircleShape)
            .testTag("insights_avatar_btn")
        ) {
          Surface(
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.88f),
            shadowElevation = 2.dp,
            modifier = Modifier.size(36.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "Profile",
                tint = Color(0xFF1E151A),
                modifier = Modifier.size(28.dp)
              )
            }
          }
        }
      }
    }

    // Scrollable container with rounded cream body sheet
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 90.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.height(180.dp))

      // Main Off-White / Cream Card Sheet
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 500.dp)
          .padding(horizontal = 14.dp)
          .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(
              topStart = 38.dp,
              topEnd = 38.dp,
              bottomStart = 32.dp,
              bottomEnd = 32.dp
            ),
            ambientColor = Color(0x1A251820),
            spotColor = Color(0x1A251820)
          )
          .testTag("insights_main_sheet"),
        shape = RoundedCornerShape(
          topStart = 38.dp,
          topEnd = 38.dp,
          bottomStart = 32.dp,
          bottomEnd = 32.dp
        ),
        color = Color(0xFFFAF7F2)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 18.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          // Top Sheet Handle Indicator
          Box(
            modifier = Modifier
              .width(44.dp)
              .height(4.dp)
              .background(Color(0xFFD4C9C5), RoundedCornerShape(2.dp))
          )

          Spacer(modifier = Modifier.height(20.dp))

          // Row of 3 Metric Cards
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            // Card 1: Cycle Length
            InsightsMetricCard(
              title = "Cycle\nLength",
              value = "${userSettings.averageCycleLength} Days",
              isValueSerif = true,
              subtitle = "Average",
              modifier = Modifier
                .weight(1f)
                .testTag("card_cycle_length"),
              onClick = onOpenPersonalizedInsights
            )

            // Card 2: Period Duration
            InsightsMetricCard(
              title = "Period\nDuration",
              value = "${userSettings.averagePeriodLength} Days",
              isValueSerif = true,
              subtitle = "Average",
              modifier = Modifier
                .weight(1f)
                .testTag("card_period_duration"),
              onClick = onOpenPersonalizedInsights
            )

            // Card 3: Symptom Trends
            InsightsMetricCard(
              title = "Symptom\nTrends",
              value = "Headaches\nDown",
              isValueSerif = false,
              subtitle = "Last 30 Days",
              modifier = Modifier
                .weight(1f)
                .testTag("card_symptom_trends"),
              onClick = onOpenArticle
            )
          }

          Spacer(modifier = Modifier.height(18.dp))

          // Mood & Energy Card
          Surface(
            modifier = Modifier
              .fillMaxWidth()
              .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color(0x12201018),
                spotColor = Color(0x12201018)
              )
              .testTag("card_mood_energy_chart"),
            shape = RoundedCornerShape(24.dp),
            color = Color.White
          ) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp, bottom = 20.dp, start = 18.dp, end = 18.dp)
            ) {
              Text(
                text = "Mood & Energy",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = Color(0xFF1E151A),
                letterSpacing = (-0.2).sp
              )

              Spacer(modifier = Modifier.height(2.dp))

              Text(
                text = "Over Last 30 Days",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF3E3238)
              )

              Spacer(modifier = Modifier.height(14.dp))

              HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFEFEBE6)
              )

              Spacer(modifier = Modifier.height(16.dp))

              // Custom Harmonic Dual-Wave Curve Chart
              MoodEnergyChartCanvas(
                modifier = Modifier
                  .fillMaxWidth()
                  .height(190.dp)
                  .testTag("mood_energy_canvas")
              )
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Discovery & Video Library Banner Card
          Surface(
            modifier = Modifier
              .fillMaxWidth()
              .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color(0x12201018),
                spotColor = Color(0x12201018)
              )
              .clip(RoundedCornerShape(24.dp))
              .clickable { onOpenDiscoveryVideo() }
              .testTag("insights_discovery_video_banner"),
            shape = RoundedCornerShape(24.dp),
            color = Color(0xFF503244)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .size(46.dp)
                  .clip(CircleShape)
                  .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Outlined.AccountCircle,
                  contentDescription = "Discovery & Video",
                  tint = Color.White,
                  modifier = Modifier.size(24.dp)
                )
              }

              Spacer(modifier = Modifier.width(14.dp))

              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = "Discovery & Video Library",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = "Guided meditations, cycle movement & nutrition →",
                  fontSize = 12.5.sp,
                  color = Color.White.copy(alpha = 0.85f),
                  lineHeight = 16.sp
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun InsightsMetricCard(
  title: String,
  value: String,
  isValueSerif: Boolean,
  subtitle: String,
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {}
) {
  Card(
    modifier = modifier
      .height(154.dp)
      .shadow(
        elevation = 3.dp,
        shape = RoundedCornerShape(22.dp),
        ambientColor = Color(0x12201018),
        spotColor = Color(0x12201018)
      )
      .clickable(onClick = onClick),
    shape = RoundedCornerShape(22.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 8.dp, vertical = 14.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      // Top Title (2 lines)
      Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        color = Color(0xFF1A1217),
        textAlign = TextAlign.Center,
        lineHeight = 17.sp
      )

      // Value in Center
      Text(
        text = value,
        fontSize = if (isValueSerif) 20.sp else 14.sp,
        fontWeight = if (isValueSerif) FontWeight.Normal else FontWeight.Bold,
        fontFamily = if (isValueSerif) FontFamily.Serif else FontFamily.SansSerif,
        color = Color(0xFF1E151A),
        textAlign = TextAlign.Center,
        lineHeight = if (isValueSerif) 24.sp else 17.sp
      )

      // Subtitle at Bottom
      Text(
        text = subtitle,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif,
        color = Color(0xFF8F7E86),
        textAlign = TextAlign.Center
      )
    }
  }
}

@Composable
private fun MoodEnergyChartCanvas(
  modifier: Modifier = Modifier
) {
  Canvas(modifier = modifier) {
    val w = size.width
    val h = size.height

    // 1. Draw subtle horizontal background grid lines
    val gridColor = Color(0xFFECE7E3)
    val gridY1 = h * 0.28f
    val gridY2 = h * 0.58f
    val gridY3 = h * 0.88f

    drawLine(
      color = gridColor,
      start = Offset(0f, gridY1),
      end = Offset(w, gridY1),
      strokeWidth = 1.dp.toPx()
    )
    drawLine(
      color = gridColor,
      start = Offset(0f, gridY2),
      end = Offset(w, gridY2),
      strokeWidth = 1.dp.toPx()
    )
    drawLine(
      color = gridColor,
      start = Offset(0f, gridY3),
      end = Offset(w, gridY3),
      strokeWidth = 1.dp.toPx()
    )

    // Base bottom Y for gradient fills
    val bottomY = h

    // 2. Draw Sage Green Wave (Energy)
    val greenPath = Path().apply {
      moveTo(0f, h * 0.86f)
      cubicTo(
        w * 0.12f, h * 0.65f,
        w * 0.22f, h * 0.62f,
        w * 0.35f, h * 0.74f
      )
      cubicTo(
        w * 0.42f, h * 0.80f,
        w * 0.48f, h * 0.46f,
        w * 0.56f, h * 0.42f
      )
      cubicTo(
        w * 0.65f, h * 0.38f,
        w * 0.74f, h * 0.68f,
        w * 0.84f, h * 0.65f
      )
      cubicTo(
        w * 0.92f, h * 0.62f,
        w * 0.96f, h * 0.52f,
        w, h * 0.50f
      )
    }

    // Sage Green Fill Path (closed to bottom)
    val greenFillPath = Path().apply {
      addPath(greenPath)
      lineTo(w, bottomY)
      lineTo(0f, bottomY)
      close()
    }

    drawPath(
      path = greenFillPath,
      brush = Brush.verticalGradient(
        colors = listOf(
          Color(0xFF85A38F).copy(alpha = 0.38f),
          Color(0xFF85A38F).copy(alpha = 0.12f),
          Color(0xFF85A38F).copy(alpha = 0.02f)
        ),
        startY = h * 0.35f,
        endY = bottomY
      )
    )

    // 3. Draw Rose Wave (Mood)
    val rosePath = Path().apply {
      moveTo(0f, h * 0.66f)
      cubicTo(
        w * 0.14f, h * 0.42f,
        w * 0.26f, h * 0.46f,
        w * 0.38f, h * 0.62f
      )
      cubicTo(
        w * 0.46f, h * 0.72f,
        w * 0.52f, h * 0.82f,
        w * 0.59f, h * 0.78f
      )
      cubicTo(
        w * 0.66f, h * 0.72f,
        w * 0.70f, h * 0.08f,
        w * 0.78f, h * 0.16f
      )
      cubicTo(
        w * 0.86f, h * 0.22f,
        w * 0.90f, h * 0.55f,
        w * 0.96f, h * 0.46f
      )
      lineTo(w, h * 0.38f)
    }

    // Rose Fill Path (closed to bottom)
    val roseFillPath = Path().apply {
      addPath(rosePath)
      lineTo(w, bottomY)
      lineTo(0f, bottomY)
      close()
    }

    drawPath(
      path = roseFillPath,
      brush = Brush.verticalGradient(
        colors = listOf(
          Color(0xFFD48B96).copy(alpha = 0.45f),
          Color(0xFFD48B96).copy(alpha = 0.18f),
          Color(0xFFD48B96).copy(alpha = 0.02f)
        ),
        startY = h * 0.10f,
        endY = bottomY
      )
    )

    // Draw strokes over fills for crisp definition
    drawPath(
      path = greenPath,
      color = Color(0xFF7A9B84),
      style = Stroke(
        width = 2.5.dp.toPx(),
        cap = StrokeCap.Round,
        join = StrokeJoin.Round
      )
    )

    drawPath(
      path = rosePath,
      color = Color(0xFFC07684),
      style = Stroke(
        width = 2.5.dp.toPx(),
        cap = StrokeCap.Round,
        join = StrokeJoin.Round
      )
    )
  }
}

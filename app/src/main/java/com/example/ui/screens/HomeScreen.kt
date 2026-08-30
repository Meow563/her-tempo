package com.example.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.PlayCircleOutline
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DayLogEntity
import com.example.data.UserSettingsEntity
import com.example.ui.components.FrostedGlassCard
import com.example.ui.components.PeriodDropIcon
import com.example.ui.components.SproutIcon
import com.example.ui.theme.BlushRose
import com.example.ui.theme.BlushRoseLight
import com.example.ui.theme.CreamBackground
import com.example.ui.theme.MauvePlum
import com.example.ui.theme.MauvePlumDark
import com.example.ui.theme.SageGreen
import com.example.ui.theme.SageGreenBadge
import com.example.ui.theme.SageGreenLight
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun HomeScreen(
  selectedDate: LocalDate,
  userSettings: UserSettingsEntity,
  todayDetail: DayLogEntity,
  onOpenLog: (LocalDate) -> Unit,
  onGoToCalendar: () -> Unit,
  onOpenAlerts: () -> Unit = {},
  onOpenArticle: () -> Unit = {},
  onOpenDiscoveryVideo: () -> Unit = {},
  onOpenPremium: () -> Unit = {},
  onOpenCommunity: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(CreamBackground)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .statusBarsPadding()
        .padding(horizontal = 20.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.height(12.dp))

      // Top Welcome Header
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Hello, ${userSettings.userName}",
            style = MaterialTheme.typography.displayLarge.copy(
              fontSize = 30.sp,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              color = TextDark
            ),
            modifier = Modifier.testTag("home_greeting")
          )
          Text(
            text = "${selectedDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())}, ${selectedDate.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())} ${selectedDate.dayOfMonth}",
            fontSize = 14.sp,
            color = TextMuted
          )
        }

        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(42.dp)
              .clip(CircleShape)
              .background(Color.White)
              .clickable { onOpenAlerts() }
              .testTag("home_alerts_btn"),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Notifications,
              contentDescription = "Alerts & Notifications",
              tint = MauvePlum,
              modifier = Modifier.size(20.dp)
            )
          }

          Box(
            modifier = Modifier
              .size(42.dp)
              .clip(CircleShape)
              .background(Color(0xFFEFE8EB))
              .clickable { onOpenDiscoveryVideo() }
              .testTag("home_discovery_video_btn"),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Outlined.PlayCircleOutline,
              contentDescription = "Discovery & Video",
              tint = MauvePlumDark,
              modifier = Modifier.size(22.dp)
            )
          }

          Box(
            modifier = Modifier
              .size(42.dp)
              .clip(CircleShape)
              .background(Color(0xFFE8ECE9))
              .clickable { onOpenCommunity() }
              .testTag("home_community_btn"),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Outlined.People,
              contentDescription = "Join Community",
              tint = Color(0xFF384B42),
              modifier = Modifier.size(20.dp)
            )
          }

          Box(
            modifier = Modifier
              .size(42.dp)
              .clip(CircleShape)
              .background(Color(0xFFF3E5AB))
              .clickable { onOpenPremium() }
              .testTag("home_premium_btn"),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Outlined.Star,
              contentDescription = "Premium Subscription",
              tint = Color(0xFFB8860B),
              modifier = Modifier.size(20.dp)
            )
          }

          Box(
            modifier = Modifier
              .size(42.dp)
              .clip(CircleShape)
              .background(Color.White)
              .clickable { onGoToCalendar() },
            contentAlignment = Alignment.Center
          ) {
            SproutIcon(size = 20.dp, tint = SageGreen)
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Cycle Status Ring Card
      FrostedGlassCard(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 500.dp)
          .testTag("cycle_ring_card"),
        cornerRadius = 28.dp,
        elevation = 6.dp
      ) {
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Box(
            modifier = Modifier
              .size(220.dp)
              .padding(16.dp),
            contentAlignment = Alignment.Center
          ) {
            // Animated Cycle Ring
            val progress = (todayDetail.cycleDay.toFloat() / userSettings.averageCycleLength.toFloat()).coerceIn(0f, 1f)
            Canvas(modifier = Modifier.fillMaxSize()) {
              val strokeWidth = 14.dp.toPx()
              // Track background
              drawCircle(
                color = Color(0x338EA795),
                style = Stroke(width = strokeWidth)
              )
              // Progress arc
              drawArc(
                brush = Brush.sweepGradient(
                  listOf(MauvePlum, BlushRose, SageGreen, MauvePlum)
                ),
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
              )
            }

            // Central Ring Content
            Column(
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = "Day ${todayDetail.cycleDay}",
                fontSize = 32.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = TextDark
              )
              Text(
                text = todayDetail.phaseName,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = SageGreenBadge
              )
              Spacer(modifier = Modifier.height(4.dp))
              val daysUntilPeriod = (userSettings.averageCycleLength - todayDetail.cycleDay).coerceAtLeast(1)
              Text(
                text = "Period in $daysUntilPeriod days",
                fontSize = 11.sp,
                color = TextMuted
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          // Quick Action Pill
          Button(
            onClick = { onOpenLog(selectedDate) },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MauvePlum),
            modifier = Modifier
              .fillMaxWidth()
              .height(46.dp)
              .testTag("log_today_btn")
          ) {
            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Log Symptoms & Flow", fontWeight = FontWeight.SemiBold)
          }
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Fertility & Health Outlook
      FrostedGlassCard(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 500.dp),
        cornerRadius = 24.dp
      ) {
        Column(modifier = Modifier.fillMaxWidth()) {
          Text(
            text = "Daily Wellness Insights",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = TextDark
          )

          Spacer(modifier = Modifier.height(12.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            // Chance of pregnancy card
            Column(
              modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(SageGreenLight.copy(alpha = 0.5f))
                .padding(12.dp)
            ) {
              Text("Fertility Chance", fontSize = 12.sp, color = TextMuted)
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                if (todayDetail.isFertile) "High (Ovulation)" else "Low",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = if (todayDetail.isFertile) MauvePlumDark else TextDark
              )
            }

            // Flow status card
            Column(
              modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(BlushRoseLight.copy(alpha = 0.5f))
                .padding(12.dp)
            ) {
              Text("Period Status", fontSize = 12.sp, color = TextMuted)
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                if (todayDetail.isPeriod) "Active (${todayDetail.flowIntensity})" else "None expected",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Wellness Quote / Tip & Nutrition Guide Link
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(14.dp))
              .background(Color.White.copy(alpha = 0.85f))
              .clickable { onOpenArticle() }
              .padding(12.dp)
              .testTag("wellness_nutrition_guide_card"),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(SageGreenBadge),
              contentAlignment = Alignment.Center
            ) {
              SproutIcon(size = 18.dp, tint = Color.White)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "Optimizing Nutrition for Your Luteal Phase",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark
              )
              Text(
                text = "Discover magnesium-rich foods, complex carbs & herbal teas for hormone balance →",
                fontSize = 11.sp,
                color = TextMuted,
                lineHeight = 15.sp
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(100.dp))
    }
  }
}

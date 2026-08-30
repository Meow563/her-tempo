package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.BottomNavBar
import com.example.ui.viewmodel.NavTab

@Composable
fun HealthProfileScreen(
  onBack: () -> Unit,
  activeTab: NavTab = NavTab.HOME,
  onTabSelected: (NavTab) -> Unit = {},
  onAddClick: () -> Unit = {},
  cycleLengthDays: Int = 28,
  periodLengthDays: Int = 5,
  lastPeriodDateText: String = "Oct 12",
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFAF7F2))
  ) {
    // Top background artwork with abstract ribbon waves and curves
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(500.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.partner_sync_no_eye_art_1787935945468),
        contentDescription = "Health Profile Artwork",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
      )

      // Soft gradient blend from artwork into cream canvas
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              colors = listOf(
                Color.Transparent,
                Color.Transparent,
                Color(0x20FAF7F2),
                Color(0x80FAF7F2),
                Color(0xFFFAF7F2)
              ),
              startY = 150f,
              endY = 1350f
            )
          )
      )

      // Circular Back navigation button
      Surface(
        onClick = onBack,
        modifier = Modifier
          .statusBarsPadding()
          .padding(start = 20.dp, top = 16.dp)
          .size(42.dp)
          .testTag("health_profile_back_button"),
        shape = CircleShape,
        color = Color.White.copy(alpha = 0.85f),
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

    // Scrollable Content Column
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 100.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.height(130.dp))

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 22.dp)
      ) {
        // Headline: "Health Profile"
        Text(
          text = "Health Profile",
          fontSize = 36.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF1E171B),
          letterSpacing = (-0.5).sp,
          modifier = Modifier.testTag("health_profile_title")
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Metric Card 1: Average Cycle Length (Translucent Glass)
        HealthMetricCard(
          icon = Icons.Outlined.DarkMode,
          label = "Average Cycle Length",
          value = "$cycleLengthDays Days",
          isTranslucent = true,
          testTag = "metric_cycle_length"
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Metric Card 2: Average Period Length (Translucent Glass)
        HealthMetricCard(
          icon = Icons.Outlined.WaterDrop,
          label = "Average Period Length",
          value = "$periodLengthDays Days",
          isTranslucent = true,
          testTag = "metric_period_length"
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Metric Card 3: Last Period Date (Frosted/White Card)
        HealthMetricCard(
          icon = Icons.Outlined.CalendarToday,
          label = "Last Period Date",
          value = lastPeriodDateText,
          isTranslucent = false,
          testTag = "metric_last_period_date"
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Section Title: "Historical Data"
        Text(
          text = "Historical Data",
          fontSize = 30.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF1E171B),
          letterSpacing = (-0.5).sp,
          modifier = Modifier.testTag("historical_data_title")
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Historical Data Cards
        HistoricalDataCard(
          dateRange = "Sep 14 - Oct 12",
          duration = "(28 days)",
          testTag = "history_item_1"
        )

        Spacer(modifier = Modifier.height(12.dp))

        HistoricalDataCard(
          dateRange = "Aug 17 - Sep 14",
          duration = "(28 days)",
          testTag = "history_item_2"
        )

        Spacer(modifier = Modifier.height(12.dp))

        HistoricalDataCard(
          dateRange = "Jul 20 - Aug 17",
          duration = "(28 days)",
          testTag = "history_item_3"
        )
      }
    }

    // Bottom Navigation Bar matching screenshot
    BottomNavBar(
      activeTab = activeTab,
      onTabSelected = onTabSelected,
      onAddClick = onAddClick,
      modifier = Modifier.align(Alignment.BottomCenter)
    )
  }
}

@Composable
private fun HealthMetricCard(
  icon: ImageVector,
  label: String,
  value: String,
  isTranslucent: Boolean,
  testTag: String
) {
  val backgroundBrush = if (isTranslucent) {
    Brush.verticalGradient(
      colors = listOf(
        Color.White.copy(alpha = 0.45f),
        Color.White.copy(alpha = 0.60f),
        Color.White.copy(alpha = 0.75f)
      )
    )
  } else {
    Brush.verticalGradient(
      colors = listOf(
        Color.White.copy(alpha = 0.88f),
        Color.White.copy(alpha = 0.95f),
        Color.White
      )
    )
  }

  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .shadow(
        elevation = if (isTranslucent) 4.dp else 8.dp,
        shape = RoundedCornerShape(26.dp),
        ambientColor = Color(0x1F30232A),
        spotColor = Color(0x1F30232A)
      )
      .testTag(testTag),
    shape = RoundedCornerShape(26.dp),
    color = Color.Transparent,
    border = androidx.compose.foundation.BorderStroke(
      width = 1.2.dp,
      color = if (isTranslucent) Color.White.copy(alpha = 0.8f) else Color.White.copy(alpha = 0.9f)
    )
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(backgroundBrush)
        .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Icon circular plum badge
        Box(
          modifier = Modifier
            .size(48.dp)
            .shadow(2.dp, CircleShape)
            .background(Color(0xFF5E394A), CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
          )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Label and Large Value
        Column(
          modifier = Modifier.weight(1f)
        ) {
          Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF2C2228)
          )

          Spacer(modifier = Modifier.height(2.dp))

          Text(
            text = value,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E171B)
          )
        }
      }
    }
  }
}

@Composable
private fun HistoricalDataCard(
  dateRange: String,
  duration: String,
  testTag: String
) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .shadow(
        elevation = 4.dp,
        shape = RoundedCornerShape(22.dp),
        ambientColor = Color(0x1830232A),
        spotColor = Color(0x1830232A)
      )
      .testTag(testTag),
    shape = RoundedCornerShape(22.dp),
    color = Color.White
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 20.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = dateRange,
        fontSize = 17.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF1E171B)
      )

      Text(
        text = duration,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF1E171B)
      )
    }
  }
}

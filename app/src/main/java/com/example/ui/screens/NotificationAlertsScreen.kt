package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun NotificationAlertsScreen(
  onBack: () -> Unit,
  activeTab: NavTab = NavTab.HOME,
  onTabSelected: (NavTab) -> Unit = {},
  onAddClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  var periodAlertEnabled by remember { mutableStateOf(true) }
  var fertilityAlertEnabled by remember { mutableStateOf(true) }
  var dailyLogReminderEnabled by remember { mutableStateOf(true) }
  var medicationPillReminderEnabled by remember { mutableStateOf(true) }

  var periodSubtitle by remember { mutableStateOf("Estimated start in 2 days") }
  var fertilitySubtitle by remember { mutableStateOf("High chance of conception") }
  var dailyLogSubtitle by remember { mutableStateOf("Track your symptoms and mood") }
  var medicationSubtitle by remember { mutableStateOf("Take your daily supplement") }

  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFAF7F2))
  ) {
    // Top background artwork
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(420.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.notifications_alert_art_1787933857711),
        contentDescription = "Wellness Flow and Harmony Background Art",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
      )

      // Soft gradient fade at the bottom of the artwork into cream background
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              colors = listOf(
                Color.Transparent,
                Color(0x20FAF7F2),
                Color(0xAAFAF7F2),
                Color(0xFFFAF7F2)
              ),
              startY = 180f,
              endY = 1100f
            )
          )
      )

      // Top Back Button
      Surface(
        onClick = onBack,
        modifier = Modifier
          .statusBarsPadding()
          .padding(start = 16.dp, top = 8.dp)
          .size(38.dp)
          .shadow(
            elevation = 4.dp,
            shape = CircleShape,
            ambientColor = Color(0x33000000),
            spotColor = Color(0x33000000)
          )
          .testTag("alerts_back_button"),
        shape = CircleShape,
        color = Color(0xF0FFFFFF)
      ) {
        Box(contentAlignment = Alignment.Center) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "Back",
            tint = Color(0xFF2C2228),
            modifier = Modifier.size(24.dp)
          )
        }
      }
    }

    // Scrollable Column with Floating Alert Setting Cards
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Offset spacer to position cards elegantly over the lower half of the artwork
      Spacer(modifier = Modifier.height(290.dp))

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 500.dp)
          .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {

        // Card 1: Period Alert
        AlertSettingCard(
          icon = Icons.Outlined.CalendarMonth,
          title = "Period Alert",
          subtitle = periodSubtitle,
          enabled = periodAlertEnabled,
          onCheckedChange = { periodAlertEnabled = it },
          testTag = "period_alert_card",
          switchTestTag = "period_alert_switch"
        )

        // Card 2: Fertility Window
        AlertSettingCard(
          icon = Icons.Default.Spa,
          title = "Fertility Window",
          subtitle = fertilitySubtitle,
          enabled = fertilityAlertEnabled,
          onCheckedChange = { fertilityAlertEnabled = it },
          testTag = "fertility_window_card",
          switchTestTag = "fertility_window_switch"
        )

        // Card 3: Daily Log Reminder
        AlertSettingCard(
          icon = Icons.Default.Edit,
          title = "Daily Log Reminder",
          subtitle = dailyLogSubtitle,
          enabled = dailyLogReminderEnabled,
          onCheckedChange = { dailyLogReminderEnabled = it },
          testTag = "daily_log_reminder_card",
          switchTestTag = "daily_log_reminder_switch"
        )

        // Card 4: Medication/Pill
        AlertSettingCard(
          icon = Icons.Default.Medication,
          title = "Medication/Pill",
          subtitle = medicationSubtitle,
          enabled = medicationPillReminderEnabled,
          onCheckedChange = { medicationPillReminderEnabled = it },
          testTag = "medication_pill_card",
          switchTestTag = "medication_pill_switch"
        )

        Spacer(modifier = Modifier.height(110.dp))
      }
    }

    // Fixed Bottom Navigation Bar
    BottomNavBar(
      activeTab = activeTab,
      onTabSelected = onTabSelected,
      onAddClick = onAddClick,
      modifier = Modifier.align(Alignment.BottomCenter)
    )
  }
}

/**
 * Modern Frosted Alert Setting Card with Icon badge, Title, Subtitle, and Custom Switch
 */
@Composable
private fun AlertSettingCard(
  icon: ImageVector,
  title: String,
  subtitle: String,
  enabled: Boolean,
  onCheckedChange: (Boolean) -> Unit,
  testTag: String,
  switchTestTag: String,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .shadow(
        elevation = 6.dp,
        shape = RoundedCornerShape(22.dp),
        ambientColor = Color(0x1F30232A),
        spotColor = Color(0x1F30232A)
      )
      .testTag(testTag),
    shape = RoundedCornerShape(22.dp),
    color = Color.White.copy(alpha = 0.94f)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 18.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      // Left Icon Badge + Title / Subtitle
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.weight(1f)
      ) {
        // Soft mauve icon container
        Box(
          modifier = Modifier
            .size(46.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(Color(0xFFF1E6E7)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color(0xFF6B485A),
            modifier = Modifier.size(24.dp)
          )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = title,
            fontSize = 17.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1E171B)
          )

          Spacer(modifier = Modifier.height(2.dp))

          Text(
            text = subtitle,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF6E5F67)
          )
        }
      }

      Spacer(modifier = Modifier.width(8.dp))

      // Custom styled Material Switch
      Switch(
        checked = enabled,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
          checkedThumbColor = Color.White,
          checkedTrackColor = Color(0xFF755566), // Deep mauve track matching design
          uncheckedThumbColor = Color.White,
          uncheckedTrackColor = Color(0xFFE5DFD9),
          uncheckedBorderColor = Color.Transparent,
          checkedBorderColor = Color.Transparent
        ),
        modifier = Modifier.testTag(switchTestTag)
      )
    }
  }
}

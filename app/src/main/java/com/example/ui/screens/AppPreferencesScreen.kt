package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.CreamBackground
import com.example.ui.theme.MauvePlum
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted

@Composable
fun AppPreferencesScreen(
  onOpenAlerts: () -> Unit = {},
  onOpenPartnerSync: () -> Unit = {},
  onOpenHealthProfile: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  var temperatureUnit by remember { mutableStateOf("Celsius") } // Celsius or Fahrenheit
  var weightUnit by remember { mutableStateOf("kg") } // kg or lb
  var selectedLanguage by remember { mutableStateOf("English (US)") }
  var selectedRegion by remember { mutableStateOf("United States") }
  var startDayOfWeek by remember { mutableStateOf("Sunday") }
  var showWeekNumbers by remember { mutableStateOf(true) }

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
      // Header Artwork with "App Preferences" Title
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(180.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_pref_header_bg_1787818748033),
          contentDescription = "Preferences Header Art",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )

        // Gradient overlay for smooth readability
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color(0x22FFFFFF),
                  Color(0x00FFFFFF),
                  Color(0x88FAF7F2)
                )
              )
            )
        )

        Text(
          text = "App Preferences",
          style = MaterialTheme.typography.headlineMedium.copy(
            fontSize = 32.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF2C242A)
          ),
          modifier = Modifier
            .align(Alignment.Center)
            .padding(top = 16.dp)
            .testTag("app_preferences_title")
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Content Column for Cards
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 540.dp)
          .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {

        // Card 1: Units (Temperature & Weight segmented toggles)
        PreferencesCard(testTag = "card_units") {
          Text(
            text = "Units",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E171B)
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Temperature Row
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = "Temperature",
              fontSize = 17.sp,
              fontWeight = FontWeight.Normal,
              color = Color(0xFF2B2228)
            )

            // Segmented Pill (Celsius / Fahrenheit)
            SegmentedTogglePill(
              options = listOf("Celsius", "Fahrenheit"),
              selectedOption = temperatureUnit,
              onOptionSelected = { temperatureUnit = it },
              testTag = "temp_segmented_toggle"
            )
          }

          Spacer(modifier = Modifier.height(14.dp))
          HorizontalDivider(color = Color(0xFFEBE3DC), thickness = 1.dp)
          Spacer(modifier = Modifier.height(14.dp))

          // Weight Row
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = "Weight",
              fontSize = 17.sp,
              fontWeight = FontWeight.Normal,
              color = Color(0xFF2B2228)
            )

            // Segmented Pill (kg / lb)
            SegmentedTogglePill(
              options = listOf("kg", "lb"),
              selectedOption = weightUnit,
              onOptionSelected = { weightUnit = it },
              testTag = "weight_segmented_toggle"
            )
          }
        }

        // Card 2: Language & Region
        PreferencesCard(testTag = "card_language_region") {
          Text(
            text = "Language & Region",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E171B)
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Language Item
          PreferenceItemRow(
            title = "Language",
            value = selectedLanguage,
            onClick = {
              selectedLanguage = if (selectedLanguage == "English (US)") "Spanish (ES)" else "English (US)"
            },
            testTag = "pref_language_row"
          )

          Spacer(modifier = Modifier.height(14.dp))
          HorizontalDivider(color = Color(0xFFEBE3DC), thickness = 1.dp)
          Spacer(modifier = Modifier.height(14.dp))

          // Region Item
          PreferenceItemRow(
            title = "Region",
            value = selectedRegion,
            onClick = {
              selectedRegion = if (selectedRegion == "United States") "United Kingdom" else "United States"
            },
            testTag = "pref_region_row"
          )
        }

        // Card 3: Calendar Settings
        PreferencesCard(testTag = "card_calendar_settings") {
          Text(
            text = "Calendar Settings",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E171B)
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Start Day of Week
          PreferenceItemRow(
            title = "Start Day of Week",
            value = startDayOfWeek,
            onClick = {
              startDayOfWeek = if (startDayOfWeek == "Sunday") "Monday" else "Sunday"
            },
            testTag = "pref_start_day_row"
          )

          Spacer(modifier = Modifier.height(14.dp))
          HorizontalDivider(color = Color(0xFFEBE3DC), thickness = 1.dp)
          Spacer(modifier = Modifier.height(14.dp))

          // Show Week Numbers
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .testTag("pref_week_numbers_row"),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = "Show Week Numbers",
              fontSize = 17.sp,
              fontWeight = FontWeight.Normal,
              color = Color(0xFF2B2228)
            )

            Switch(
              checked = showWeekNumbers,
              onCheckedChange = { showWeekNumbers = it },
              colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFFDE9E8E),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFDCCFC7),
                uncheckedBorderColor = Color.Transparent,
                checkedBorderColor = Color.Transparent
              )
            )
          }
        }

        // Card: Notifications & Alerts Preferences
        PreferencesCard(testTag = "card_notifications_alerts") {
          Text(
            text = "Notifications & Reminders",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E171B)
          )

          Spacer(modifier = Modifier.height(14.dp))

          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { onOpenAlerts() }
              .padding(vertical = 4.dp)
              .testTag("pref_open_alerts_row"),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Column {
              Text(
                text = "Cycle Alerts & Reminders",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF2B2228)
              )
              Text(
                text = "Period, fertility, daily logs & medication",
                fontSize = 13.sp,
                color = TextMuted
              )
            }

            Icon(
              imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
              contentDescription = "Configure Alerts",
              tint = Color(0xFFC0AFAF),
              modifier = Modifier.size(24.dp)
            )
          }
        }

        // Card: Health Profile & Sharing
        PreferencesCard(testTag = "card_partner_sync") {
          Text(
            text = "Health Profile & Sharing",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E171B)
          )

          Spacer(modifier = Modifier.height(14.dp))

          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { onOpenHealthProfile() }
              .padding(vertical = 4.dp)
              .testTag("pref_open_health_profile_row"),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Column {
              Text(
                text = "Health Profile",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF2B2228)
              )
              Text(
                text = "Cycle length, period length & historical data",
                fontSize = 13.sp,
                color = TextMuted
              )
            }

            Icon(
              imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
              contentDescription = "Health Profile",
              tint = Color(0xFFC0AFAF),
              modifier = Modifier.size(24.dp)
            )
          }

          Spacer(modifier = Modifier.height(10.dp))
          HorizontalDivider(color = Color(0xFFF0EBE6))
          Spacer(modifier = Modifier.height(10.dp))

          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { onOpenPartnerSync() }
              .padding(vertical = 4.dp)
              .testTag("pref_open_partner_sync_row"),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Column {
              Text(
                text = "Sync with Partner",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF2B2228)
              )
              Text(
                text = "Share phase, symptoms, mood & insights",
                fontSize = 13.sp,
                color = TextMuted
              )
            }

            Icon(
              imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
              contentDescription = "Sync with Partner",
              tint = Color(0xFFC0AFAF),
              modifier = Modifier.size(24.dp)
            )
          }
        }

        // Card 4: Privacy & Data
        PreferencesCard(testTag = "card_privacy_data") {
          Text(
            text = "Privacy & Data",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1E171B)
          )

          Spacer(modifier = Modifier.height(14.dp))

          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { /* Manage permissions */ }
              .padding(vertical = 4.dp)
              .testTag("pref_manage_permissions_row"),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = "Manage Permissions & Data",
              fontSize = 17.sp,
              fontWeight = FontWeight.Normal,
              color = Color(0xFFCF8F82)
            )

            Icon(
              imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
              contentDescription = "Navigate",
              tint = Color(0xFFC0AFAF),
              modifier = Modifier.size(24.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(110.dp))
      }
    }
  }
}

/**
 * Standard elevated white rounded card with soft drop shadow
 */
@Composable
private fun PreferencesCard(
  testTag: String,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .shadow(
        elevation = 3.dp,
        shape = RoundedCornerShape(20.dp),
        ambientColor = Color(0x1A402D34),
        spotColor = Color(0x1A402D34)
      )
      .testTag(testTag),
    shape = RoundedCornerShape(20.dp),
    color = Color.White
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
      content()
    }
  }
}

/**
 * Segmented Pill selector with active raised white capsule and muted container
 */
@Composable
private fun SegmentedTogglePill(
  options: List<String>,
  selectedOption: String,
  onOptionSelected: (String) -> Unit,
  testTag: String,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .height(36.dp)
      .testTag(testTag),
    shape = RoundedCornerShape(10.dp),
    color = Color(0xFFEBE5DF)
  ) {
    Row(
      modifier = Modifier.padding(2.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      options.forEach { option ->
        val isSelected = option == selectedOption
        val bgColor by animateColorAsState(
          targetValue = if (isSelected) Color.White else Color.Transparent,
          label = "seg_bg"
        )
        val textColor = if (isSelected) Color(0xFF1E171B) else Color(0xFF6B5C64)
        val elevation = if (isSelected) 2.dp else 0.dp

        Surface(
          onClick = { onOptionSelected(option) },
          modifier = Modifier
            .shadow(elevation = elevation, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp)),
          shape = RoundedCornerShape(8.dp),
          color = bgColor
        ) {
          Box(
            modifier = Modifier
              .padding(horizontal = 14.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = option,
              fontSize = 14.sp,
              fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
              color = textColor
            )
          }
        }
      }
    }
  }
}

/**
 * Clickable preference row with arrow and value (e.g. -> Sunday or -> English (US))
 */
@Composable
private fun PreferenceItemRow(
  title: String,
  value: String,
  onClick: () -> Unit,
  testTag: String,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .clickable(onClick = onClick)
      .padding(vertical = 2.dp)
      .testTag(testTag),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = title,
      fontSize = 17.sp,
      fontWeight = FontWeight.Normal,
      color = Color(0xFF2B2228)
    )

    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
        contentDescription = null,
        tint = Color(0xFF705E66),
        modifier = Modifier.size(17.dp)
      )

      Spacer(modifier = Modifier.width(8.dp))

      Text(
        text = value,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF2B2228)
      )
    }
  }
}

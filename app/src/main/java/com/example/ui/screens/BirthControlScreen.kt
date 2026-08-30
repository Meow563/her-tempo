package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.example.ui.components.BottomNavBar
import com.example.ui.viewmodel.NavTab

@Composable
fun BirthControlScreen(
  onBack: () -> Unit,
  activeTab: NavTab = NavTab.INSIGHTS,
  onTabSelected: (NavTab) -> Unit = {},
  onAddClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  var isPillTakenToday by remember { mutableStateOf(true) }
  var currentPackDay by remember { mutableIntStateOf(12) }
  val totalPackDays = 28
  val remainingDays = totalPackDays - currentPackDay

  var dailyPillReminderEnabled by remember { mutableStateOf(true) }
  var dailyPillTime by remember { mutableStateOf("9:00 AM") }

  var refillReminderEnabled by remember { mutableStateOf(true) }
  var refillNotice by remember { mutableStateOf("3 Days Before") }

  var doctorVisitReminderEnabled by remember { mutableStateOf(false) }
  var doctorVisitDate by remember { mutableStateOf("Set Date") }

  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFAF7F2))
  ) {
    // Top background artwork with flowing waves
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(230.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.bc_hero_art_1787934427835),
        contentDescription = "Birth Control Header Art",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
      )

      // Soft gradient blend
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              colors = listOf(
                Color(0x33000000),
                Color(0x10000000),
                Color(0x00FAF7F2),
                Color(0xBBFAF7F2),
                Color(0xFFFAF7F2)
              )
            )
          )
      )

      // Circular Back Button
      Surface(
        onClick = onBack,
        modifier = Modifier
          .statusBarsPadding()
          .padding(start = 16.dp, top = 8.dp)
          .size(36.dp)
          .shadow(
            elevation = 4.dp,
            shape = CircleShape,
            ambientColor = Color(0x33000000),
            spotColor = Color(0x33000000)
          )
          .testTag("bc_back_btn"),
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

      // Title & Subtitle centered over waves
      Column(
        modifier = Modifier
          .align(Alignment.TopCenter)
          .statusBarsPadding()
          .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "Birth Control",
          style = MaterialTheme.typography.displaySmall.copy(
            fontSize = 36.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            color = Color.White
          ),
          textAlign = TextAlign.Center,
          modifier = Modifier.testTag("bc_screen_title")
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
          text = "Cycle & Wellness",
          fontSize = 17.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFFF7F2F0),
          letterSpacing = 0.4.sp,
          textAlign = TextAlign.Center
        )
      }
    }

    // Scrollable Column with White Rounded Cards
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.height(130.dp))

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 500.dp)
          .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {

        // Card 1: Daily Pill Status
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .shadow(
              elevation = 4.dp,
              shape = RoundedCornerShape(26.dp),
              ambientColor = Color(0x1F30232A),
              spotColor = Color(0x1F30232A)
            )
            .testTag("daily_pill_status_card"),
          shape = RoundedCornerShape(26.dp),
          color = Color.White
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 24.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(
              text = "Daily Pill Status",
              fontSize = 23.sp,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Normal,
              color = Color(0xFF1E171B)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Glowing Multi-Layer Orb Button
            PillStatusOrb(
              isTaken = isPillTakenToday,
              onClick = { isPillTakenToday = !isPillTakenToday },
              modifier = Modifier.size(165.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Info lines below orb
            Text(
              text = "Next pill: Today at 9:00 AM",
              fontSize = 14.sp,
              fontWeight = FontWeight.Normal,
              color = Color(0xFF2C2228)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center
            ) {
              Text(
                text = if (isPillTakenToday) "Status: Taken at 9:02 AM " else "Status: Pending for today ",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF2C2228)
              )
              if (isPillTakenToday) {
                Box(
                  modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF43936C)),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Taken",
                    tint = Color.White,
                    modifier = Modifier.size(10.dp)
                  )
                }
              }
            }
          }
        }

        // Card 2: Pack Progress
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .shadow(
              elevation = 4.dp,
              shape = RoundedCornerShape(26.dp),
              ambientColor = Color(0x1F30232A),
              spotColor = Color(0x1F30232A)
            )
            .testTag("pack_progress_card"),
          shape = RoundedCornerShape(26.dp),
          color = Color.White
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 22.dp, vertical = 20.dp)
          ) {
            Text(
              text = "Pack Progress",
              fontSize = 21.sp,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Normal,
              color = Color(0xFF1E171B)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Current Pack: Day 12 & Remaining: 16 Days
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Current Pack: Day $currentPackDay",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF1E171B)
              )

              Text(
                text = "Remaining: $remainingDays Days",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF1E171B)
              )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Progress Bar with Capsule Icon and 12/28 days label
            PillProgressBar(
              currentDay = currentPackDay,
              totalDays = totalPackDays,
              modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Refill warning note centered
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.Center,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "⚠️",
                fontSize = 12.sp
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Refill Soon: 4 days left to order",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF8B574B)
              )
            }
          }
        }

        // Card 3: Set Reminders
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .shadow(
              elevation = 4.dp,
              shape = RoundedCornerShape(26.dp),
              ambientColor = Color(0x1F30232A),
              spotColor = Color(0x1F30232A)
            )
            .testTag("set_reminders_card"),
          shape = RoundedCornerShape(26.dp),
          color = Color.White
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 22.dp, vertical = 20.dp)
          ) {
            Text(
              text = "Set Reminders",
              fontSize = 21.sp,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Normal,
              color = Color(0xFF1E171B)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Row 1: Daily Pill Reminder
            ReminderRow(
              label = "Daily Pill Reminder",
              valueText = dailyPillTime,
              checked = dailyPillReminderEnabled,
              onCheckedChange = { dailyPillReminderEnabled = it },
              onClickValue = {
                dailyPillTime = if (dailyPillTime == "9:00 AM") "9:30 AM" else "9:00 AM"
              },
              testTag = "daily_pill_reminder_switch"
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Row 2: Refill Reminder
            ReminderRow(
              label = "Refill Reminder",
              valueText = refillNotice,
              checked = refillReminderEnabled,
              onCheckedChange = { refillReminderEnabled = it },
              onClickValue = {
                refillNotice = if (refillNotice == "3 Days Before") "5 Days Before" else "3 Days Before"
              },
              testTag = "refill_reminder_switch"
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Row 3: Doctor Visit Reminder
            ReminderRow(
              label = "Doctor Visit Reminder",
              valueText = doctorVisitDate,
              checked = doctorVisitReminderEnabled,
              onCheckedChange = { doctorVisitReminderEnabled = it },
              onClickValue = {
                doctorVisitDate = if (doctorVisitDate == "Set Date") "Oct 26" else "Set Date"
              },
              testTag = "doctor_visit_reminder_switch"
            )
          }
        }

        Spacer(modifier = Modifier.height(110.dp))
      }
    }

    // Bottom Navigation Bar
    BottomNavBar(
      activeTab = activeTab,
      onTabSelected = onTabSelected,
      onAddClick = onAddClick,
      modifier = Modifier.align(Alignment.BottomCenter)
    )
  }
}

/**
 * Glowing Multi-Layer Orb for Pill Logging
 */
@Composable
private fun PillStatusOrb(
  isTaken: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val scale by animateFloatAsState(
    targetValue = if (isTaken) 1.0f else 0.98f,
    label = "orb_scale"
  )

  Box(
    modifier = modifier
      .scale(scale)
      .clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick
      )
      .testTag("pill_status_orb"),
    contentAlignment = Alignment.Center
  ) {
    // Background Glow Canvas
    Canvas(modifier = Modifier.fillMaxSize()) {
      val center = Offset(size.width / 2f, size.height / 2f)
      val radius = (size.width / 2f) - 6.dp.toPx()

      // Soft diffuse ambient outer glow ring (Sage to blush rose)
      drawCircle(
        brush = Brush.sweepGradient(
          colors = listOf(
            Color(0xFF88A596),
            Color(0xFFB3C8BD),
            Color(0xFFE8B8B8),
            Color(0xFFDCA4A4),
            Color(0xFF88A596)
          ),
          center = center
        ),
        radius = radius + 3.dp.toPx(),
        center = center,
        style = Stroke(width = 6.dp.toPx())
      )

      // Inner Frosted glass gradient (Sage-to-Pink blend)
      drawCircle(
        brush = Brush.linearGradient(
          colors = if (isTaken) {
            listOf(
              Color(0xFF8BA798),
              Color(0xFFA2B9AD),
              Color(0xFFC9A2A2),
              Color(0xFFD49999)
            )
          } else {
            listOf(
              Color(0xFFA5BDB1),
              Color(0xFFC7A2A2),
              Color(0xFFDCA0A0)
            )
          },
          start = Offset(0f, 0f),
          end = Offset(size.width, size.height)
        ),
        radius = radius,
        center = center,
        style = Fill
      )

      // Top-left soft highlight shine
      drawCircle(
        brush = Brush.radialGradient(
          colors = listOf(
            Color(0x66FFFFFF),
            Color(0x00FFFFFF)
          ),
          center = Offset(center.x * 0.8f, center.y * 0.6f),
          radius = radius * 0.7f
        ),
        radius = radius,
        center = center
      )
    }

    // Orb Content (Checkmark + "Take Your Pill")
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
      modifier = Modifier.padding(horizontal = 14.dp)
    ) {
      Icon(
        imageVector = Icons.Default.Check,
        contentDescription = "Checkmark",
        tint = Color.White.copy(alpha = 0.95f),
        modifier = Modifier.size(34.dp)
      )

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = if (isTaken) "Take Your\nPill" else "Tap to\nLog Pill",
        fontSize = 17.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        textAlign = TextAlign.Center,
        lineHeight = 22.sp
      )
    }
  }
}

/**
 * Capsule Pill Progress Bar (12/28 days)
 */
@Composable
private fun PillProgressBar(
  currentDay: Int,
  totalDays: Int,
  modifier: Modifier = Modifier
) {
  val progress = (currentDay.toFloat() / totalDays.toFloat()).coerceIn(0f, 1f)

  Box(
    modifier = modifier
      .clip(CircleShape)
      .background(Color(0xFFE2ECE6)),
    contentAlignment = Alignment.CenterStart
  ) {
    // Filled Track (Sage Green)
    Box(
      modifier = Modifier
        .fillMaxWidth(progress)
        .fillMaxSize()
        .clip(CircleShape)
        .background(Color(0xFF7E9D8D)),
      contentAlignment = Alignment.CenterEnd
    ) {
      // Capsule icon on the edge of the progress
      Surface(
        shape = CircleShape,
        color = Color.White.copy(alpha = 0.4f),
        modifier = Modifier
          .padding(end = 4.dp)
          .size(22.dp)
      ) {
        Box(contentAlignment = Alignment.Center) {
          Text(
            text = "💊",
            fontSize = 11.sp,
            textAlign = TextAlign.Center
          )
        }
      }
    }

    // Right-aligned text: 12/28 days
    Text(
      text = "$currentDay/$totalDays days",
      fontSize = 13.sp,
      fontWeight = FontWeight.Normal,
      color = Color(0xFF2C2228),
      modifier = Modifier
        .align(Alignment.CenterEnd)
        .padding(end = 12.dp)
    )
  }
}

/**
 * Reminder Row with Switch on the LEFT, label in middle, and value on right
 */
@Composable
private fun ReminderRow(
  label: String,
  valueText: String,
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit,
  onClickValue: () -> Unit,
  testTag: String,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.weight(1f)
    ) {
      // Switch on the left
      Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
          checkedThumbColor = Color.White,
          checkedTrackColor = Color(0xFF755566), // Mauve plum track
          uncheckedThumbColor = Color.White,
          uncheckedTrackColor = Color(0xFFE0DAD5),
          uncheckedBorderColor = Color.Transparent,
          checkedBorderColor = Color.Transparent
        ),
        modifier = Modifier.testTag(testTag)
      )

      Spacer(modifier = Modifier.width(12.dp))

      Text(
        text = label,
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF1E171B)
      )
    }

    Text(
      text = valueText,
      fontSize = 14.sp,
      fontWeight = FontWeight.Normal,
      color = Color(0xFF5A4952),
      modifier = Modifier
        .clickable { onClickValue() }
        .padding(start = 8.dp)
    )
  }
}

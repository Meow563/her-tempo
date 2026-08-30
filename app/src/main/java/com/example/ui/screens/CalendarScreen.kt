package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
import com.example.ui.components.CycleDetailPill
import com.example.ui.components.FrostedGlassCard
import com.example.ui.components.OutlinedCalendarIcon
import com.example.ui.components.OutlinedDropIcon
import com.example.ui.components.OutlinedSproutIcon
import com.example.ui.components.PeriodDropIcon
import com.example.ui.components.PillStyle
import com.example.ui.components.SingleLeafIcon
import com.example.ui.components.SproutIcon
import com.example.ui.theme.CardGlassCalendarBg
import com.example.ui.theme.CreamBackground
import com.example.ui.theme.MauveDrop
import com.example.ui.theme.MauvePlum
import com.example.ui.theme.SageGreen
import com.example.ui.theme.SageGreenBadge
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarScreen(
  selectedDate: LocalDate,
  currentMonth: YearMonth,
  dayLogs: Map<String, DayLogEntity>,
  todayDetail: DayLogEntity,
  onSelectDate: (LocalDate) -> Unit,
  onPrevMonth: () -> Unit,
  onNextMonth: () -> Unit,
  onOpenLog: (LocalDate) -> Unit,
  onOpenAppointmentDetail: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(CreamBackground)
  ) {
    // Top Flowing Background Image Asset
    Image(
      painter = painterResource(id = R.drawable.img_wavy_header_bg),
      contentDescription = "Silk wave background",
      modifier = Modifier
        .fillMaxWidth()
        .height(480.dp)
        .align(Alignment.TopCenter),
      contentScale = ContentScale.Crop
    )

    // Main Scrollable Content
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .statusBarsPadding()
        .padding(horizontal = 20.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.height(12.dp))

      // Top App Title "Calendar"
      Text(
        text = "Calendar",
        style = MaterialTheme.typography.displayLarge.copy(
          fontSize = 34.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = TextDark
        ),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("calendar_title")
          .padding(bottom = 16.dp, start = 4.dp)
      )

      // Calendar Frosted Glass Card (Transparent glass aesthetic)
      FrostedGlassCard(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 500.dp)
          .testTag("calendar_card"),
        backgroundColor = CardGlassCalendarBg,
        cornerRadius = 28.dp,
        elevation = 6.dp
      ) {
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          // Month Header with Prev/Next Navigation
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            IconButton(
              onClick = onPrevMonth,
              modifier = Modifier
                .size(36.dp)
                .testTag("prev_month_button")
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Previous Month",
                tint = MauvePlum
              )
            }

            Text(
              text = currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
              style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 26.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = TextDark
              ),
              modifier = Modifier.testTag("month_name_text")
            )

            IconButton(
              onClick = onNextMonth,
              modifier = Modifier
                .size(36.dp)
                .testTag("next_month_button")
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Next Month",
                tint = MauvePlum
              )
            }
          }

          // Days of Week Row (Sun Mon Tue Wed Thu Fri Sat)
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround
          ) {
            listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach { dayLabel ->
              Text(
                text = dayLabel,
                style = MaterialTheme.typography.titleMedium.copy(
                  fontSize = 15.sp,
                  fontFamily = FontFamily.Serif,
                  fontWeight = FontWeight.Bold,
                  color = TextDark
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
              )
            }
          }

          // Calendar Grid (7 columns)
          CalendarGrid(
            currentMonth = currentMonth,
            selectedDate = selectedDate,
            dayLogs = dayLogs,
            onSelectDate = onSelectDate
          )
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // "Today's Details" / "Selected Day Details" Frosted Glass Card
      FrostedGlassCard(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 500.dp)
          .testTag("today_details_card"),
        cornerRadius = 28.dp,
        elevation = 8.dp
      ) {
        Column(
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            val detailsTitle = if (selectedDate == LocalDate.of(2026, 6, 15) || selectedDate == LocalDate.now()) {
              "Today's Details"
            } else {
              "${selectedDate.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())} ${selectedDate.dayOfMonth} Details"
            }

            Text(
              text = detailsTitle,
              style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 21.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = TextDark
              ),
              modifier = Modifier.testTag("details_header_text")
            )

            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable { onOpenLog(selectedDate) }
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .testTag("edit_day_button")
            ) {
              Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Log entry for this day",
                tint = MauvePlum,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = "Log",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MauvePlum
              )
            }
          }

          // Pill 1: Cycle Day (Ovulation Phase)
          val cycleText = if (todayDetail.phaseName.isNotEmpty()) {
            "Cycle Day: ${todayDetail.cycleDay} (${todayDetail.phaseName})"
          } else {
            "Cycle Day: ${todayDetail.cycleDay}"
          }

          CycleDetailPill(
            title = cycleText,
            style = PillStyle.SAGE,
            testTag = "cycle_day_pill",
            iconContent = {
              OutlinedDropIcon(
                size = 17.dp,
                tint = TextDark
              )
            }
          )

          Spacer(modifier = Modifier.height(10.dp))

          // Pill 2: Symptoms: Mild cramping
          val symptomsText = if (todayDetail.symptoms.isNotEmpty()) {
            "Symptoms: ${todayDetail.symptoms}"
          } else {
            "Symptoms: None logged"
          }

          CycleDetailPill(
            title = symptomsText,
            style = PillStyle.ROSE,
            testTag = "symptoms_pill",
            iconContent = {
              OutlinedSproutIcon(
                size = 17.dp,
                tint = TextDark
              )
            }
          )

          Spacer(modifier = Modifier.height(10.dp))

          // Pill 3: Events / Doctor Appointment
          val eventsText = if (todayDetail.events.isNotEmpty()) {
            "Events: ${todayDetail.events}"
          } else {
            "Appointment: Dr. Anya Sharma (10:00 AM)"
          }

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { onOpenAppointmentDetail() }
          ) {
            CycleDetailPill(
              title = eventsText,
              style = PillStyle.SAGE,
              testTag = "events_pill",
              iconContent = {
                OutlinedCalendarIcon(
                  size = 17.dp,
                  tint = TextDark
                )
              }
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Doctor Appointment Shortcut Card
          FrostedGlassCard(
            modifier = Modifier
              .fillMaxWidth()
              .testTag("calendar_appt_card")
              .clickable { onOpenAppointmentDetail() }
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                  painter = painterResource(id = R.drawable.img_doctor_anya_1787819162193),
                  contentDescription = "Doctor Thumbnail",
                  modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape),
                  contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                  Text(
                    text = "Dr. Anya Sharma (OB/GYN)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDark
                  )
                  Text(
                    text = "Thu, Oct 26 • 10:00 AM • Willow Creek",
                    fontSize = 12.sp,
                    color = TextMuted
                  )
                }
              }

              Text(
                text = "Details →",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MauvePlum
              )
            }
          }
        }
      }

      // Bottom padding so items are not blocked by the bottom navigation bar
      Spacer(modifier = Modifier.height(100.dp))
    }
  }
}

@Composable
fun CalendarGrid(
  currentMonth: YearMonth,
  selectedDate: LocalDate,
  dayLogs: Map<String, DayLogEntity>,
  onSelectDate: (LocalDate) -> Unit
) {
  val firstDayOfMonth = currentMonth.atDay(1)
  val daysInMonth = currentMonth.lengthOfMonth()
  // DayOfWeek.SUNDAY = 7 -> offset 0 for Sunday
  val firstDayOfWeek = firstDayOfMonth.dayOfWeek
  val emptyStartCells = when (firstDayOfWeek) {
    DayOfWeek.SUNDAY -> 0
    DayOfWeek.MONDAY -> 1
    DayOfWeek.TUESDAY -> 2
    DayOfWeek.WEDNESDAY -> 3
    DayOfWeek.THURSDAY -> 4
    DayOfWeek.FRIDAY -> 5
    DayOfWeek.SATURDAY -> 6
  }

  val totalCells = emptyStartCells + daysInMonth
  val totalRows = (totalCells + 6) / 7

  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    for (row in 0 until totalRows) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
      ) {
        for (col in 0 until 7) {
          val cellIndex = row * 7 + col
          val dayNumber = cellIndex - emptyStartCells + 1

          if (dayNumber in 1..daysInMonth) {
            val date = currentMonth.atDay(dayNumber)
            val dateKey = date.format(DateTimeFormatter.ISO_LOCAL_DATE)
            val dayLog = dayLogs[dateKey]
            val isSelected = date == selectedDate

            CalendarDayCell(
              dayNumber = dayNumber,
              isSelected = isSelected,
              dayLog = dayLog,
              onClick = { onSelectDate(date) },
              modifier = Modifier.weight(1f)
            )
          } else {
            // Empty cell placeholder
            Box(modifier = Modifier.weight(1f))
          }
        }
      }
    }
  }
}

@Composable
fun CalendarDayCell(
  dayNumber: Int,
  isSelected: Boolean,
  dayLog: DayLogEntity?,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val interactionSource = remember { MutableInteractionSource() }

  Column(
    modifier = modifier
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
      )
      .padding(vertical = 2.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Number with optional Selected Sage Green Badge
    Box(
      modifier = Modifier
        .size(34.dp)
        .then(
          if (isSelected) {
            Modifier
              .shadow(
                elevation = 4.dp,
                shape = CircleShape,
                ambientColor = Color(0x334A6553),
                spotColor = Color(0x334A6553)
              )
              .background(SageGreenBadge, shape = CircleShape)
          } else {
            Modifier
          }
        ),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = dayNumber.toString(),
        fontSize = 16.sp,
        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
        fontFamily = FontFamily.Default,
        color = if (isSelected) Color(0xFF1E2822) else TextDark,
        textAlign = TextAlign.Center
      )
    }

    Spacer(modifier = Modifier.height(3.dp))

    // Marker Icon below day number
    Box(
      modifier = Modifier
        .height(14.dp)
        .fillMaxWidth(),
      contentAlignment = Alignment.Center
    ) {
      when {
        dayLog?.isPeriod == true -> {
          PeriodDropIcon(
            size = 12.dp,
            tint = MauveDrop
          )
        }
        dayLog?.isFertile == true || dayLog?.isOvulation == true -> {
          if (dayNumber == 28) {
            // Day 28 has a single leaf icon matching the design
            SingleLeafIcon(
              size = 12.dp,
              tint = SageGreen
            )
          } else {
            SproutIcon(
              size = 12.dp,
              tint = SageGreen
            )
          }
        }
        else -> {
          // Empty space to maintain consistent row height
          Spacer(modifier = Modifier.size(12.dp))
        }
      }
    }
  }
}

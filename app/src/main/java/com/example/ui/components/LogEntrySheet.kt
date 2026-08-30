package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DayLogEntity
import com.example.ui.theme.BlushRose
import com.example.ui.theme.BlushRoseLight
import com.example.ui.theme.CreamBackground
import com.example.ui.theme.MauvePlum
import com.example.ui.theme.MauvePlumDark
import com.example.ui.theme.SageGreen
import com.example.ui.theme.SageGreenLight
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LogEntrySheet(
  date: LocalDate,
  existingLog: DayLogEntity?,
  onDismiss: () -> Unit,
  onSave: (
    date: LocalDate,
    isPeriod: Boolean,
    flow: String,
    symptoms: String,
    events: String,
    mood: String,
    notes: String,
    waterGlasses: Int
  ) -> Unit
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

  var isPeriod by remember { mutableStateOf(existingLog?.isPeriod ?: false) }
  var flowIntensity by remember { mutableStateOf(existingLog?.flowIntensity ?: "None") }
  val initialSymptoms = remember {
    existingLog?.symptoms?.split(",")?.map { it.trim() }?.filter { it.isNotEmpty() }?.toSet() ?: emptySet()
  }
  var selectedSymptoms by remember { mutableStateOf(initialSymptoms) }
  var eventsText by remember { mutableStateOf(existingLog?.events ?: "") }
  var moodText by remember { mutableStateOf(existingLog?.mood ?: "Calm") }
  var notesText by remember { mutableStateOf(existingLog?.notes ?: "") }
  var waterGlasses by remember { mutableIntStateOf(existingLog?.waterGlasses ?: 6) }

  val symptomOptions = listOf(
    "Mild cramping", "Severe cramps", "Headache", "Tender breasts",
    "Fatigue", "Bloating", "Acne", "Backache", "Insomnia", "Craving sweets"
  )

  val moodOptions = listOf("Calm", "Happy", "Energetic", "Sensitive", "Low energy", "Anxious")
  val flowOptions = listOf("Spotting", "Light", "Medium", "Heavy")

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = Color.White,
    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 24.dp, vertical = 12.dp)
        .testTag("log_entry_sheet")
    ) {
      // Header
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Log Day Details",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = TextDark
          )
          Text(
            text = "${date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())}, ${date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${date.dayOfMonth}, ${date.year}",
            fontSize = 14.sp,
            color = TextMuted
          )
        }

        IconButton(
          onClick = onDismiss,
          modifier = Modifier.testTag("close_sheet_button")
        ) {
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = TextMuted
          )
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Period Toggle
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(if (isPeriod) BlushRoseLight.copy(alpha = 0.5f) else CreamBackground)
          .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Period / Bleeding",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = TextDark
          )
          Text(
            text = if (isPeriod) "Period logged for this day" else "No period logged",
            fontSize = 12.sp,
            color = TextMuted
          )
        }

        Switch(
          checked = isPeriod,
          onCheckedChange = {
            isPeriod = it
            if (!it) flowIntensity = "None"
            else if (flowIntensity == "None") flowIntensity = "Medium"
          },
          colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = MauvePlum,
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = Color(0xFFD4C8C2)
          ),
          modifier = Modifier.testTag("period_switch")
        )
      }

      // Flow Intensity Picker (if period active)
      if (isPeriod) {
        Spacer(modifier = Modifier.height(14.dp))
        Text(
          text = "Flow Intensity",
          fontWeight = FontWeight.SemiBold,
          fontSize = 14.sp,
          color = TextDark,
          modifier = Modifier.padding(bottom = 6.dp)
        )
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          flowOptions.forEach { flow ->
            val isSelected = flowIntensity == flow
            Box(
              modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(if (isSelected) MauvePlum else CreamBackground)
                .clickable { flowIntensity = flow }
                .padding(vertical = 8.dp),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = flow,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                color = if (isSelected) Color.White else TextDark
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Symptoms Section
      Text(
        text = "Symptoms",
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = TextDark,
        modifier = Modifier.padding(bottom = 8.dp)
      )

      FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        symptomOptions.forEach { symptom ->
          val isSelected = symptom in selectedSymptoms
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(if (isSelected) BlushRoseLight else CreamBackground)
              .border(
                1.dp,
                if (isSelected) BlushRose else Color(0x33000000),
                RoundedCornerShape(20.dp)
              )
              .clickable {
                selectedSymptoms = if (isSelected) {
                  selectedSymptoms - symptom
                } else {
                  selectedSymptoms + symptom
                }
              }
              .padding(horizontal = 14.dp, vertical = 7.dp)
          ) {
            Text(
              text = symptom,
              fontSize = 13.sp,
              fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
              color = if (isSelected) MauvePlumDark else TextDark
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Mood Section
      Text(
        text = "Mood",
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = TextDark,
        modifier = Modifier.padding(bottom = 8.dp)
      )

      FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        moodOptions.forEach { mood ->
          val isSelected = moodText == mood
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(if (isSelected) SageGreenLight else CreamBackground)
              .border(
                1.dp,
                if (isSelected) SageGreen else Color(0x33000000),
                RoundedCornerShape(20.dp)
              )
              .clickable { moodText = mood }
              .padding(horizontal = 14.dp, vertical = 7.dp)
          ) {
            Text(
              text = mood,
              fontSize = 13.sp,
              fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
              color = if (isSelected) Color(0xFF233B2B) else TextDark
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Events / Activities
      Text(
        text = "Events & Activities",
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = TextDark,
        modifier = Modifier.padding(bottom = 6.dp)
      )

      OutlinedTextField(
        value = eventsText,
        onValueChange = { eventsText = it },
        placeholder = { Text("e.g. Yoga at 6 PM, Doctor visit", color = TextMuted) },
        modifier = Modifier
          .fillMaxWidth()
          .testTag("events_input"),
        shape = RoundedCornerShape(14.dp),
        singleLine = true
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Notes
      Text(
        text = "Personal Notes",
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = TextDark,
        modifier = Modifier.padding(bottom = 6.dp)
      )

      OutlinedTextField(
        value = notesText,
        onValueChange = { notesText = it },
        placeholder = { Text("Add daily reflections or health notes...", color = TextMuted) },
        modifier = Modifier
          .fillMaxWidth()
          .testTag("notes_input"),
        shape = RoundedCornerShape(14.dp),
        minLines = 2
      )

      Spacer(modifier = Modifier.height(24.dp))

      // Save Button
      Button(
        onClick = {
          onSave(
            date,
            isPeriod,
            flowIntensity,
            selectedSymptoms.joinToString(", "),
            eventsText.trim(),
            moodText,
            notesText.trim(),
            waterGlasses
          )
        },
        modifier = Modifier
          .fillMaxWidth()
          .height(52.dp)
          .testTag("save_log_button"),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MauvePlum)
      ) {
        Text(
          text = "Save Entry",
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
      }

      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

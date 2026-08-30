package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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

data class DoctorAppointment(
  val doctorName: String = "Dr. Anya Sharma",
  val specialty: String = "OB/GYN & Holistic Wellness Specialist",
  val clinicLocation: String = "Cycle & Hormone Clinic, 4th Floor",
  val dateFormatted: String = "Oct 26, 2023",
  val dayOfWeek: String = "Thursday",
  val timeFormatted: String = "10:00 AM",
  val facilityAddress: String = "Willow Creek Medical Center, Suite 405"
)

@Composable
fun AppointmentDetailScreen(
  onBack: () -> Unit,
  onRescheduleCancel: () -> Unit = {},
  appointment: DoctorAppointment = DoctorAppointment(),
  modifier: Modifier = Modifier
) {
  var noteInput by remember { mutableStateOf("") }
  val notesList = remember {
    mutableStateListOf(
      "Discuss recent luteal phase symptom changes and energy levels.",
      "Review supplement recommendations for cycle balance."
    )
  }

  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFBF8F3))
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Top Decorative Rose Gold Botanical Ribbon Header
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(170.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_appt_header_bg_1787819183710),
          contentDescription = "Appointment Header Banner",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )

        // Soft gradient overlay
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color(0x10FFFFFF),
                  Color(0x00FFFFFF),
                  Color(0x80FBF8F3)
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
            .size(38.dp)
            .shadow(
              elevation = 4.dp,
              shape = CircleShape,
              ambientColor = Color(0x33000000),
              spotColor = Color(0x33000000)
            )
            .testTag("appt_back_btn"),
          shape = CircleShape,
          color = Color(0xF2FFFFFF)
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

      // Main Section with "Appointment Details" Title
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 520.dp)
          .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.Start
      ) {
        // Title: Appointment Details
        Text(
          text = "Appointment Details",
          style = MaterialTheme.typography.displaySmall.copy(
            fontSize = 32.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF1E171B)
          ),
          modifier = Modifier
            .padding(vertical = 12.dp)
            .testTag("appt_details_title")
        )

        // Card 1: Doctor Profile & Schedule Details Card
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .shadow(
              elevation = 4.dp,
              shape = RoundedCornerShape(24.dp),
              ambientColor = Color(0x22402D34),
              spotColor = Color(0x22402D34)
            )
            .testTag("doctor_detail_card"),
          shape = RoundedCornerShape(24.dp),
          color = Color.White
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 20.dp, vertical = 22.dp)
          ) {
            // Doctor Avatar and Top Info
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Image(
                painter = painterResource(id = R.drawable.img_doctor_anya_1787819162193),
                contentDescription = "Dr. Anya Sharma Portrait",
                modifier = Modifier
                  .size(68.dp)
                  .clip(CircleShape)
                  .border(BorderStroke(1.5.dp, Color(0xFFE8DACF)), CircleShape),
                contentScale = ContentScale.Crop
              )

              Spacer(modifier = Modifier.width(16.dp))

              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = appointment.doctorName,
                  fontSize = 20.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF1E171B),
                  lineHeight = 24.sp
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                  text = appointment.specialty,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Normal,
                  color = Color(0xFF4A3B43),
                  lineHeight = 18.sp
                )
              }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Centered Clinic Information
            Column(
              modifier = Modifier.fillMaxWidth(),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = "OB/GYN & Holistic Wellness Specialist",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1E171B),
                textAlign = TextAlign.Center
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = appointment.clinicLocation,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF382932),
                textAlign = TextAlign.Center
              )
            }

            Spacer(modifier = Modifier.height(18.dp))
            HorizontalDivider(color = Color(0xFFEFE7E2), thickness = 1.dp)
            Spacer(modifier = Modifier.height(18.dp))

            // Date & Time Row
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = appointment.dateFormatted,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E171B)
              )

              Row(
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Outlined.AccessTime,
                  contentDescription = "Time",
                  tint = Color(0xFF4A3B43),
                  modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                  Text(
                    text = "${appointment.dayOfWeek},",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF30232A),
                    lineHeight = 15.sp
                  )
                  Text(
                    text = appointment.timeFormatted,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF30232A),
                    lineHeight = 15.sp
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Location Row
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "Location Pin",
                tint = Color(0xFF6B485C),
                modifier = Modifier.size(22.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = appointment.facilityAddress,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF30232A)
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Card 2: Notes for Doctor Card
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .shadow(
              elevation = 4.dp,
              shape = RoundedCornerShape(20.dp),
              ambientColor = Color(0x22402D34),
              spotColor = Color(0x22402D34)
            )
            .testTag("notes_doctor_card"),
          shape = RoundedCornerShape(20.dp),
          color = Color.White
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 18.dp, vertical = 18.dp)
          ) {
            Text(
              text = "Notes for Doctor",
              fontSize = 18.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color(0xFF1E171B)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Input Row with Add Button
            Surface(
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .border(BorderStroke(1.dp, Color(0xFFDCCFC7)), RoundedCornerShape(12.dp)),
              shape = RoundedCornerShape(12.dp),
              color = Color.White
            ) {
              Row(
                modifier = Modifier
                  .fillMaxSize()
                  .padding(start = 12.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Box(
                  modifier = Modifier.weight(1f),
                  contentAlignment = Alignment.CenterStart
                ) {
                  if (noteInput.isEmpty()) {
                    Text(
                      text = "Add notes or questions for Dr. Sharma...",
                      fontSize = 13.sp,
                      color = Color(0xFF9E8E95)
                    )
                  }
                  BasicTextField(
                    value = noteInput,
                    onValueChange = { noteInput = it },
                    textStyle = TextStyle(
                      fontSize = 14.sp,
                      color = Color(0xFF1E171B)
                    ),
                    cursorBrush = SolidColor(MauvePlum),
                    singleLine = true,
                    modifier = Modifier
                      .fillMaxWidth()
                      .testTag("notes_input_field")
                  )
                }

                Surface(
                  onClick = {
                    if (noteInput.isNotBlank()) {
                      notesList.add(noteInput.trim())
                      noteInput = ""
                    }
                  },
                  modifier = Modifier
                    .padding(vertical = 4.dp)
                    .testTag("add_note_btn"),
                  shape = RoundedCornerShape(8.dp),
                  color = Color(0xFF6B485C)
                ) {
                  Text(
                    text = "Add",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                  )
                }
              }
            }

            // Render existing notes
            if (notesList.isNotEmpty()) {
              Spacer(modifier = Modifier.height(12.dp))
              notesList.forEachIndexed { idx, note ->
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Box(
                    modifier = Modifier
                      .size(6.dp)
                      .clip(CircleShape)
                      .background(Color(0xFF9E7E8B))
                  )
                  Spacer(modifier = Modifier.width(8.dp))
                  Text(
                    text = note,
                    fontSize = 13.sp,
                    color = Color(0xFF4A3B43),
                    modifier = Modifier.weight(1f)
                  )
                  IconButton(
                    onClick = { notesList.removeAt(idx) },
                    modifier = Modifier.size(20.dp)
                  ) {
                    Icon(
                      imageVector = Icons.Default.Close,
                      contentDescription = "Remove note",
                      tint = Color(0xFFB5A4AC),
                      modifier = Modifier.size(14.dp)
                    )
                  }
                }
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(26.dp))

        // CTA Button: "Reschedule / Cancel"
        Surface(
          onClick = onRescheduleCancel,
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .shadow(
              elevation = 8.dp,
              shape = CircleShape,
              ambientColor = Color(0x406B485C),
              spotColor = Color(0x406B485C)
            )
            .testTag("reschedule_cancel_button"),
          shape = CircleShape,
          color = Color(0xFF644356)
        ) {
          Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "Reschedule / Cancel",
              fontSize = 16.sp,
              fontWeight = FontWeight.Medium,
              color = Color.White
            )
          }
        }

        Spacer(modifier = Modifier.height(110.dp))
      }
    }
  }
}

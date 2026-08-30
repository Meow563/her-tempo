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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.example.ui.components.BottomNavBar
import com.example.ui.viewmodel.NavTab

@Composable
fun PartnerSyncScreen(
  onBack: () -> Unit,
  activeTab: NavTab = NavTab.HOME,
  onTabSelected: (NavTab) -> Unit = {},
  onAddClick: () -> Unit = {},
  onInviteClicked: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  var phaseNameShared by remember { mutableStateOf(true) }
  var symptomsShared by remember { mutableStateOf(true) }
  var moodShared by remember { mutableStateOf(true) }
  var insightsShared by remember { mutableStateOf(true) }

  var inviteSent by remember { mutableStateOf(false) }

  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFAF7F2))
  ) {
    // Top background artwork with fluid ribbon curves (no eye)
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(520.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.partner_sync_no_eye_art_1787935945468),
        contentDescription = "Sync with Partner Artwork",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
      )

      // Soft fading blend at the bottom into warm cream canvas
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              colors = listOf(
                Color.Transparent,
                Color.Transparent,
                Color(0x15FAF7F2),
                Color(0x70FAF7F2),
                Color(0xFFFAF7F2)
              ),
              startY = 150f,
              endY = 1400f
            )
          )
      )

      // Back navigation button
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
          .testTag("partner_sync_back_button"),
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

    // Scrollable Column with Frosted Glass / Translucent Floating Card
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.height(200.dp))

      // Main Transparent / Frosted Glass Card
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 20.dp)
          .shadow(
            elevation = 10.dp,
            shape = RoundedCornerShape(32.dp),
            ambientColor = Color(0x2230232A),
            spotColor = Color(0x2230232A)
          )
          .testTag("partner_sync_card"),
        shape = RoundedCornerShape(32.dp),
        color = Color.Transparent,
        border = androidx.compose.foundation.BorderStroke(1.5.dp, Color.White.copy(alpha = 0.8f))
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.White.copy(alpha = 0.35f),
                  Color.White.copy(alpha = 0.52f),
                  Color.White.copy(alpha = 0.70f),
                  Color.White.copy(alpha = 0.82f)
                )
              )
            )
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 26.dp, vertical = 28.dp)
          ) {
            // Title: "Sync with Partner" in elegant display serif
            Text(
              text = "Sync with Partner",
              fontSize = 32.sp,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Normal,
              color = Color(0xFF1E171B),
              letterSpacing = (-0.5).sp,
              modifier = Modifier.testTag("partner_sync_title")
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle description
            Text(
              text = "Share your cycle data with your partner to enhance understanding and support.",
              fontSize = 15.sp,
              lineHeight = 22.sp,
              fontWeight = FontWeight.Normal,
              color = Color(0xFF2C2228),
              modifier = Modifier.testTag("partner_sync_subtitle")
            )

            Spacer(modifier = Modifier.height(24.dp))

            // "Invite Partner" Button with plum drop shadow
            Surface(
              onClick = {
                inviteSent = !inviteSent
                onInviteClicked()
              },
              modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .shadow(
                  elevation = 6.dp,
                  shape = RoundedCornerShape(26.dp),
                  ambientColor = Color(0x66583748),
                  spotColor = Color(0x66583748)
                )
                .testTag("invite_partner_button"),
              shape = RoundedCornerShape(26.dp),
              color = Color(0xFF583748)
            ) {
              Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = if (inviteSent) "Invite Sent ✓" else "Invite Partner",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Medium,
                  color = Color.White
                )
              }
            }

            Spacer(modifier = Modifier.height(22.dp))

            HorizontalDivider(
              color = Color(0xFFECE6E1),
              thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // "Shared Data Permissions"
            Text(
              text = "Shared Data Permissions",
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF1E171B),
              modifier = Modifier.testTag("permissions_section_title")
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 4 Permission Rows
            PermissionToggleRow(
              label = "Phase Name",
              checked = phaseNameShared,
              onCheckedChange = { phaseNameShared = it },
              testTag = "permission_phase_name"
            )

            Spacer(modifier = Modifier.height(10.dp))

            PermissionToggleRow(
              label = "Symptoms",
              checked = symptomsShared,
              onCheckedChange = { symptomsShared = it },
              testTag = "permission_symptoms"
            )

            Spacer(modifier = Modifier.height(10.dp))

            PermissionToggleRow(
              label = "Mood",
              checked = moodShared,
              onCheckedChange = { moodShared = it },
              testTag = "permission_mood"
            )

            Spacer(modifier = Modifier.height(10.dp))

            PermissionToggleRow(
              label = "Insights",
              checked = insightsShared,
              onCheckedChange = { insightsShared = it },
              testTag = "permission_insights"
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(110.dp))
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
 * Clean Permission Row with label on the left and lush green switch on the right
 */
@Composable
private fun PermissionToggleRow(
  label: String,
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit,
  testTag: String,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 2.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = label,
      fontSize = 16.sp,
      fontWeight = FontWeight.Normal,
      color = Color(0xFF1E171B)
    )

    Switch(
      checked = checked,
      onCheckedChange = onCheckedChange,
      colors = SwitchDefaults.colors(
        checkedThumbColor = Color.White,
        checkedTrackColor = Color(0xFF5CA36D), // Sage green track matching design
        uncheckedThumbColor = Color.White,
        uncheckedTrackColor = Color(0xFFE2DDD8),
        uncheckedBorderColor = Color.Transparent,
        checkedBorderColor = Color.Transparent
      ),
      modifier = Modifier.testTag(testTag)
    )
  }
}

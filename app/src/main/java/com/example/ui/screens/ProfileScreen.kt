package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Autorenew
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
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
import com.example.data.UserSettingsEntity

data class ProfileMenuItem(
  val id: String,
  val title: String,
  val icon: ImageVector,
  val badgeBgColor: Color,
  val iconColor: Color,
  val testTag: String
)

@Composable
fun ProfileScreen(
  userSettings: UserSettingsEntity,
  onSaveSettings: (cycleLength: Int, periodLength: Int, userName: String, reminders: Boolean) -> Unit,
  onOpenGateway: () -> Unit = {},
  onOpenWelcome: () -> Unit = {},
  onOpenPasscodeLock: () -> Unit = {},
  onOpenAlerts: () -> Unit = {},
  onOpenPartnerSync: () -> Unit = {},
  onOpenHealthProfile: () -> Unit = {},
  onOpenPremium: () -> Unit = {},
  onOpenCommunity: () -> Unit = {},
  onOpenCycleUnderstood: () -> Unit = {},
  onOpenTrackWithEase: () -> Unit = {},
  onOpenPersonalizedInsights: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  var showCycleSettingsDialog by remember { mutableStateOf(false) }
  var showAccountDialog by remember { mutableStateOf(false) }

  var tempCycleLength by remember(userSettings.averageCycleLength) {
    mutableFloatStateOf(userSettings.averageCycleLength.toFloat())
  }
  var tempPeriodLength by remember(userSettings.averagePeriodLength) {
    mutableFloatStateOf(userSettings.averagePeriodLength.toFloat())
  }

  val menuItems = remember {
    listOf(
      ProfileMenuItem(
        id = "account_info",
        title = "Account Info",
        icon = Icons.Outlined.PersonOutline,
        badgeBgColor = Color(0xFFFBE1E5),
        iconColor = Color(0xFF2C2228),
        testTag = "menu_account_info"
      ),
      ProfileMenuItem(
        id = "notifications",
        title = "Notifications",
        icon = Icons.Outlined.Notifications,
        badgeBgColor = Color(0xFFFDF0DA),
        iconColor = Color(0xFF2C2228),
        testTag = "menu_notifications"
      ),
      ProfileMenuItem(
        id = "cycle_settings",
        title = "Cycle Settings",
        icon = Icons.Outlined.Autorenew,
        badgeBgColor = Color(0xFFE2EDE5),
        iconColor = Color(0xFF2C2228),
        testTag = "menu_cycle_settings"
      ),
      ProfileMenuItem(
        id = "privacy",
        title = "Privacy",
        icon = Icons.Outlined.Shield,
        badgeBgColor = Color(0xFFE3ECEB),
        iconColor = Color(0xFF2C2228),
        testTag = "menu_privacy"
      )
    )
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFCFAF7))
  ) {
    // Top fluid ribbon artwork
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.personalized_waves_bg_1787988997650),
        contentDescription = "Profile Artwork Header",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
      )
    }

    // Scrollable container with large bottom card
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 90.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.height(180.dp))

      // Main Card Container with Avatar overlapping top
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 500.dp),
        contentAlignment = Alignment.TopCenter
      ) {
        // Main Cream Sheet Card
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 52.dp, start = 14.dp, end = 14.dp)
            .shadow(
              elevation = 4.dp,
              shape = RoundedCornerShape(topStart = 38.dp, topEnd = 38.dp, bottomStart = 30.dp, bottomEnd = 30.dp),
              ambientColor = Color(0x1A251820),
              spotColor = Color(0x1A251820)
            )
            .testTag("profile_main_card"),
          shape = RoundedCornerShape(topStart = 38.dp, topEnd = 38.dp, bottomStart = 30.dp, bottomEnd = 30.dp),
          color = Color(0xFFFAF7F2)
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 70.dp, start = 18.dp, end = 18.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            menuItems.forEach { item ->
              ProfileMenuCard(
                item = item,
                onClick = {
                  when (item.id) {
                    "account_info" -> showAccountDialog = true
                    "notifications" -> onOpenAlerts()
                    "cycle_settings" -> showCycleSettingsDialog = true
                    "privacy" -> onOpenPasscodeLock()
                  }
                }
              )
            }
          }
        }

        // Circular Frosted Profile Avatar overlapping the top
        Box(
          modifier = Modifier
            .size(108.dp)
            .shadow(
              elevation = 8.dp,
              shape = CircleShape,
              ambientColor = Color(0x2E302028),
              spotColor = Color(0x2E302028)
            )
            .border(width = 4.dp, color = Color.White.copy(alpha = 0.95f), shape = CircleShape)
            .clip(CircleShape)
            .background(Color(0xFFE5EDE7))
            .clickable { showAccountDialog = true }
            .testTag("profile_avatar_circle"),
          contentAlignment = Alignment.Center
        ) {
          // Soft frosted silhouette figure inside circle
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
          ) {
            // Blurred Head
            Box(
              modifier = Modifier
                .size(32.dp)
                .background(Color(0xFFC78F9B).copy(alpha = 0.72f), CircleShape)
                .blur(3.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            // Blurred Shoulders
            Box(
              modifier = Modifier
                .width(52.dp)
                .height(30.dp)
                .background(
                  Color(0xFFC78F9B).copy(alpha = 0.65f),
                  RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .blur(4.dp)
            )
          }
        }
      }
    }
  }

  // Cycle Settings Dialog
  if (showCycleSettingsDialog) {
    AlertDialog(
      onDismissRequest = { showCycleSettingsDialog = false },
      title = {
        Text(
          text = "Cycle Settings",
          fontSize = 22.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF1E151A)
        )
      },
      text = {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text("Average Cycle Length", fontSize = 14.sp, color = Color(0xFF2C2228))
            Text("${tempCycleLength.toInt()} days", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5A394B))
          }
          Slider(
            value = tempCycleLength,
            onValueChange = { tempCycleLength = it },
            valueRange = 21f..45f,
            steps = 23,
            colors = SliderDefaults.colors(
              thumbColor = Color(0xFF5A394B),
              activeTrackColor = Color(0xFF5A394B)
            )
          )

          Spacer(modifier = Modifier.height(16.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text("Average Period Duration", fontSize = 14.sp, color = Color(0xFF2C2228))
            Text("${tempPeriodLength.toInt()} days", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5A394B))
          }
          Slider(
            value = tempPeriodLength,
            onValueChange = { tempPeriodLength = it },
            valueRange = 2f..10f,
            steps = 7,
            colors = SliderDefaults.colors(
              thumbColor = Color(0xFF5A394B),
              activeTrackColor = Color(0xFF5A394B)
            )
          )
        }
      },
      confirmButton = {
        Button(
          onClick = {
            onSaveSettings(
              tempCycleLength.toInt(),
              tempPeriodLength.toInt(),
              userSettings.userName,
              userSettings.notificationsEnabled
            )
            showCycleSettingsDialog = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5A394B)),
          shape = RoundedCornerShape(20.dp)
        ) {
          Text("Save Changes", color = Color.White)
        }
      },
      dismissButton = {
        TextButton(onClick = { showCycleSettingsDialog = false }) {
          Text("Cancel", color = Color(0xFF8F7A85))
        }
      },
      shape = RoundedCornerShape(28.dp),
      containerColor = Color(0xFFFAF7F2)
    )
  }

  // Account Info Dialog
  if (showAccountDialog) {
    AlertDialog(
      onDismissRequest = { showAccountDialog = false },
      title = {
        Text(
          text = "Account Info",
          fontSize = 22.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF1E151A)
        )
      },
      text = {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text("User", fontSize = 14.sp, color = Color(0xFF8F7A85))
            Text(userSettings.userName, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1E151A))
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text("Member Since", fontSize = 14.sp, color = Color(0xFF8F7A85))
            Text("May 2026", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF1E151A))
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text("Membership", fontSize = 14.sp, color = Color(0xFF8F7A85))
            Text("Premium Active", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5A8466))
          }

          Spacer(modifier = Modifier.height(6.dp))

          Button(
            onClick = {
              showAccountDialog = false
              onOpenGateway()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5A394B)),
            shape = RoundedCornerShape(20.dp)
          ) {
            Text("Switch or Link Account", color = Color.White)
          }
        }
      },
      confirmButton = {
        TextButton(onClick = { showAccountDialog = false }) {
          Text("Done", color = Color(0xFF5A394B), fontWeight = FontWeight.SemiBold)
        }
      },
      shape = RoundedCornerShape(28.dp),
      containerColor = Color(0xFFFAF7F2)
    )
  }
}

@Composable
private fun ProfileMenuCard(
  item: ProfileMenuItem,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    onClick = onClick,
    modifier = modifier
      .fillMaxWidth()
      .height(76.dp)
      .shadow(
        elevation = 3.dp,
        shape = RoundedCornerShape(22.dp),
        ambientColor = Color(0x14201018),
        spotColor = Color(0x14201018)
      )
      .testTag(item.testTag),
    shape = RoundedCornerShape(22.dp),
    color = Color.White
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 18.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Icon Circle Badge
        Box(
          modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(item.badgeBgColor),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = item.iconColor,
            modifier = Modifier.size(24.dp)
          )
        }

        Spacer(modifier = Modifier.width(18.dp))

        // Title
        Text(
          text = item.title,
          fontSize = 18.sp,
          fontWeight = FontWeight.SemiBold,
          color = Color(0xFF1E151A),
          letterSpacing = (-0.2).sp
        )
      }

      // Chevron Right
      Icon(
        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
        contentDescription = "Open ${item.title}",
        tint = Color(0xFFC5B7BE),
        modifier = Modifier.size(24.dp)
      )
    }
  }
}

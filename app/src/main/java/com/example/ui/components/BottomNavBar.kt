package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.MauvePlum
import com.example.ui.theme.TextMuted
import com.example.ui.viewmodel.NavTab

@Composable
fun BottomNavBar(
  activeTab: NavTab,
  onTabSelected: (NavTab) -> Unit,
  onAddClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .shadow(
        elevation = 16.dp,
        ambientColor = Color(0x1A000000),
        spotColor = Color(0x1A000000)
      ),
    color = Color.White
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .navigationBarsPadding()
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .height(64.dp)
          .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Tab 1: Home
        BottomNavItem(
          icon = Icons.Filled.Home,
          label = "Home",
          isSelected = activeTab == NavTab.HOME,
          onClick = { onTabSelected(NavTab.HOME) },
          testTag = "nav_home",
          modifier = Modifier.weight(1f)
        )

        // Tab 2: Calendar
        BottomNavItem(
          icon = Icons.Outlined.CalendarMonth,
          label = "Calendar",
          isSelected = activeTab == NavTab.CALENDAR,
          onClick = { onTabSelected(NavTab.CALENDAR) },
          testTag = "nav_calendar",
          modifier = Modifier.weight(1f)
        )

        // Tab 3: Central Add Button (+)
        val isAddSelected = activeTab == NavTab.ADD
        Column(
          modifier = Modifier
            .weight(1f)
            .clickable(
              interactionSource = remember { MutableInteractionSource() },
              indication = null,
              onClick = {
                onTabSelected(NavTab.ADD)
                onAddClick()
              }
            )
            .testTag("nav_add_button"),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Box(
            modifier = Modifier
              .size(26.dp)
              .clip(CircleShape)
              .background(Color(0xFF503244)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Add,
              contentDescription = "Add",
              tint = Color.White,
              modifier = Modifier.size(18.dp)
            )
          }

          Spacer(modifier = Modifier.height(3.dp))

          Text(
            text = "Add",
            fontSize = 11.sp,
            fontWeight = if (isAddSelected) FontWeight.SemiBold else FontWeight.Medium,
            color = Color(0xFF22161E)
          )
        }

        // Tab 4: Insights
        val isInsightsSelected = activeTab == NavTab.INSIGHTS
        Column(
          modifier = Modifier
            .weight(1f)
            .clickable(
              interactionSource = remember { MutableInteractionSource() },
              indication = null,
              onClick = { onTabSelected(NavTab.INSIGHTS) }
            )
            .testTag("nav_insights"),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          // 3-Bar Chart Icon matching mockup
          Canvas(modifier = Modifier.size(22.dp)) {
            val tint = Color(0xFF22161E)
            val strokeWidth = 1.4.dp.toPx()
            val w = size.width
            val h = size.height

            // Bar 1 (left)
            drawRoundRect(
              color = tint,
              topLeft = Offset(w * 0.14f, h * 0.38f),
              size = Size(w * 0.18f, h * 0.54f),
              cornerRadius = CornerRadius(w * 0.09f, w * 0.09f),
              style = Stroke(width = strokeWidth)
            )

            // Bar 2 (center, tallest)
            drawRoundRect(
              color = tint,
              topLeft = Offset(w * 0.41f, h * 0.14f),
              size = Size(w * 0.18f, h * 0.78f),
              cornerRadius = CornerRadius(w * 0.09f, w * 0.09f),
              style = Stroke(width = strokeWidth)
            )

            // Bar 3 (right, medium-short)
            drawRoundRect(
              color = tint,
              topLeft = Offset(w * 0.68f, h * 0.48f),
              size = Size(w * 0.18f, h * 0.44f),
              cornerRadius = CornerRadius(w * 0.09f, w * 0.09f),
              style = Stroke(width = strokeWidth)
            )
          }

          Spacer(modifier = Modifier.height(3.dp))

          Text(
            text = "Insights",
            fontSize = 11.sp,
            fontWeight = if (isInsightsSelected) FontWeight.SemiBold else FontWeight.Medium,
            color = Color(0xFF22161E)
          )
        }

        // Tab 5: Profile
        BottomNavItem(
          icon = Icons.Outlined.Person,
          label = "Profile",
          isSelected = activeTab == NavTab.PROFILE,
          onClick = { onTabSelected(NavTab.PROFILE) },
          testTag = "nav_profile",
          modifier = Modifier.weight(1f)
        )
      }
    }
  }
}

@Composable
private fun BottomNavItem(
  icon: ImageVector,
  label: String,
  isSelected: Boolean,
  onClick: () -> Unit,
  testTag: String,
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
      .testTag(testTag),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Icon(
      imageVector = icon,
      contentDescription = label,
      tint = Color(0xFF22161E),
      modifier = Modifier.size(24.dp)
    )

    Spacer(modifier = Modifier.height(3.dp))

    Text(
      text = label,
      fontSize = 11.sp,
      fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
      color = Color(0xFF22161E)
    )
  }
}

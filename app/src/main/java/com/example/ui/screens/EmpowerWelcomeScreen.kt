package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.OutlinedFlowerSunIcon
import com.example.ui.components.OutlinedHealthInsightsIcon
import com.example.ui.components.OutlinedPregnancyIcon
import com.example.ui.components.WelcomeLinearDropIcon
import com.example.ui.theme.TextDark

enum class WelcomeFeature(val line1: String, val line2: String) {
  PERIOD("Period", "Tracker"),
  OVULATION("Ovulation", "Tracker"),
  PREGNANCY("Pregnancy", "Tracker"),
  INSIGHTS("Health", "Insights")
}

@Composable
fun EmpowerWelcomeScreen(
  onLetBegin: () -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedFeatureIndex by remember { mutableIntStateOf(0) }
  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFBF8F3))
  ) {
    // Top to mid celestial artwork
    Image(
      painter = painterResource(id = R.drawable.empower_rhythm_art_1787818023486),
      contentDescription = "Empower rhythm illustration",
      modifier = Modifier
        .fillMaxWidth()
        .height(520.dp)
        .align(Alignment.TopCenter),
      contentScale = ContentScale.Crop
    )

    // Soft gradient scrim fading artwork smoothly into background
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(520.dp)
        .background(
          Brush.verticalGradient(
            colors = listOf(
              Color.Transparent,
              Color(0x00FBF8F3),
              Color(0x4DFBF8F3),
              Color(0xFFFBF8F3)
            )
          )
        )
    )

    // Main Content
    Column(
      modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .navigationBarsPadding()
        .verticalScroll(scrollState)
        .padding(horizontal = 24.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Space matching the hero image focus
      Spacer(modifier = Modifier.height(440.dp))

      // Main Headline: "Empower your rhythm."
      Text(
        text = "Empower your rhythm.",
        style = MaterialTheme.typography.displayMedium.copy(
          fontSize = 36.sp,
          lineHeight = 42.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF1E171B),
          textAlign = TextAlign.Center
        ),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("welcome_empower_title")
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Subtitle: "Your journey, your health, your holistic well-being."
      Text(
        text = "Your journey, your health,\nyour holistic well-being.",
        style = MaterialTheme.typography.bodyLarge.copy(
          fontSize = 17.sp,
          lineHeight = 24.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF382932),
          textAlign = TextAlign.Center
        ),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("welcome_empower_subtitle")
      )

      Spacer(modifier = Modifier.height(30.dp))

      // 4 Circular Feature Badges in Row
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 440.dp)
          .testTag("empower_feature_row"),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        WelcomeCircleBadge(
          feature = WelcomeFeature.PERIOD,
          isSelected = selectedFeatureIndex == 0,
          onClick = { selectedFeatureIndex = 0 },
          icon = { WelcomeLinearDropIcon(size = 28.dp, tint = Color.White) },
          gradientColors = listOf(Color(0xFF8FA499), Color(0xFFB58793)),
          testTag = "badge_period_tracker"
        )

        WelcomeCircleBadge(
          feature = WelcomeFeature.OVULATION,
          isSelected = selectedFeatureIndex == 1,
          onClick = { selectedFeatureIndex = 1 },
          icon = { OutlinedFlowerSunIcon(size = 28.dp, tint = Color.White) },
          gradientColors = listOf(Color(0xFF8DA297), Color(0xFFB88C95)),
          testTag = "badge_ovulation_tracker"
        )

        WelcomeCircleBadge(
          feature = WelcomeFeature.PREGNANCY,
          isSelected = selectedFeatureIndex == 2,
          onClick = { selectedFeatureIndex = 2 },
          icon = { OutlinedPregnancyIcon(size = 28.dp, tint = Color.White) },
          gradientColors = listOf(Color(0xFF8FA499), Color(0xFFB48592)),
          testTag = "badge_pregnancy_tracker"
        )

        WelcomeCircleBadge(
          feature = WelcomeFeature.INSIGHTS,
          isSelected = selectedFeatureIndex == 3,
          onClick = { selectedFeatureIndex = 3 },
          icon = { OutlinedHealthInsightsIcon(size = 28.dp, tint = Color.White) },
          gradientColors = listOf(Color(0xFF8DA297), Color(0xFFB88B97)),
          testTag = "badge_health_insights"
        )
      }

      Spacer(modifier = Modifier.height(28.dp))

      // 4 Carousel Indicator Dots
      Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        repeat(4) { idx ->
          Box(
            modifier = Modifier
              .size(if (idx == selectedFeatureIndex) 8.dp else 6.dp)
              .clip(CircleShape)
              .background(
                if (idx == selectedFeatureIndex) Color(0xFF8B9E93) else Color(0xFFDACCC5)
              )
              .clickable { selectedFeatureIndex = idx }
          )
        }
      }

      Spacer(modifier = Modifier.height(28.dp))

      // Button: "Let's Begin ->" with Sage to Dusty Rose Gradient
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 440.dp)
          .height(58.dp)
          .shadow(
            elevation = 12.dp,
            shape = CircleShape,
            ambientColor = Color(0x33402D34),
            spotColor = Color(0x33402D34)
          )
          .clip(CircleShape)
          .background(
            Brush.horizontalGradient(
              colors = listOf(
                Color(0xFF7D9688),
                Color(0xFF9EA394),
                Color(0xFFB5878E)
              )
            )
          )
          .clickable(onClick = onLetBegin)
          .testTag("lets_begin_button"),
        contentAlignment = Alignment.Center
      ) {
        Row(
          modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Spacer(modifier = Modifier.weight(1f))

          Text(
            text = "Let’s Begin",
            style = MaterialTheme.typography.titleMedium.copy(
              fontSize = 19.sp,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Normal,
              color = Color.White
            )
          )

          Spacer(modifier = Modifier.weight(1f))

          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Begin",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

@Composable
private fun WelcomeCircleBadge(
  feature: WelcomeFeature,
  isSelected: Boolean,
  onClick: () -> Unit,
  icon: @Composable () -> Unit,
  gradientColors: List<Color>,
  testTag: String
) {
  val interactionSource = remember { MutableInteractionSource() }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
      )
      .testTag(testTag)
  ) {
    // Circular frosted gradient orb
    Box(
      modifier = Modifier
        .size(68.dp)
        .shadow(
          elevation = if (isSelected) 8.dp else 4.dp,
          shape = CircleShape,
          ambientColor = Color(0x26000000),
          spotColor = Color(0x26000000)
        )
        .clip(CircleShape)
        .background(
          Brush.linearGradient(
            colors = gradientColors
          )
        )
        .border(
          BorderStroke(
            width = if (isSelected) 2.5.dp else 1.2.dp,
            color = if (isSelected) Color.White else Color(0x80FFFFFF)
          ),
          shape = CircleShape
        ),
      contentAlignment = Alignment.Center
    ) {
      icon()
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = feature.line1,
      fontSize = 12.sp,
      fontWeight = FontWeight.Normal,
      color = TextDark,
      textAlign = TextAlign.Center
    )
    Text(
      text = feature.line2,
      fontSize = 12.sp,
      fontWeight = FontWeight.Normal,
      color = TextDark,
      textAlign = TextAlign.Center
    )
  }
}

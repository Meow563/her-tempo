package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.MauvePlumDark
import com.example.ui.theme.TextDark

/**
 * Custom golden/taupe outlined checkmark inside a subtle ring
 */
@Composable
fun GoldCheckIcon(
  modifier: Modifier = Modifier,
  size: Dp = 28.dp,
  tint: Color = Color(0xFFBCA188)
) {
  androidx.compose.foundation.Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height
    val strokeWidth = w * 0.07f

    // Outer circle outline
    drawCircle(
      color = tint,
      radius = w * 0.44f,
      style = Stroke(width = strokeWidth)
    )

    // Checkmark check path
    val checkPath = Path().apply {
      moveTo(w * 0.30f, h * 0.50f)
      lineTo(w * 0.44f, h * 0.65f)
      lineTo(w * 0.72f, h * 0.36f)
    }
    drawPath(
      path = checkPath,
      color = tint,
      style = Stroke(width = strokeWidth * 1.15f, cap = StrokeCap.Round)
    )
  }
}

@Composable
fun OnboardingSuccessScreen(
  onGoToDashboard: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFCEB8C8))
  ) {
    // Ethereal lavender / mauve wave background
    Image(
      painter = painterResource(id = R.drawable.img_success_bg_1787817770454),
      contentDescription = "Success backdrop",
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop
    )

    // Central & Footer layout container
    Column(
      modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .navigationBarsPadding()
        .padding(horizontal = 24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Spacer(modifier = Modifier.height(30.dp))

      // Center Elevated White Card
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .shadow(
            elevation = 20.dp,
            shape = RoundedCornerShape(32.dp),
            ambientColor = Color(0x33402D34),
            spotColor = Color(0x33402D34)
          )
          .testTag("success_card"),
        shape = RoundedCornerShape(32.dp),
        color = Color.White
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 48.dp),
          horizontalAlignment = Alignment.Start
        ) {
          // Headline: "You're All Set!"
          Text(
            text = "You’re All Set!",
            style = MaterialTheme.typography.displayMedium.copy(
              fontSize = 38.sp,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF38232F)
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("success_title"),
            textAlign = TextAlign.Center
          )

          Spacer(modifier = Modifier.height(42.dp))

          // Feature 1: Personalized insights
          SuccessFeatureRow(
            text = "Personalized insights",
            testTag = "feature_personalized_insights"
          )

          Spacer(modifier = Modifier.height(28.dp))

          // Feature 2: Smart reminders
          SuccessFeatureRow(
            text = "Smart reminders",
            testTag = "feature_smart_reminders"
          )

          Spacer(modifier = Modifier.height(28.dp))

          // Feature 3: Privacy protected
          SuccessFeatureRow(
            text = "Privacy protected",
            testTag = "feature_privacy_protected"
          )

          Spacer(modifier = Modifier.height(16.dp))
        }
      }

      // Bottom Button: "Go to Dashboard"
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Button(
          onClick = onGoToDashboard,
          modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .shadow(
              elevation = 12.dp,
              shape = CircleShape,
              ambientColor = Color(0x55402D34),
              spotColor = Color(0x55402D34)
            )
            .border(
              width = 1.dp,
              color = Color(0x66FFFFFF),
              shape = CircleShape
            )
            .testTag("go_to_dashboard_button"),
          shape = CircleShape,
          colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4A3440)
          )
        ) {
          Text(
            text = "Go to Dashboard",
            style = MaterialTheme.typography.titleMedium.copy(
              fontSize = 20.sp,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Normal,
              color = Color.White
            )
          )
        }
      }
    }
  }
}

@Composable
private fun SuccessFeatureRow(
  text: String,
  testTag: String
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .testTag(testTag),
    verticalAlignment = Alignment.CenterVertically
  ) {
    GoldCheckIcon(size = 30.dp, tint = Color(0xFFB99D85))

    Spacer(modifier = Modifier.width(18.dp))

    Text(
      text = text,
      style = MaterialTheme.typography.titleLarge.copy(
        fontSize = 22.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF38232F)
      )
    )
  }
}

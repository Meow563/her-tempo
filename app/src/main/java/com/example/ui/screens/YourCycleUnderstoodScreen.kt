package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
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

// Custom Shape for the top arched wave art (straight sides with downward curved convex bottom)
val ArchedBottomShape = GenericShape { size, _ ->
  val w = size.width
  val h = size.height
  val cornerRadius = 32f

  // Top-left rounded corner
  moveTo(0f, cornerRadius)
  quadraticTo(0f, 0f, cornerRadius, 0f)

  // Top edge
  lineTo(w - cornerRadius, 0f)

  // Top-right rounded corner
  quadraticTo(w, 0f, w, cornerRadius)

  // Right edge down to curve start
  val curveStartY = h * 0.45f
  lineTo(w, curveStartY)

  // Convex bottom bowl curve
  cubicTo(
    w, h * 0.88f,
    w * 0.82f, h,
    w * 0.5f, h
  )
  cubicTo(
    w * 0.18f, h,
    0f, h * 0.88f,
    0f, curveStartY
  )

  close()
}

@Composable
fun YourCycleUnderstoodScreen(
  onGetStarted: () -> Unit = {},
  onBack: (() -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFCFAF7))
  ) {
    // Scrollable container
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 44.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Top Arched Artwork Container
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(top = 10.dp, start = 12.dp, end = 12.dp)
            .clip(ArchedBottomShape)
            .background(Color(0xFFFAF7F2))
            .testTag("cycle_understood_art_container")
        ) {
          Image(
            painter = painterResource(id = R.drawable.personalized_waves_bg_1787988997650),
            contentDescription = "Fluid organic wave art in rose, sage, and gold",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
          )

          // Optional Back Button if launched from settings or sub-flow
          if (onBack != null) {
            Surface(
              onClick = onBack,
              modifier = Modifier
                .statusBarsPadding()
                .padding(start = 14.dp, top = 14.dp)
                .size(42.dp)
                .testTag("cycle_understood_back_button"),
              shape = CircleShape,
              color = Color.White.copy(alpha = 0.88f),
              shadowElevation = 3.dp
            ) {
              Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                  contentDescription = "Back",
                  tint = Color(0xFF2C2228),
                  modifier = Modifier.size(20.dp)
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Headline: "Your cycle,\nunderstood."
        Text(
          text = "Your cycle,\nunderstood.",
          fontSize = 42.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF140E12),
          textAlign = TextAlign.Center,
          lineHeight = 50.sp,
          letterSpacing = (-0.5).sp,
          modifier = Modifier
            .padding(horizontal = 24.dp)
            .testTag("cycle_understood_headline")
        )
      }

      Spacer(modifier = Modifier.height(48.dp))

      // Bottom "Get Started" Action Container
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 440.dp)
          .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Box(
          modifier = Modifier.fillMaxWidth(),
          contentAlignment = Alignment.Center
        ) {
          // Soft plum ambient glow behind button
          Box(
            modifier = Modifier
              .fillMaxWidth(0.92f)
              .height(52.dp)
              .offset(y = 4.dp)
              .background(
                Brush.radialGradient(
                  colors = listOf(
                    Color(0xFF5A394B).copy(alpha = 0.38f),
                    Color(0xFF5A394B).copy(alpha = 0.15f),
                    Color.Transparent
                  ),
                  radius = 300f
                ),
                shape = RoundedCornerShape(28.dp)
              )
              .blur(14.dp)
          )

          // Primary "Get Started" Pill Button
          Button(
            onClick = onGetStarted,
            modifier = Modifier
              .fillMaxWidth()
              .height(56.dp)
              .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0x335A394B),
                spotColor = Color(0x335A394B)
              )
              .testTag("cycle_understood_get_started_btn"),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF5A394B)
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
          ) {
            Text(
              text = "Get Started",
              fontSize = 18.sp,
              fontWeight = FontWeight.Medium,
              color = Color.White
            )
          }
        }
      }
    }
  }
}

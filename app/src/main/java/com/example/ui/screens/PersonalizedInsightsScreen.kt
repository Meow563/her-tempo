package com.example.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R

@Composable
fun PersonalizedInsightsScreen(
  onBack: () -> Unit = {},
  onNext: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFCFAF7))
  ) {
    // Scrollable Page Content
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 48.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Top artwork container with floating Insights card
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(490.dp)
      ) {
        // Art image container with rounded bottom mask
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(440.dp)
            .padding(top = 12.dp, start = 12.dp, end = 12.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(Color(0xFFFAF7F2))
        ) {
          Image(
            painter = painterResource(id = R.drawable.personalized_waves_bg_1787988997650),
            contentDescription = "Personalized wellness fluid wave art",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
          )

          // Soft gradient mask at bottom of art
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(
                Brush.verticalGradient(
                  colors = listOf(
                    Color.Transparent,
                    Color.Transparent,
                    Color(0x25FCFAF7),
                    Color(0x85FCFAF7),
                    Color(0xFFFCFAF7)
                  ),
                  startY = 300f,
                  endY = 1200f
                )
              )
          )

          // Floating Circular Back Button
          Surface(
            onClick = onBack,
            modifier = Modifier
              .statusBarsPadding()
              .padding(start = 14.dp, top = 14.dp)
              .size(42.dp)
              .testTag("personalized_back_button"),
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

        // Floating "Insights" Card overlapping the artwork exactly as shown in screenshot
        Card(
          modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .widthIn(max = 380.dp)
            .padding(horizontal = 28.dp)
            .offset(y = 10.dp)
            .shadow(
              elevation = 12.dp,
              shape = RoundedCornerShape(20.dp),
              ambientColor = Color(0x35402A35),
              spotColor = Color(0x35402A35)
            )
            .testTag("insights_chart_card"),
          shape = RoundedCornerShape(20.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 20.dp, vertical = 16.dp)
          ) {
            // "Insights" Title
            Text(
              text = "Insights",
              fontSize = 17.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF191316)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Badges row: Estrogen & Progesterone
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.Center
            ) {
              // Estrogen Badge
              Box(
                modifier = Modifier
                  .background(Color(0xFFFAF3F5), RoundedCornerShape(12.dp))
                  .border(0.75.dp, Color(0xFFECDCE2), RoundedCornerShape(12.dp))
                  .padding(horizontal = 10.dp, vertical = 4.dp)
              ) {
                Text(
                  text = "Estrogen",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Medium,
                  color = Color(0xFF7A4A57)
                )
              }

              Spacer(modifier = Modifier.width(20.dp))

              // Progesterone Badge
              Box(
                modifier = Modifier
                  .background(Color(0xFFF3F0F4), RoundedCornerShape(12.dp))
                  .border(0.75.dp, Color(0xFFDFD7E2), RoundedCornerShape(12.dp))
                  .padding(horizontal = 10.dp, vertical = 4.dp)
              ) {
                Text(
                  text = "Progesterone",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Medium,
                  color = Color(0xFF4A3845)
                )
              }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Hormonal Cycle Graph Canvas
            Canvas(
              modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
            ) {
              val w = size.width
              val h = size.height
              val baselineY = h * 0.88f

              // Light horizontal baseline
              drawLine(
                color = Color(0xFFECE4DF),
                start = Offset(0f, baselineY),
                end = Offset(w, baselineY),
                strokeWidth = 1.dp.toPx()
              )

              // Estrogen Curve (Rose / Blush Pink peak at ~36% width)
              val estrogenPath = Path().apply {
                moveTo(0f, baselineY - 12f)
                cubicTo(
                  w * 0.15f, baselineY - 18f,
                  w * 0.24f, h * 0.22f,
                  w * 0.36f, h * 0.22f
                )
                cubicTo(
                  w * 0.48f, h * 0.22f,
                  w * 0.55f, baselineY - 26f,
                  w * 0.72f, baselineY - 32f
                )
                cubicTo(
                  w * 0.85f, baselineY - 38f,
                  w * 0.94f, baselineY - 14f,
                  w, baselineY
                )
              }

              // Estrogen Filled Gradient
              val estrogenFill = Path().apply {
                addPath(estrogenPath)
                lineTo(w, baselineY)
                lineTo(0f, baselineY)
                close()
              }
              drawPath(
                path = estrogenFill,
                brush = Brush.verticalGradient(
                  colors = listOf(
                    Color(0xFFE4AAB4).copy(alpha = 0.45f),
                    Color(0xFFF9EAEF).copy(alpha = 0.15f),
                    Color.Transparent
                  ),
                  startY = h * 0.2f,
                  endY = baselineY
                )
              )

              // Draw Estrogen Curve Line
              drawPath(
                path = estrogenPath,
                color = Color(0xFFC77989),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
              )

              // Progesterone Curve (Plum / Mauve peak at ~66% width)
              val progPath = Path().apply {
                moveTo(0f, baselineY - 6f)
                cubicTo(
                  w * 0.20f, baselineY - 8f,
                  w * 0.40f, baselineY - 16f,
                  w * 0.52f, h * 0.48f
                )
                cubicTo(
                  w * 0.60f, h * 0.22f,
                  w * 0.70f, h * 0.22f,
                  w * 0.78f, h * 0.50f
                )
                cubicTo(
                  w * 0.88f, baselineY - 20f,
                  w * 0.95f, baselineY - 10f,
                  w, baselineY
                )
              }

              // Progesterone Filled Gradient
              val progFill = Path().apply {
                addPath(progPath)
                lineTo(w, baselineY)
                lineTo(0f, baselineY)
                close()
              }
              drawPath(
                path = progFill,
                brush = Brush.verticalGradient(
                  colors = listOf(
                    Color(0xFF7A586B).copy(alpha = 0.35f),
                    Color(0xFF9E8190).copy(alpha = 0.12f),
                    Color.Transparent
                  ),
                  startY = h * 0.2f,
                  endY = baselineY
                )
              )

              // Draw Progesterone Curve Line
              drawPath(
                path = progPath,
                color = Color(0xFF5E4554),
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
              )

              // Secondary Eucalyptus / Sage Wave
              val sagePath = Path().apply {
                moveTo(0f, baselineY - 24f)
                cubicTo(
                  w * 0.15f, baselineY - 32f,
                  w * 0.25f, baselineY - 18f,
                  w * 0.38f, baselineY - 40f
                )
                cubicTo(
                  w * 0.50f, baselineY - 55f,
                  w * 0.65f, baselineY - 58f,
                  w * 0.78f, baselineY - 45f
                )
                cubicTo(
                  w * 0.88f, baselineY - 25f,
                  w * 0.95f, baselineY - 18f,
                  w, baselineY - 8f
                )
              }
              drawPath(
                path = sagePath,
                color = Color(0xFF88A295),
                style = Stroke(width = 1.75.dp.toPx(), cap = StrokeCap.Round)
              )

              // Peak Dots / Markers
              // Estrogen peak dot
              drawCircle(
                color = Color(0xFFC77989),
                radius = 3.5.dp.toPx(),
                center = Offset(w * 0.36f, h * 0.22f)
              )
              drawCircle(
                color = Color.White,
                radius = 1.8.dp.toPx(),
                center = Offset(w * 0.36f, h * 0.22f)
              )

              // Progesterone peak dot
              drawCircle(
                color = Color(0xFF5E4554),
                radius = 3.5.dp.toPx(),
                center = Offset(w * 0.65f, h * 0.22f)
              )
              drawCircle(
                color = Color.White,
                radius = 1.8.dp.toPx(),
                center = Offset(w * 0.65f, h * 0.22f)
              )

              // Intersection markers matching screenshot
              drawCircle(
                color = Color(0xFF88A295),
                radius = 3.dp.toPx(),
                center = Offset(w * 0.36f, baselineY - 38f)
              )
              drawCircle(
                color = Color(0xFF5E4554),
                radius = 3.dp.toPx(),
                center = Offset(w * 0.36f, baselineY - 14f)
              )
              drawCircle(
                color = Color(0xFFC77989),
                radius = 3.dp.toPx(),
                center = Offset(w * 0.65f, baselineY - 46f)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(28.dp))

      // Text and CTA Body Container
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 440.dp)
          .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Headline: "Personalized\nfor you."
        Text(
          text = "Personalized\nfor you.",
          fontSize = 40.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF161014),
          textAlign = TextAlign.Center,
          lineHeight = 48.sp,
          letterSpacing = (-0.5).sp,
          modifier = Modifier.testTag("personalized_headline")
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Subtitle: "Receive tailored health tips\nand cycle predictions."
        Text(
          text = "Receive tailored health tips\nand cycle predictions.",
          fontSize = 17.sp,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF3C3037),
          textAlign = TextAlign.Center,
          lineHeight = 24.sp,
          modifier = Modifier.testTag("personalized_subtitle")
        )

        Spacer(modifier = Modifier.height(38.dp))

        // Primary Action: "Next" Pill Button with soft glow
        Box(
          modifier = Modifier.fillMaxWidth(),
          contentAlignment = Alignment.Center
        ) {
          // Soft ambient shadow glow
          Box(
            modifier = Modifier
              .fillMaxWidth(0.92f)
              .height(52.dp)
              .offset(y = 4.dp)
              .background(
                Brush.radialGradient(
                  colors = listOf(
                    Color(0xFF5A394B).copy(alpha = 0.35f),
                    Color(0xFF5A394B).copy(alpha = 0.15f),
                    Color.Transparent
                  ),
                  radius = 300f
                ),
                shape = RoundedCornerShape(28.dp)
              )
              .blur(14.dp)
          )

          Button(
            onClick = onNext,
            modifier = Modifier
              .fillMaxWidth()
              .height(56.dp)
              .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0x335A394B),
                spotColor = Color(0x335A394B)
              )
              .testTag("personalized_next_button"),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF5A394B)
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
          ) {
            Text(
              text = "Next",
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

package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
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
import kotlinx.coroutines.launch

enum class MoodType(
  val label: String,
  val selectedBgColor: Color,
  val selectedBorderColor: Color,
  val selectedIconColor: Color
) {
  CALM(
    label = "Calm",
    selectedBgColor = Color(0xFFFBF0F5),
    selectedBorderColor = Color(0xFF553547),
    selectedIconColor = Color(0xFF2A1622)
  ),
  HAPPY(
    label = "Happy",
    selectedBgColor = Color(0xFFFBF1F5),
    selectedBorderColor = Color(0xFF553547),
    selectedIconColor = Color(0xFF2A1622)
  ),
  TIRED(
    label = "Tired",
    selectedBgColor = Color(0xFFFBF1F5),
    selectedBorderColor = Color(0xFF553547),
    selectedIconColor = Color(0xFF2A1622)
  ),
  ANXIOUS(
    label = "Anxious",
    selectedBgColor = Color(0xFFFBF1F5),
    selectedBorderColor = Color(0xFF553547),
    selectedIconColor = Color(0xFF2A1622)
  ),
  FOCUSED(
    label = "Focused",
    selectedBgColor = Color(0xFFFBF1F5),
    selectedBorderColor = Color(0xFF553547),
    selectedIconColor = Color(0xFF2A1622)
  ),
  FRUSTRATED(
    label = "Frustrated",
    selectedBgColor = Color(0xFFFBF1F5),
    selectedBorderColor = Color(0xFF553547),
    selectedIconColor = Color(0xFF2A1622)
  )
}

@Composable
fun HowAreYouFeelingScreen(
  onSaveEntry: (mood: String, note: String) -> Unit,
  onNavigateHome: () -> Unit = {},
  initialMood: String = "Calm",
  initialNote: String = "",
  modifier: Modifier = Modifier
) {
  val verticalScrollState = rememberScrollState()
  val horizontalChipsScrollState = rememberScrollState()
  val snackbarHostState = remember { SnackbarHostState() }
  val coroutineScope = rememberCoroutineScope()

  var selectedMood by remember {
    mutableStateOf(
      MoodType.values().firstOrNull { it.label.equals(initialMood, ignoreCase = true) } ?: MoodType.CALM
    )
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(
        Brush.verticalGradient(
          colors = listOf(
            Color(0xFFFCF9F5),
            Color(0xFFFAF6F0),
            Color(0xFFF7F2EA)
          )
        )
      )
  ) {
    // Top background artwork with watercolor flowing ribbons and circular dancers
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(520.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.feeling_today_exact_art_1787991656417),
        contentDescription = "Supportive Watercolor Art",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
      )

      // Seamless radiant gradient overlay blending the artwork down to background
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(160.dp)
          .align(Alignment.BottomCenter)
          .background(
            Brush.verticalGradient(
              colors = listOf(
                Color.Transparent,
                Color(0xFFFAF6F0).copy(alpha = 0.55f),
                Color(0xFFFAF6F0).copy(alpha = 0.92f),
                Color(0xFFFAF6F0)
              )
            )
          )
      )
    }

    // Main Foreground Content
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(verticalScrollState)
        .padding(bottom = 96.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.height(272.dp))

      // Center Frosted Glass Question Card
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 22.dp)
          .shadow(
            elevation = 12.dp,
            shape = RoundedCornerShape(28.dp),
            ambientColor = Color(0x282C1A25),
            spotColor = Color(0x282C1A25)
          )
          .border(
            width = 1.2.dp,
            brush = Brush.verticalGradient(
              colors = listOf(
                Color.White.copy(alpha = 0.98f),
                Color.White.copy(alpha = 0.70f)
              )
            ),
            shape = RoundedCornerShape(28.dp)
          )
          .testTag("feeling_question_card"),
        shape = RoundedCornerShape(28.dp),
        color = Color.White.copy(alpha = 0.86f)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .height(205.dp)
            .padding(horizontal = 28.dp, vertical = 28.dp)
        ) {
          Text(
            text = "How are you feeling\ntoday?",
            fontSize = 32.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF140A10),
            lineHeight = 40.sp,
            letterSpacing = (-0.4).sp,
            modifier = Modifier.testTag("feeling_title_text")
          )
        }
      }

      Spacer(modifier = Modifier.height(28.dp))

      // Horizontal Row of Mood Tiles matching the design
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(horizontalChipsScrollState)
          .padding(horizontal = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        MoodType.values().forEach { mood ->
          MoodButtonTile(
            mood = mood,
            isSelected = selectedMood == mood,
            onSelect = { selectedMood = mood }
          )
        }
      }

      Spacer(modifier = Modifier.height(30.dp))

      // "Save Entry" Action Button with rich plum gradient & warm luminescence
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 22.dp)
          .shadow(
            elevation = 14.dp,
            shape = RoundedCornerShape(30.dp),
            ambientColor = Color(0x70523447),
            spotColor = Color(0x70523447)
          )
      ) {
        Button(
          onClick = {
            onSaveEntry(selectedMood.label, "")
            coroutineScope.launch {
              snackbarHostState.showSnackbar(
                message = "Saved daily feeling: ${selectedMood.label}",
                duration = SnackbarDuration.Short
              )
            }
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .background(
              brush = Brush.horizontalGradient(
                colors = listOf(
                  Color(0xFF5A374E),
                  Color(0xFF4E2E42),
                  Color(0xFF45263A)
                )
              ),
              shape = RoundedCornerShape(30.dp)
            )
            .testTag("save_feeling_entry_button"),
          shape = RoundedCornerShape(30.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
          )
        ) {
          Text(
            text = "Save Entry",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.3.sp,
            color = Color.White
          )
        }
      }
    }

    // Snackbar Host
    SnackbarHost(
      hostState = snackbarHostState,
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .padding(bottom = 98.dp)
    )
  }
}

@Composable
private fun MoodButtonTile(
  mood: MoodType,
  isSelected: Boolean,
  onSelect: () -> Unit,
  modifier: Modifier = Modifier
) {
  val borderColor by animateColorAsState(
    targetValue = if (isSelected) mood.selectedBorderColor else Color.Transparent,
    animationSpec = tween(durationMillis = 200),
    label = "tileBorder"
  )
  val backgroundColor by animateColorAsState(
    targetValue = if (isSelected) mood.selectedBgColor else Color(0xFFE2EBE5),
    animationSpec = tween(durationMillis = 200),
    label = "tileBg"
  )
  val iconColor by animateColorAsState(
    targetValue = if (isSelected) mood.selectedIconColor else Color(0xFF221A1F),
    animationSpec = tween(durationMillis = 200),
    label = "tileIconColor"
  )

  Surface(
    onClick = onSelect,
    modifier = modifier
      .size(width = 64.dp, height = 72.dp)
      .shadow(
        elevation = if (isSelected) 6.dp else 2.5.dp,
        shape = RoundedCornerShape(18.dp),
        ambientColor = if (isSelected) Color(0x3B553547) else Color(0x1F22362A),
        spotColor = if (isSelected) Color(0x3B553547) else Color(0x1F22362A)
      )
      .border(
        width = if (isSelected) 1.6.dp else 0.dp,
        color = borderColor,
        shape = RoundedCornerShape(18.dp)
      )
      .testTag("mood_tile_${mood.name.lowercase()}"),
    shape = RoundedCornerShape(18.dp),
    color = backgroundColor
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp, bottom = 8.dp, start = 2.dp, end = 2.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      MoodIconRenderer(
        mood = mood,
        tint = iconColor,
        modifier = Modifier.size(28.dp)
      )

      Text(
        text = mood.label,
        fontSize = 12.sp,
        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
        fontFamily = FontFamily.SansSerif,
        color = iconColor,
        textAlign = TextAlign.Center,
        maxLines = 1
      )
    }
  }
}

@Composable
private fun MoodIconRenderer(
  mood: MoodType,
  tint: Color,
  modifier: Modifier = Modifier
) {
  Canvas(modifier = modifier) {
    val w = size.width
    val h = size.height
    val strokeWidth = 1.55.dp.toPx()

    when (mood) {
      MoodType.CALM -> {
        // Outer Circle
        drawCircle(
          color = tint,
          radius = w * 0.44f,
          center = Offset(w * 0.5f, h * 0.5f),
          style = Stroke(width = strokeWidth)
        )

        // Top right crescent moon
        val moonPath = Path().apply {
          val cx = w * 0.62f
          val cy = h * 0.36f
          val r = w * 0.15f
          moveTo(cx, cy - r)
          cubicTo(cx + r * 1.05f, cy - r * 0.35f, cx + r * 1.05f, cy + r * 0.35f, cx, cy + r)
          cubicTo(cx + r * 0.35f, cy + r * 0.25f, cx + r * 0.35f, cy - r * 0.25f, cx, cy - r)
          close()
        }
        drawPath(path = moonPath, color = tint)

        // Wave 1
        val wave1 = Path().apply {
          moveTo(w * 0.22f, h * 0.56f)
          cubicTo(w * 0.34f, h * 0.49f, w * 0.46f, h * 0.63f, w * 0.58f, h * 0.56f)
          cubicTo(w * 0.68f, h * 0.50f, w * 0.72f, h * 0.56f, w * 0.78f, h * 0.56f)
        }
        drawPath(path = wave1, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))

        // Wave 2
        val wave2 = Path().apply {
          moveTo(w * 0.28f, h * 0.70f)
          cubicTo(w * 0.38f, h * 0.64f, w * 0.48f, h * 0.76f, w * 0.60f, h * 0.70f)
          cubicTo(w * 0.68f, h * 0.65f, w * 0.70f, h * 0.70f, w * 0.74f, h * 0.70f)
        }
        drawPath(path = wave2, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))
      }

      MoodType.HAPPY -> {
        // Outer Circle
        drawCircle(
          color = tint,
          radius = w * 0.44f,
          center = Offset(w * 0.5f, h * 0.5f),
          style = Stroke(width = strokeWidth)
        )

        // Arched Smiling Eyes
        val leftEye = Path().apply {
          moveTo(w * 0.30f, h * 0.42f)
          cubicTo(w * 0.35f, h * 0.32f, w * 0.41f, h * 0.32f, w * 0.46f, h * 0.42f)
        }
        drawPath(path = leftEye, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))

        val rightEye = Path().apply {
          moveTo(w * 0.54f, h * 0.42f)
          cubicTo(w * 0.59f, h * 0.32f, w * 0.65f, h * 0.32f, w * 0.70f, h * 0.42f)
        }
        drawPath(path = rightEye, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))

        // Open Grinning Smile
        val mouth = Path().apply {
          moveTo(w * 0.30f, h * 0.56f)
          cubicTo(w * 0.38f, h * 0.80f, w * 0.62f, h * 0.80f, w * 0.70f, h * 0.56f)
          close()
        }
        drawPath(path = mouth, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))
      }

      MoodType.TIRED -> {
        // Outer Circle
        drawCircle(
          color = tint,
          radius = w * 0.44f,
          center = Offset(w * 0.5f, h * 0.5f),
          style = Stroke(width = strokeWidth)
        )

        // Downward Sleepy Eyes
        val leftEye = Path().apply {
          moveTo(w * 0.28f, h * 0.40f)
          cubicTo(w * 0.34f, h * 0.48f, w * 0.40f, h * 0.48f, w * 0.46f, h * 0.40f)
        }
        drawPath(path = leftEye, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))

        val rightEye = Path().apply {
          moveTo(w * 0.54f, h * 0.40f)
          cubicTo(w * 0.60f, h * 0.48f, w * 0.66f, h * 0.48f, w * 0.72f, h * 0.40f)
        }
        drawPath(path = rightEye, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))

        // Flat/Neutral Mouth
        drawLine(
          color = tint,
          start = Offset(w * 0.38f, h * 0.66f),
          end = Offset(w * 0.62f, h * 0.66f),
          strokeWidth = strokeWidth,
          cap = StrokeCap.Round
        )

        // Small Zzz in top right
        val z1 = Path().apply {
          moveTo(w * 0.72f, h * 0.20f)
          lineTo(w * 0.84f, h * 0.20f)
          lineTo(w * 0.72f, h * 0.32f)
          lineTo(w * 0.84f, h * 0.32f)
        }
        drawPath(path = z1, color = tint, style = Stroke(width = strokeWidth * 0.85f, cap = StrokeCap.Round, join = StrokeJoin.Miter))
      }

      MoodType.ANXIOUS -> {
        // Rounded Alert Triangle
        val triangle = Path().apply {
          moveTo(w * 0.5f, h * 0.16f)
          lineTo(w * 0.86f, h * 0.82f)
          lineTo(w * 0.14f, h * 0.82f)
          close()
        }
        drawPath(path = triangle, color = tint, style = Stroke(width = strokeWidth, join = StrokeJoin.Round, cap = StrokeCap.Round))

        // Exclamation Mark
        drawLine(
          color = tint,
          start = Offset(w * 0.5f, h * 0.38f),
          end = Offset(w * 0.5f, h * 0.58f),
          strokeWidth = strokeWidth * 1.1f,
          cap = StrokeCap.Round
        )
        drawCircle(
          color = tint,
          radius = strokeWidth * 0.75f,
          center = Offset(w * 0.5f, h * 0.70f)
        )
      }

      MoodType.FOCUSED -> {
        // Concentric Target Rings
        drawCircle(
          color = tint,
          radius = w * 0.40f,
          center = Offset(w * 0.46f, h * 0.54f),
          style = Stroke(width = strokeWidth)
        )
        drawCircle(
          color = tint,
          radius = w * 0.24f,
          center = Offset(w * 0.46f, h * 0.54f),
          style = Stroke(width = strokeWidth)
        )
        drawCircle(
          color = tint,
          radius = w * 0.09f,
          center = Offset(w * 0.46f, h * 0.54f)
        )

        // Diagonal Arrow / Dart
        drawLine(
          color = tint,
          start = Offset(w * 0.82f, h * 0.18f),
          end = Offset(w * 0.52f, h * 0.48f),
          strokeWidth = strokeWidth * 1.1f,
          cap = StrokeCap.Round
        )
        drawLine(
          color = tint,
          start = Offset(w * 0.72f, h * 0.18f),
          end = Offset(w * 0.82f, h * 0.18f),
          strokeWidth = strokeWidth,
          cap = StrokeCap.Round
        )
        drawLine(
          color = tint,
          start = Offset(w * 0.82f, h * 0.18f),
          end = Offset(w * 0.82f, h * 0.28f),
          strokeWidth = strokeWidth,
          cap = StrokeCap.Round
        )
      }

      MoodType.FRUSTRATED -> {
        // Outer Circle
        drawCircle(
          color = tint,
          radius = w * 0.44f,
          center = Offset(w * 0.5f, h * 0.5f),
          style = Stroke(width = strokeWidth)
        )
        // Slanted Eyebrows & Eyes
        drawLine(
          color = tint,
          start = Offset(w * 0.28f, h * 0.36f),
          end = Offset(w * 0.44f, h * 0.44f),
          strokeWidth = strokeWidth,
          cap = StrokeCap.Round
        )
        drawLine(
          color = tint,
          start = Offset(w * 0.72f, h * 0.36f),
          end = Offset(w * 0.56f, h * 0.44f),
          strokeWidth = strokeWidth,
          cap = StrokeCap.Round
        )
        // Frown Mouth
        val frown = Path().apply {
          moveTo(w * 0.32f, h * 0.70f)
          cubicTo(w * 0.42f, h * 0.60f, w * 0.58f, h * 0.60f, w * 0.68f, h * 0.70f)
        }
        drawPath(path = frown, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))
      }
    }
  }
}

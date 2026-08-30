package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.TextDark
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Biometric / Face ID Scanner Icon
 */
@Composable
fun FaceIdScannerIcon(
  modifier: Modifier = Modifier,
  size: Dp = 22.dp,
  tint: Color = TextDark
) {
  androidx.compose.foundation.Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height
    val strokeWidth = w * 0.08f
    val cornerLen = w * 0.22f

    // 4 Corner Brackets
    // Top-left
    val tl = Path().apply {
      moveTo(w * 0.12f, h * 0.12f + cornerLen)
      lineTo(w * 0.12f, h * 0.12f)
      lineTo(w * 0.12f + cornerLen, h * 0.12f)
    }
    drawPath(tl, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))

    // Top-right
    val tr = Path().apply {
      moveTo(w * 0.88f - cornerLen, h * 0.12f)
      lineTo(w * 0.88f, h * 0.12f)
      lineTo(w * 0.88f, h * 0.12f + cornerLen)
    }
    drawPath(tr, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))

    // Bottom-left
    val bl = Path().apply {
      moveTo(w * 0.12f, h * 0.88f - cornerLen)
      lineTo(w * 0.12f, h * 0.88f)
      lineTo(w * 0.12f + cornerLen, h * 0.88f)
    }
    drawPath(bl, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))

    // Bottom-right
    val br = Path().apply {
      moveTo(w * 0.88f - cornerLen, h * 0.88f)
      lineTo(w * 0.88f, h * 0.88f)
      lineTo(w * 0.88f, h * 0.88f - cornerLen)
    }
    drawPath(br, color = tint, style = Stroke(width = strokeWidth, cap = StrokeCap.Round))

    // Eyes
    drawCircle(color = tint, radius = w * 0.04f, center = androidx.compose.ui.geometry.Offset(w * 0.36f, h * 0.42f))
    drawCircle(color = tint, radius = w * 0.04f, center = androidx.compose.ui.geometry.Offset(w * 0.64f, h * 0.42f))

    // Nose
    val nose = Path().apply {
      moveTo(w * 0.5f, h * 0.46f)
      lineTo(w * 0.5f, h * 0.58f)
      lineTo(w * 0.44f, h * 0.58f)
    }
    drawPath(nose, color = tint, style = Stroke(width = strokeWidth * 0.8f, cap = StrokeCap.Round))

    // Smile
    val smile = Path().apply {
      moveTo(w * 0.36f, h * 0.68f)
      quadraticTo(w * 0.5f, h * 0.78f, w * 0.64f, h * 0.68f)
    }
    drawPath(smile, color = tint, style = Stroke(width = strokeWidth * 0.8f, cap = StrokeCap.Round))
  }
}

@Composable
fun PasscodeLockScreen(
  onUnlockSuccess: () -> Unit,
  appName: String = "CycleWell",
  modifier: Modifier = Modifier
) {
  var passcode by remember { mutableStateOf("") }
  val maxDigits = 4
  val shakeOffset = remember { Animatable(0f) }
  val coroutineScope = rememberCoroutineScope()

  fun handleDigit(d: String) {
    if (passcode.length < maxDigits) {
      val newCode = passcode + d
      passcode = newCode
      if (newCode.length == maxDigits) {
        coroutineScope.launch {
          delay(180)
          onUnlockSuccess()
        }
      }
    }
  }

  fun handleBackspace() {
    if (passcode.isNotEmpty()) {
      passcode = passcode.dropLast(1)
    }
  }

  fun handleFaceId() {
    coroutineScope.launch {
      passcode = "••••"
      delay(300)
      onUnlockSuccess()
    }
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFE5D5D8))
  ) {
    // Atmospheric Blurred Watercolor Artwork Backdrop
    Image(
      painter = painterResource(id = R.drawable.img_passcode_lock_bg_1787818421798),
      contentDescription = "Lock screen backdrop",
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop
    )

    // Soft gradient scrim to ensure high text contrast
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.verticalGradient(
            colors = listOf(
              Color(0x33000000),
              Color(0x1A000000),
              Color(0x26000000)
            )
          )
        )
    )

    // Passcode UI Column
    Column(
      modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .navigationBarsPadding()
        .padding(horizontal = 24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Spacer(modifier = Modifier.height(28.dp))

      // Header: "Enter Passcode" / "to unlock CycleWell."
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          text = "Enter Passcode",
          style = MaterialTheme.typography.headlineLarge.copy(
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            textAlign = TextAlign.Center
          ),
          modifier = Modifier.testTag("passcode_title")
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
          text = "to unlock $appName.",
          style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xEEFFFFFF),
            textAlign = TextAlign.Center
          ),
          modifier = Modifier.testTag("passcode_subtitle")
        )

        Spacer(modifier = Modifier.height(38.dp))

        // 4 Glowing Frosted Pin Bubble Indicators with Gold Outline
        Row(
          modifier = Modifier
            .offset { IntOffset(shakeOffset.value.toInt(), 0) }
            .testTag("passcode_pin_row"),
          horizontalArrangement = Arrangement.spacedBy(16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          repeat(maxDigits) { index ->
            val isFilled = index < passcode.length
            PasscodeBubbleIndicator(isFilled = isFilled)
          }
        }
      }

      // Numeric Keypad Grid (1 to 9, 0, Backspace)
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 360.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Row 1: 1, 2, 3
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceEvenly
        ) {
          KeypadButton(digit = "1", onClick = { handleDigit("1") })
          KeypadButton(digit = "2", onClick = { handleDigit("2") })
          KeypadButton(digit = "3", onClick = { handleDigit("3") })
        }

        // Row 2: 4, 5, 6
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceEvenly
        ) {
          KeypadButton(digit = "4", onClick = { handleDigit("4") })
          KeypadButton(digit = "5", onClick = { handleDigit("5") })
          KeypadButton(digit = "6", onClick = { handleDigit("6") })
        }

        // Row 3: 7, 8, 9
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceEvenly
        ) {
          KeypadButton(digit = "7", onClick = { handleDigit("7") })
          KeypadButton(digit = "8", onClick = { handleDigit("8") })
          KeypadButton(digit = "9", onClick = { handleDigit("9") })
        }

        // Row 4: Empty spacer, 0, Backspace
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceEvenly
        ) {
          Box(modifier = Modifier.size(76.dp)) // Spacer to keep grid balanced

          KeypadButton(digit = "0", onClick = { handleDigit("0") })

          KeypadActionButton(
            onClick = { handleBackspace() },
            testTag = "backspace_btn"
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.Backspace,
              contentDescription = "Backspace",
              tint = Color(0xFF2B1F26),
              modifier = Modifier.size(26.dp)
            )
          }
        }
      }

      // Bottom Pill: "Use Face ID"
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Surface(
          onClick = { handleFaceId() },
          modifier = Modifier
            .height(48.dp)
            .shadow(
              elevation = 6.dp,
              shape = CircleShape,
              ambientColor = Color(0x33000000),
              spotColor = Color(0x33000000)
            )
            .testTag("face_id_btn"),
          shape = CircleShape,
          color = Color(0xCCF7F3EE)
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 22.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
          ) {
            FaceIdScannerIcon(size = 20.dp, tint = Color(0xFF2B1F26))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
              text = "Use Face ID",
              fontSize = 16.sp,
              fontWeight = FontWeight.Medium,
              color = Color(0xFF2B1F26)
            )
          }
        }
      }
    }
  }
}

/**
 * Frosted circular passcode pin slot with gold halo border ring
 */
@Composable
private fun PasscodeBubbleIndicator(
  isFilled: Boolean,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .size(62.dp)
      .shadow(
        elevation = 6.dp,
        shape = CircleShape,
        ambientColor = Color(0x2B4A3440),
        spotColor = Color(0x2B4A3440)
      )
      .clip(CircleShape)
      .background(
        Brush.radialGradient(
          colors = listOf(
            Color(0xF0FFFFFF),
            Color(0xCCF3ECE6)
          )
        )
      )
      .border(
        BorderStroke(
          width = 1.8.dp,
          brush = Brush.linearGradient(
            colors = listOf(
              Color(0xFFE8C88B),
              Color(0xFFCE9E64),
              Color(0xFFE8C88B)
            )
          )
        ),
        shape = CircleShape
      ),
    contentAlignment = Alignment.Center
  ) {
    if (isFilled) {
      // Filled glowing dot / pearl
      Box(
        modifier = Modifier
          .size(18.dp)
          .clip(CircleShape)
          .background(
            Brush.radialGradient(
              colors = listOf(
                Color(0xFF4A3440),
                Color(0xFF6B485C)
              )
            )
          )
      )
    }
  }
}

/**
 * Keypad number button (76.dp circular frosted surface with gold rim)
 */
@Composable
private fun KeypadButton(
  digit: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val interactionSource = remember { MutableInteractionSource() }

  Box(
    modifier = modifier
      .size(76.dp)
      .shadow(
        elevation = 5.dp,
        shape = CircleShape,
        ambientColor = Color(0x33000000),
        spotColor = Color(0x33000000)
      )
      .clip(CircleShape)
      .background(
        Brush.radialGradient(
          colors = listOf(
            Color(0xF5FFFFFF),
            Color(0xDBF2EAE4)
          )
        )
      )
      .border(
        BorderStroke(
          width = 1.6.dp,
          brush = Brush.linearGradient(
            colors = listOf(
              Color(0xFFE5C384),
              Color(0xFFC7985F),
              Color(0xFFE5C384)
            )
          )
        ),
        shape = CircleShape
      )
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
      )
      .testTag("digit_btn_$digit"),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = digit,
      fontSize = 28.sp,
      fontWeight = FontWeight.Normal,
      color = Color(0xFF1E171B)
    )
  }
}

/**
 * Keypad action button (e.g. Backspace)
 */
@Composable
private fun KeypadActionButton(
  onClick: () -> Unit,
  testTag: String,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit
) {
  val interactionSource = remember { MutableInteractionSource() }

  Box(
    modifier = modifier
      .size(76.dp)
      .shadow(
        elevation = 5.dp,
        shape = CircleShape,
        ambientColor = Color(0x33000000),
        spotColor = Color(0x33000000)
      )
      .clip(CircleShape)
      .background(
        Brush.radialGradient(
          colors = listOf(
            Color(0xF0FFFFFF),
            Color(0xCEF0E6E0)
          )
        )
      )
      .border(
        BorderStroke(
          width = 1.6.dp,
          brush = Brush.linearGradient(
            colors = listOf(
              Color(0xFFE5C384),
              Color(0xFFC7985F),
              Color(0xFFE5C384)
            )
          )
        ),
        shape = CircleShape
      )
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
      )
      .testTag(testTag),
    contentAlignment = Alignment.Center
  ) {
    content()
  }
}

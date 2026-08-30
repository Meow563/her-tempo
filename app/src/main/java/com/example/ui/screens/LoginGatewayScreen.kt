package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.R
import com.example.ui.components.AppleLogoIcon
import com.example.ui.components.GoogleLogoIcon
import com.example.ui.components.OvulationPhaseIcon
import com.example.ui.components.PeriodDropIcon
import com.example.ui.components.PregnancySilhouetteIcon
import com.example.ui.components.WellnessLotusIcon
import com.example.ui.theme.CreamBackground
import com.example.ui.theme.MauveDrop
import com.example.ui.theme.MauvePlum
import com.example.ui.theme.MauvePlumDark
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted

enum class HealthGoal(val title: String, val subtitle: String) {
  PERIOD("Period", "Tracking"),
  OVULATION("Ovulation", "Tracking"),
  PREGNANCY("Pregnancy", "Tracking"),
  WELLNESS("Wellness", "& More")
}

@Composable
fun LoginGatewayScreen(
  onContinueAsGuest: () -> Unit,
  onSignInSuccess: (userName: String, email: String, goal: HealthGoal) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedGoal by remember { mutableStateOf(HealthGoal.PERIOD) }
  var showEmailDialog by remember { mutableStateOf(false) }
  var isSignUpMode by remember { mutableStateOf(false) }

  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(CreamBackground)
  ) {
    // Ethereal top flowing wave artwork
    Image(
      painter = painterResource(id = R.drawable.img_wavy_header_bg),
      contentDescription = "Silk wave background",
      modifier = Modifier
        .fillMaxWidth()
        .height(420.dp)
        .align(Alignment.TopCenter),
      contentScale = ContentScale.Crop
    )

    // Gentle gradient overlay on top for smooth contrast
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(420.dp)
        .background(
          Brush.verticalGradient(
            colors = listOf(
              Color(0x00FAF7F2),
              Color(0x66FAF7F2),
              Color(0xFFFAF7F2)
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
      Spacer(modifier = Modifier.height(36.dp))

      // Main Display Title: "Your health, in your rhythm."
      Text(
        text = "Your health,\nin your rhythm.",
        style = MaterialTheme.typography.displayLarge.copy(
          fontSize = 40.sp,
          lineHeight = 48.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = TextDark,
          textAlign = TextAlign.Center
        ),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("login_title")
          .padding(horizontal = 8.dp)
      )

      Spacer(modifier = Modifier.height(42.dp))

      // 4 Health Goal Cards in Horizontal Row
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .testTag("goal_cards_row"),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        GoalFeatureCard(
          goal = HealthGoal.PERIOD,
          isSelected = selectedGoal == HealthGoal.PERIOD,
          onClick = { selectedGoal = HealthGoal.PERIOD },
          iconContent = {
            PeriodDropIcon(
              size = 32.dp,
              tint = if (selectedGoal == HealthGoal.PERIOD) MauveDrop else MauveDrop.copy(alpha = 0.85f)
            )
          },
          modifier = Modifier.weight(1f)
        )

        GoalFeatureCard(
          goal = HealthGoal.OVULATION,
          isSelected = selectedGoal == HealthGoal.OVULATION,
          onClick = { selectedGoal = HealthGoal.OVULATION },
          iconContent = {
            OvulationPhaseIcon(
              size = 32.dp,
              tint = if (selectedGoal == HealthGoal.OVULATION) MauveDrop else MauveDrop.copy(alpha = 0.85f)
            )
          },
          modifier = Modifier.weight(1f)
        )

        GoalFeatureCard(
          goal = HealthGoal.PREGNANCY,
          isSelected = selectedGoal == HealthGoal.PREGNANCY,
          onClick = { selectedGoal = HealthGoal.PREGNANCY },
          iconContent = {
            PregnancySilhouetteIcon(
              size = 32.dp,
              tint = if (selectedGoal == HealthGoal.PREGNANCY) MauveDrop else MauveDrop.copy(alpha = 0.85f)
            )
          },
          modifier = Modifier.weight(1f)
        )

        GoalFeatureCard(
          goal = HealthGoal.WELLNESS,
          isSelected = selectedGoal == HealthGoal.WELLNESS,
          onClick = { selectedGoal = HealthGoal.WELLNESS },
          iconContent = {
            WellnessLotusIcon(
              size = 32.dp,
              tint = if (selectedGoal == HealthGoal.WELLNESS) MauveDrop else MauveDrop.copy(alpha = 0.85f)
            )
          },
          modifier = Modifier.weight(1f)
        )
      }

      Spacer(modifier = Modifier.height(48.dp))

      // Button 1: Continue with Google
      AuthActionButton(
        icon = { GoogleLogoIcon(size = 22.dp) },
        label = "Continue with Google",
        backgroundColor = Color.White,
        textColor = Color(0xFF1E1E1E),
        onClick = {
          onSignInSuccess("Maya", "maya.wellness@gmail.com", selectedGoal)
        },
        testTag = "google_signin_button",
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 440.dp)
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Button 2: Continue with Apple
      AuthActionButton(
        icon = { AppleLogoIcon(size = 22.dp, tint = Color.Black) },
        label = "Continue with Apple",
        backgroundColor = Color.White,
        textColor = Color(0xFF1E1E1E),
        onClick = {
          onSignInSuccess("Maya", "maya@icloud.com", selectedGoal)
        },
        testTag = "apple_signin_button",
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 440.dp)
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Button 3: Continue with Email (Deep Plum)
      AuthActionButton(
        icon = null,
        label = "Continue with Email",
        backgroundColor = Color(0xFF4A3440),
        textColor = Color.White,
        onClick = {
          isSignUpMode = true
          showEmailDialog = true
        },
        testTag = "email_signin_button",
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 440.dp)
      )

      Spacer(modifier = Modifier.height(28.dp))

      // OR Divider
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 440.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        HorizontalDivider(
          modifier = Modifier.weight(1f),
          color = Color(0x33000000),
          thickness = 1.dp
        )
        Text(
          text = "OR",
          fontSize = 13.sp,
          fontWeight = FontWeight.Medium,
          color = Color(0xFF7A6E75),
          modifier = Modifier.padding(horizontal = 16.dp)
        )
        HorizontalDivider(
          modifier = Modifier.weight(1f),
          color = Color(0x33000000),
          thickness = 1.dp
        )
      }

      Spacer(modifier = Modifier.height(22.dp))

      // Privacy footnote
      Text(
        text = "Your data is private and secure",
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF382D33),
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(18.dp))

      // Already have an account? Sign In
      Row(
        modifier = Modifier
          .clickable {
            isSignUpMode = false
            showEmailDialog = true
          }
          .padding(8.dp)
          .testTag("already_have_account_button"),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Already have an account? ",
          fontSize = 15.sp,
          color = Color(0xFF382D33)
        )
        Text(
          text = "Sign In",
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = MauvePlumDark
        )
      }

      Spacer(modifier = Modifier.height(24.dp))
    }
  }

  // Interactive Email Sign In / Sign Up Dialog
  if (showEmailDialog) {
    EmailAuthDialog(
      isSignUp = isSignUpMode,
      onDismiss = { showEmailDialog = false },
      onSubmit = { name, email ->
        showEmailDialog = false
        onSignInSuccess(name.ifEmpty { "Maya" }, email, selectedGoal)
      }
    )
  }
}

@Composable
fun GoalFeatureCard(
  goal: HealthGoal,
  isSelected: Boolean,
  onClick: () -> Unit,
  iconContent: @Composable () -> Unit,
  modifier: Modifier = Modifier
) {
  val shape = RoundedCornerShape(20.dp)
  val interactionSource = remember { MutableInteractionSource() }

  Box(
    modifier = modifier
      .shadow(
        elevation = if (isSelected) 8.dp else 4.dp,
        shape = shape,
        ambientColor = Color(0x14402D34),
        spotColor = Color(0x14402D34)
      )
      .clip(shape)
      .background(
        if (isSelected) Color(0xEEFFFFFF) else Color(0x99FFFFFF)
      )
      .border(
        BorderStroke(
          width = if (isSelected) 2.dp else 1.2.dp,
          color = if (isSelected) MauvePlum.copy(alpha = 0.4f) else Color(0xB3FFFFFF)
        ),
        shape = shape
      )
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
      )
      .padding(vertical = 18.dp, horizontal = 4.dp),
    contentAlignment = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Box(
        modifier = Modifier
          .size(46.dp)
          .padding(bottom = 6.dp),
        contentAlignment = Alignment.Center
      ) {
        iconContent()
      }

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = goal.title,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextDark,
        textAlign = TextAlign.Center
      )
      Text(
        text = goal.subtitle,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = TextDark,
        textAlign = TextAlign.Center
      )
    }
  }
}

@Composable
fun AuthActionButton(
  icon: (@Composable () -> Unit)?,
  label: String,
  backgroundColor: Color,
  textColor: Color,
  onClick: () -> Unit,
  testTag: String,
  modifier: Modifier = Modifier
) {
  Surface(
    onClick = onClick,
    modifier = modifier
      .height(58.dp)
      .shadow(
        elevation = 4.dp,
        shape = RoundedCornerShape(18.dp),
        ambientColor = Color(0x1A000000),
        spotColor = Color(0x1A000000)
      )
      .testTag(testTag),
    shape = RoundedCornerShape(18.dp),
    color = backgroundColor
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      if (icon != null) {
        Box(
          modifier = Modifier.size(24.dp),
          contentAlignment = Alignment.Center
        ) {
          icon()
        }
        Spacer(modifier = Modifier.width(14.dp))
      }

      Text(
        text = label,
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        color = textColor,
        textAlign = TextAlign.Center
      )
    }
  }
}

@Composable
private fun EmailAuthDialog(
  isSignUp: Boolean,
  onDismiss: () -> Unit,
  onSubmit: (name: String, email: String) -> Unit
) {
  var name by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }

  Dialog(onDismissRequest = onDismiss) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .testTag("email_auth_dialog"),
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(24.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = if (isSignUp) "Create Account" else "Welcome Back",
            fontSize = 22.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = TextDark
          )

          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = TextMuted)
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isSignUp) {
          OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = MauvePlum) },
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
          )
          Spacer(modifier = Modifier.height(12.dp))
        }

        OutlinedTextField(
          value = email,
          onValueChange = { email = it },
          label = { Text("Email Address") },
          leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = MauvePlum) },
          shape = RoundedCornerShape(14.dp),
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
          value = password,
          onValueChange = { password = it },
          label = { Text("Password") },
          leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = MauvePlum) },
          visualTransformation = PasswordVisualTransformation(),
          shape = RoundedCornerShape(14.dp),
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
          onClick = {
            onSubmit(name, email.ifEmpty { "user@example.com" })
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .testTag("submit_auth_btn"),
          shape = RoundedCornerShape(14.dp),
          colors = ButtonDefaults.buttonColors(containerColor = MauvePlumDark)
        ) {
          Text(
            text = if (isSignUp) "Get Started" else "Sign In",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        }
      }
    }
  }
}

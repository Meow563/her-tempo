package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.ui.theme.MauvePlum
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted

@Composable
fun CommunityGatewayScreen(
  onBack: () -> Unit = {},
  onOpenCreatePost: () -> Unit = {},
  onOpenPostDetail: () -> Unit = {},
  onCreateAccountSuccess: (name: String, email: String) -> Unit = { _, _ -> },
  onSignInSuccess: (name: String, email: String) -> Unit = { _, _ -> },
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()
  var showAuthDialog by remember { mutableStateOf(false) }
  var isSignUpMode by remember { mutableStateOf(true) }

  var userNameInput by remember { mutableStateOf("") }
  var userEmailInput by remember { mutableStateOf("") }
  var userPasswordInput by remember { mutableStateOf("") }

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
      // Top artwork container with soft curved corners
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(440.dp)
          .padding(top = 12.dp, start = 12.dp, end = 12.dp)
          .clip(RoundedCornerShape(32.dp))
          .background(Color(0xFFFAF7F2))
      ) {
        Image(
          painter = painterResource(id = R.drawable.community_embrace_art_1787988487172),
          contentDescription = "Join our community artwork",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop,
          alignment = Alignment.Center
        )

        // Soft gradient fade at the bottom of the art image
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Transparent,
                  Color.Transparent,
                  Color(0x20FCFAF7),
                  Color(0x90FCFAF7),
                  Color(0xFFFCFAF7)
                ),
                startY = 240f,
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
            .testTag("community_back_button"),
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

      Spacer(modifier = Modifier.height(20.dp))

      // Text and CTA Body Container
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 440.dp)
          .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Headline: "Join our\ncommunity."
        Text(
          text = "Join our\ncommunity.",
          fontSize = 40.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF161014),
          textAlign = TextAlign.Center,
          lineHeight = 48.sp,
          letterSpacing = (-0.5).sp,
          modifier = Modifier.testTag("community_headline")
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Subtitle: "Discover a supportive space\nfor your wellness journey."
        Text(
          text = "Discover a supportive space\nfor your wellness journey.",
          fontSize = 17.sp,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF3C3037),
          textAlign = TextAlign.Center,
          lineHeight = 24.sp,
          modifier = Modifier.testTag("community_subtitle")
        )

        Spacer(modifier = Modifier.height(38.dp))

        // Primary Action: "Create Account" Pill Button with soft glow
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
            onClick = {
              isSignUpMode = true
              showAuthDialog = true
            },
            modifier = Modifier
              .fillMaxWidth()
              .height(56.dp)
              .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0x335A394B),
                spotColor = Color(0x335A394B)
              )
              .testTag("create_account_button"),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF5A394B)
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
          ) {
            Text(
              text = "Create Account",
              fontSize = 18.sp,
              fontWeight = FontWeight.Medium,
              color = Color.White
            )
          }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Secondary Action: "Sign In"
        Text(
          text = "Sign In",
          fontSize = 18.sp,
          fontWeight = FontWeight.Medium,
          color = Color(0xFF2C2228),
          modifier = Modifier
            .clickable {
              isSignUpMode = false
              showAuthDialog = true
            }
            .padding(horizontal = 24.dp, vertical = 6.dp)
            .testTag("sign_in_button")
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Tertiary Action: "Write a New Post"
        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          TextButton(
            onClick = onOpenCreatePost,
            modifier = Modifier.testTag("open_create_post_button")
          ) {
            Text(
              text = "Create a New Post",
              fontSize = 15.sp,
              fontWeight = FontWeight.SemiBold,
              color = MauvePlum
            )
          }

          Text(
            text = "•",
            color = TextMuted,
            fontSize = 14.sp
          )

          TextButton(
            onClick = onOpenPostDetail,
            modifier = Modifier.testTag("open_featured_post_button")
          ) {
            Text(
              text = "View Discussions",
              fontSize = 15.sp,
              fontWeight = FontWeight.SemiBold,
              color = MauvePlum
            )
          }
        }
      }
    }

    // Modal Dialog for Sign In / Create Account
    if (showAuthDialog) {
      Dialog(onDismissRequest = { showAuthDialog = false }) {
        Card(
          shape = RoundedCornerShape(28.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0xFFFCFAF7)),
          elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
          modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .testTag("auth_dialog")
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = if (isSignUpMode) "Create Account" else "Welcome Back",
                fontSize = 22.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E171B)
              )
              IconButton(
                onClick = { showAuthDialog = false },
                modifier = Modifier.size(32.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Close,
                  contentDescription = "Close",
                  tint = TextMuted
                )
              }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Quick Social Sign-In Buttons
            Button(
              onClick = {
                showAuthDialog = false
                if (isSignUpMode) {
                  onCreateAccountSuccess("Elena Rose", "elena@wellness.org")
                } else {
                  onSignInSuccess("Elena Rose", "elena@wellness.org")
                }
              },
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("google_auth_btn"),
              shape = RoundedCornerShape(24.dp),
              colors = ButtonDefaults.buttonColors(containerColor = Color.White),
              elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                GoogleLogoIcon(size = 18.dp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                  text = if (isSignUpMode) "Sign up with Google" else "Sign in with Google",
                  color = TextDark,
                  fontWeight = FontWeight.SemiBold,
                  fontSize = 14.sp
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
              onClick = {
                showAuthDialog = false
                if (isSignUpMode) {
                  onCreateAccountSuccess("Elena Rose", "elena@icloud.com")
                } else {
                  onSignInSuccess("Elena Rose", "elena@icloud.com")
                }
              },
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("apple_auth_btn"),
              shape = RoundedCornerShape(24.dp),
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E171B)),
              elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                AppleLogoIcon(size = 18.dp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                  text = if (isSignUpMode) "Sign up with Apple" else "Sign in with Apple",
                  color = Color.White,
                  fontWeight = FontWeight.SemiBold,
                  fontSize = 14.sp
                )
              }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically
            ) {
              HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE2DAD5))
              Text(
                text = " or with email ",
                fontSize = 12.sp,
                color = TextMuted,
                modifier = Modifier.padding(horizontal = 8.dp)
              )
              HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE2DAD5))
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isSignUpMode) {
              OutlinedTextField(
                value = userNameInput,
                onValueChange = { userNameInput = it },
                label = { Text("Your Name") },
                leadingIcon = {
                  Icon(Icons.Default.Person, contentDescription = null, tint = MauvePlum)
                },
                modifier = Modifier
                  .fillMaxWidth()
                  .testTag("input_auth_name"),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                  focusedBorderColor = MauvePlum,
                  unfocusedBorderColor = Color(0xFFE2DAD5)
                ),
                singleLine = true
              )
              Spacer(modifier = Modifier.height(10.dp))
            }

            OutlinedTextField(
              value = userEmailInput,
              onValueChange = { userEmailInput = it },
              label = { Text("Email Address") },
              leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = null, tint = MauvePlum)
              },
              modifier = Modifier
                .fillMaxWidth()
                .testTag("input_auth_email"),
              shape = RoundedCornerShape(16.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MauvePlum,
                unfocusedBorderColor = Color(0xFFE2DAD5)
              ),
              singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
              value = userPasswordInput,
              onValueChange = { userPasswordInput = it },
              label = { Text("Password") },
              visualTransformation = PasswordVisualTransformation(),
              leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null, tint = MauvePlum)
              },
              modifier = Modifier
                .fillMaxWidth()
                .testTag("input_auth_password"),
              shape = RoundedCornerShape(16.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MauvePlum,
                unfocusedBorderColor = Color(0xFFE2DAD5)
              ),
              singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
              onClick = {
                showAuthDialog = false
                val finalName = if (userNameInput.isNotBlank()) userNameInput else "Elena Rose"
                val finalEmail = if (userEmailInput.isNotBlank()) userEmailInput else "elena@wellness.org"
                if (isSignUpMode) {
                  onCreateAccountSuccess(finalName, finalEmail)
                } else {
                  onSignInSuccess(finalName, finalEmail)
                }
              },
              modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("submit_auth_form_btn"),
              shape = RoundedCornerShape(25.dp),
              colors = ButtonDefaults.buttonColors(containerColor = MauvePlum)
            ) {
              Text(
                text = if (isSignUpMode) "Create Account" else "Sign In",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
              )
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
              onClick = { isSignUpMode = !isSignUpMode }
            ) {
              Text(
                text = if (isSignUpMode) "Already have an account? Sign In" else "Don't have an account? Create one",
                color = MauvePlum,
                fontSize = 13.sp
              )
            }
          }
        }
      }
    }
  }
}

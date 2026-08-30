package com.example.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.BottomNavBar
import com.example.ui.viewmodel.NavTab

@Composable
fun PremiumSubscriptionScreen(
  onBack: () -> Unit = {},
  onSubscribe: () -> Unit = {},
  activeTab: NavTab = NavTab.HOME,
  onTabSelected: (NavTab) -> Unit = {},
  onAddClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()
  var isSubscribed by remember { mutableStateOf(false) }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFAF7F2))
  ) {
    // Scrollable container
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 90.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Top Art Card framed in gold exactly like reference
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .padding(top = 16.dp)
          .height(390.dp)
          .shadow(
            elevation = 6.dp,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 4.dp, bottomEnd = 4.dp),
            ambientColor = Color(0x2030232A),
            spotColor = Color(0x2030232A)
          )
          .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 4.dp, bottomEnd = 4.dp))
          .background(Color(0xFFFDFBF7))
      ) {
        // High quality fluid artwork with gold ribbons & reaching silhouettes
        Image(
          painter = painterResource(id = R.drawable.premium_hero_artwork_1787938715259),
          contentDescription = "Unlock Your Full Potential Art",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop,
          alignment = Alignment.Center
        )

        // Top subtle golden border
        Box(
          modifier = Modifier
            .align(Alignment.TopCenter)
            .fillMaxWidth()
            .height(2.5.dp)
            .background(
              Brush.horizontalGradient(
                listOf(
                  Color(0xFFD4AF37),
                  Color(0xFFF5E4B5),
                  Color(0xFFD4AF37),
                  Color(0xFFAA7C11),
                  Color(0xFFD4AF37)
                )
              )
            )
        )

        // Bottom crisp golden border divider exactly like screenshot
        Box(
          modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .height(4.dp)
            .background(
              Brush.horizontalGradient(
                listOf(
                  Color(0xFFD4AF37),
                  Color(0xFFF5E4B5),
                  Color(0xFFD4AF37),
                  Color(0xFFAA7C11),
                  Color(0xFFD4AF37)
                )
              )
            )
        )

        // Circular Back Button pinned top left with soft shadow
        Surface(
          onClick = onBack,
          modifier = Modifier
            .statusBarsPadding()
            .padding(start = 16.dp, top = 12.dp)
            .size(42.dp)
            .testTag("premium_back_button"),
          shape = CircleShape,
          color = Color.White.copy(alpha = 0.92f),
          shadowElevation = 4.dp
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

      Spacer(modifier = Modifier.height(26.dp))

      // Content section
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start
      ) {
        // Headline: "Unlock Your Full Potential"
        Text(
          text = "Unlock Your Full Potential",
          fontSize = 32.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF191316),
          letterSpacing = (-0.5).sp,
          modifier = Modifier.testTag("premium_headline_text")
        )

        Spacer(modifier = Modifier.height(26.dp))

        // Feature 1: Advanced AI Insights
        GoldCheckFeatureRow(
          text = "Advanced AI Insights",
          testTag = "feature_ai_insights"
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Feature 2: Unlimited Journaling
        GoldCheckFeatureRow(
          text = "Unlimited Journaling",
          testTag = "feature_unlimited_journaling"
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Feature 3: Exclusive Content
        GoldCheckFeatureRow(
          text = "Exclusive Content",
          testTag = "feature_exclusive_content"
        )

        Spacer(modifier = Modifier.height(34.dp))

        // Warm Radiant Golden Glow behind primary button exactly matching reference
        Box(
          modifier = Modifier
            .fillMaxWidth(),
          contentAlignment = Alignment.Center
        ) {
          // Ambient warm golden glow effect
          Box(
            modifier = Modifier
              .fillMaxWidth(0.92f)
              .height(54.dp)
              .offset(y = 2.dp)
              .background(
                Brush.radialGradient(
                  colors = listOf(
                    Color(0xFFE8C872).copy(alpha = 0.55f),
                    Color(0xFFD4AF37).copy(alpha = 0.35f),
                    Color.Transparent
                  ),
                  radius = 350f
                ),
                shape = RoundedCornerShape(30.dp)
              )
              .blur(16.dp)
          )

          Button(
            onClick = {
              isSubscribed = true
              onSubscribe()
            },
            modifier = Modifier
              .fillMaxWidth()
              .height(56.dp)
              .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0x405A394B),
                spotColor = Color(0x405A394B)
              )
              .testTag("try_free_subscribe_button"),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF5A394B)
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
          ) {
            Text(
              text = if (isSubscribed) "Subscription Active ✓" else "Try Free & Subscribe",
              fontSize = 18.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color.White
            )
          }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Pricing details
        Column(
          modifier = Modifier.fillMaxWidth(),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "Monthly Plan: $9.99/mo",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1E171B),
            modifier = Modifier.testTag("pricing_monthly_text")
          )

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = "Annual Plan: $89.99/yr (Save 25%)",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF1E171B),
            modifier = Modifier.testTag("pricing_annual_text")
          )
        }
      }
    }

    // Bottom Navigation Bar
    BottomNavBar(
      activeTab = activeTab,
      onTabSelected = onTabSelected,
      onAddClick = onAddClick,
      modifier = Modifier.align(Alignment.BottomCenter)
    )
  }
}

@Composable
private fun GoldCheckFeatureRow(
  text: String,
  testTag: String
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .testTag(testTag),
    verticalAlignment = Alignment.CenterVertically
  ) {
    // Pure Vector Drawn Gold Metallic Gradient Badge with checkmark
    Box(
      modifier = Modifier
        .size(28.dp)
        .shadow(
          elevation = 2.dp,
          shape = CircleShape,
          ambientColor = Color(0x33B8860B),
          spotColor = Color(0x33B8860B)
        )
        .background(
          brush = Brush.linearGradient(
            colors = listOf(
              Color(0xFFF3D58C),
              Color(0xFFD4AF37),
              Color(0xFFC59B27),
              Color(0xFFE5C158)
            ),
            start = Offset(0f, 0f),
            end = Offset(70f, 70f)
          ),
          shape = CircleShape
        )
        .border(1.dp, Color(0xFFFFF6DF), CircleShape),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = Icons.Default.Check,
        contentDescription = "Included",
        tint = Color.White,
        modifier = Modifier.size(17.dp)
      )
    }

    Spacer(modifier = Modifier.width(16.dp))

    Text(
      text = text,
      fontSize = 19.sp,
      fontWeight = FontWeight.Normal,
      color = Color(0xFF1F181D)
    )
  }
}

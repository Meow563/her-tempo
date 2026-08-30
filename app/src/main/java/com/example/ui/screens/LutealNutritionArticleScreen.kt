package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R

@Composable
fun LutealNutritionArticleScreen(
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFCFAF7))
  ) {
    // Scrollable Article Content
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 48.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Top Hero Image with Soft Gradient Fade
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(380.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.luteal_nutrition_tea_hero_1787937745306),
          contentDescription = "Optimizing Nutrition for Your Luteal Phase Hero",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop,
          alignment = Alignment.Center
        )

        // Soft gradient overlay at bottom
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Transparent,
                  Color.Transparent,
                  Color(0x10FCFAF7),
                  Color(0x60FCFAF7),
                  Color(0xFFFCFAF7)
                ),
                startY = 180f,
                endY = 1100f
              )
            )
        )

        // Floating Circular Back Button
        Surface(
          onClick = onBack,
          modifier = Modifier
            .statusBarsPadding()
            .padding(start = 20.dp, top = 16.dp)
            .size(42.dp)
            .testTag("article_back_button"),
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

      // Article Body Container
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 520.dp)
          .padding(horizontal = 24.dp)
      ) {
        // Article Title
        Text(
          text = "Optimizing Nutrition\nfor Your Luteal Phase",
          fontSize = 32.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF1B1418),
          lineHeight = 40.sp,
          letterSpacing = (-0.5).sp,
          modifier = Modifier.testTag("article_title")
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Subtle Divider Line
        HorizontalDivider(
          color = Color(0xFFEFE8E2),
          thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Body Text
        Text(
          text = "The luteal phase, following ovulation and preceding menstruation, requires specific nutritional support. Focus on complex carbohydrates, magnesium-rich foods like leafy greens and nuts, and omega-3 fatty acids to help balance mood swings, stabilize blood sugar, and alleviate premenstrual symptoms naturally.",
          fontSize = 16.sp,
          lineHeight = 26.sp,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF2E2429),
          modifier = Modifier.testTag("article_body_text")
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = "During this time, progesterone peaks and metabolism slightly increases. Prioritize warm, grounding foods, herbal peppermint and chamomile teas, and foods rich in vitamin B6 and zinc to nourish your body and maintain sustained vital energy.",
          fontSize = 15.sp,
          lineHeight = 24.sp,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF4A3B43)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Related Articles Header
        Text(
          text = "Related Articles",
          fontSize = 19.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF1E171B),
          modifier = Modifier.testTag("related_articles_title")
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Horizontal Row of Related Article Cards
        val horizontalScrollState = rememberScrollState()
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(horizontalScrollState),
          horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          // Card 1: 5 Luteal Phase Recipes
          RelatedArticleCard(
            title = "5 Luteal Phase\nRecipes",
            imageRes = R.drawable.luteal_recipes_thumb_1787937767417,
            testTag = "related_card_recipes"
          )

          // Card 2: Understanding Progesterone
          RelatedArticleCard(
            title = "Understanding\nProgesterone",
            imageRes = R.drawable.progesterone_wellness_thumb_1787937824305,
            testTag = "related_card_progesterone"
          )

          // Card 3: Gentle Yoga for PMS
          RelatedArticleCard(
            title = "Gentle Yoga\nfor PMS",
            imageRes = R.drawable.yoga_pms_thumb_1787937788166,
            testTag = "related_card_yoga"
          )
        }
      }
    }
  }
}

@Composable
private fun RelatedArticleCard(
  title: String,
  imageRes: Int,
  testTag: String,
  onClick: () -> Unit = {}
) {
  Surface(
    modifier = Modifier
      .width(140.dp)
      .height(170.dp)
      .shadow(
        elevation = 3.dp,
        shape = RoundedCornerShape(20.dp),
        ambientColor = Color(0x1830232A),
        spotColor = Color(0x1830232A)
      )
      .clickable { onClick() }
      .testTag(testTag),
    shape = RoundedCornerShape(20.dp),
    color = Color(0xFFF7F2EC)
  ) {
    Column(
      modifier = Modifier.fillMaxSize()
    ) {
      // Title top section
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 12.dp, vertical = 10.dp)
      ) {
        Text(
          text = title,
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold,
          lineHeight = 17.sp,
          color = Color(0xFF1E171B),
          maxLines = 2
        )
      }

      Spacer(modifier = Modifier.weight(1f))

      // Thumbnail Image at bottom
      Image(
        painter = painterResource(id = imageRes),
        contentDescription = title,
        modifier = Modifier
          .fillMaxWidth()
          .height(105.dp)
          .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
        contentScale = ContentScale.Crop
      )
    }
  }
}

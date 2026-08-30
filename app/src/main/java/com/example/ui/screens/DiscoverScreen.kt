package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Bedtime
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.SelfImprovement
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R

data class FeaturedArticleItem(
  val id: String,
  val title: String,
  val imageRes: Int,
  val category: String
)

data class DiscoverCategoryItem(
  val id: String,
  val title: String,
  val icon: ImageVector,
  val badgeBgColor: Color,
  val iconColor: Color,
  val subtitle: String = ""
)

@Composable
fun DiscoverScreen(
  onOpenArticle: (String) -> Unit = {},
  onSelectCategory: (String) -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  val featuredArticles = listOf(
    FeaturedArticleItem(
      id = "holistic_support",
      title = "Holistic Cycle\nSupport",
      imageRes = R.drawable.img_discover_holistic_1787990161206,
      category = "Wellness"
    ),
    FeaturedArticleItem(
      id = "nourishing_body",
      title = "Nourishing\nYour Body",
      imageRes = R.drawable.img_discover_nourish_1787990179951,
      category = "Nutrition"
    ),
    FeaturedArticleItem(
      id = "healthy_recipes",
      title = "Healthy Recipes\n& Tonics",
      imageRes = R.drawable.img_discover_recipes_1787990197680,
      category = "Diet"
    )
  )

  val categories = listOf(
    DiscoverCategoryItem(
      id = "cycle_health",
      title = "Cycle Health",
      icon = Icons.Outlined.Spa,
      badgeBgColor = Color(0xFFFBE1E5),
      iconColor = Color(0xFFC0596B),
      subtitle = "Hormones, phases & physiology"
    ),
    DiscoverCategoryItem(
      id = "nutrition",
      title = "Nutrition",
      icon = Icons.Outlined.Eco,
      badgeBgColor = Color(0xFFE2EDE5),
      iconColor = Color(0xFF5A8466),
      subtitle = "Phase-based seed syncing & recipes"
    ),
    DiscoverCategoryItem(
      id = "mental_wellbeing",
      title = "Mental Well-being",
      icon = Icons.Outlined.SelfImprovement,
      badgeBgColor = Color(0xFFFBEEDB),
      iconColor = Color(0xFFB57E48),
      subtitle = "Mindfulness, stress & emotional balance"
    ),
    DiscoverCategoryItem(
      id = "sleep_rest",
      title = "Sleep & Rest",
      icon = Icons.Outlined.Bedtime,
      badgeBgColor = Color(0xFFE4EAF0),
      iconColor = Color(0xFF4C667E),
      subtitle = "Circadian rhythm & bedtime rituals"
    )
  )

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFCFAF7))
  ) {
    // Top background wave artwork
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(290.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.personalized_waves_bg_1787988997650),
        contentDescription = "Wave Header Art",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
      )

      // Soft fading gradient overlay at bottom of header
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(100.dp)
          .align(Alignment.BottomCenter)
          .background(
            Brush.verticalGradient(
              colors = listOf(
                Color.Transparent,
                Color(0xFFFCFAF7).copy(alpha = 0.85f),
                Color(0xFFFCFAF7)
              )
            )
          )
      )
    }

    // Scrollable content
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 32.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Header Section
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .statusBarsPadding()
          .padding(top = 16.dp, start = 20.dp, end = 20.dp)
      ) {
        Text(
          text = "Discover",
          fontSize = 38.sp,
          fontWeight = FontWeight.Bold,
          fontFamily = FontFamily.SansSerif,
          color = Color(0xFF140D12),
          letterSpacing = (-0.5).sp,
          modifier = Modifier.testTag("discover_screen_title")
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
          text = "Featured Articles",
          fontSize = 21.sp,
          fontWeight = FontWeight.Bold,
          fontFamily = FontFamily.SansSerif,
          color = Color(0xFF1A1217),
          modifier = Modifier.testTag("discover_featured_title")
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Horizontal Carousel of Featured Articles
      LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("discover_featured_row")
      ) {
        items(featuredArticles, key = { it.id }) { article ->
          FeaturedArticleCard(
            article = article,
            onClick = { onOpenArticle(article.id) }
          )
        }
      }

      Spacer(modifier = Modifier.height(30.dp))

      // Category List Section
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 500.dp)
          .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        categories.forEach { category ->
          DiscoverCategoryCard(
            category = category,
            onClick = { onSelectCategory(category.id) }
          )
        }
      }
    }
  }
}

@Composable
private fun FeaturedArticleCard(
  article: FeaturedArticleItem,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .width(190.dp)
      .height(245.dp)
      .shadow(
        elevation = 6.dp,
        shape = RoundedCornerShape(26.dp),
        ambientColor = Color(0x1F2C1A24),
        spotColor = Color(0x1F2C1A24)
      )
      .clickable(onClick = onClick)
      .testTag("featured_article_${article.id}"),
    shape = RoundedCornerShape(26.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White)
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      Image(
        painter = painterResource(id = article.imageRes),
        contentDescription = article.title,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
      )

      // Dark gradient overlay for text readability
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              colors = listOf(
                Color.Transparent,
                Color.Black.copy(alpha = 0.15f),
                Color.Black.copy(alpha = 0.72f)
              ),
              startY = 120f
            )
          )
      )

      // Title Text overlaid on bottom
      Text(
        text = article.title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        lineHeight = 22.sp,
        modifier = Modifier
          .align(Alignment.BottomStart)
          .padding(start = 16.dp, end = 16.dp, bottom = 18.dp)
      )
    }
  }
}

@Composable
private fun DiscoverCategoryCard(
  category: DiscoverCategoryItem,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    onClick = onClick,
    modifier = modifier
      .fillMaxWidth()
      .height(78.dp)
      .shadow(
        elevation = 3.dp,
        shape = RoundedCornerShape(24.dp),
        ambientColor = Color(0x14201018),
        spotColor = Color(0x14201018)
      )
      .testTag("category_card_${category.id}"),
    shape = RoundedCornerShape(24.dp),
    color = Color.White
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Icon Circle Badge
        Box(
          modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(category.badgeBgColor),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = category.icon,
            contentDescription = category.title,
            tint = category.iconColor,
            modifier = Modifier.size(26.dp)
          )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Title
        Text(
          text = category.title,
          fontSize = 18.sp,
          fontWeight = FontWeight.SemiBold,
          color = Color(0xFF1E151A),
          letterSpacing = (-0.2).sp
        )
      }

      // Chevron Right
      Icon(
        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
        contentDescription = "Open ${category.title}",
        tint = Color(0xFFC5B7BE),
        modifier = Modifier.size(24.dp)
      )
    }
  }
}

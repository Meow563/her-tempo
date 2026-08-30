package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.BottomNavBar
import com.example.ui.viewmodel.NavTab
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class VideoCategory(
  val id: String,
  val title: String,
  val subtitle: String,
  val itemCountText: String,
  val imageRes: Int,
  val containerColor: Color,
  val isPremium: Boolean = true
)

data class ArticleSummary(
  val id: String,
  val title: String,
  val subtitle: String,
  val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveryVideoLibraryScreen(
  onBack: () -> Unit = {},
  onOpenArticle: (String) -> Unit = {},
  onOpenPremium: () -> Unit = {},
  activeTab: NavTab = NavTab.INSIGHTS,
  onTabSelected: (NavTab) -> Unit = {},
  onAddClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()
  val snackbarHostState = remember { SnackbarHostState() }
  val coroutineScope = rememberCoroutineScope()

  var isVideoPlaying by remember { mutableStateOf(false) }
  var showVideoPlayerSheet by remember { mutableStateOf(false) }
  var videoProgress by remember { mutableFloatStateOf(0.18f) }

  val categories = remember {
    listOf(
      VideoCategory(
        id = "pcos_nutrition",
        title = "Nutrition for PCOS",
        subtitle = "Nourish your body to manage symptoms",
        itemCountText = "8 videos & articles",
        imageRes = R.drawable.category_nutrition_pcos_1788023718517,
        containerColor = Color(0xFFD2938E),
        isPremium = true
      ),
      VideoCategory(
        id = "cycle_movement",
        title = "Cycle-Synced Movement",
        subtitle = "Workouts aligned with your energy levels",
        itemCountText = "12 videos",
        imageRes = R.drawable.category_yoga_movement_1788023732115,
        containerColor = Color(0xFF9CB8A5),
        isPremium = true
      ),
      VideoCategory(
        id = "mindfulness_stress",
        title = "Mindfulness & Stress",
        subtitle = "Techniques to cultivate your calm",
        itemCountText = "5 guided sessions",
        imageRes = R.drawable.category_mindfulness_breath_1788023746773,
        containerColor = Color(0xFF81A49E),
        isPremium = true
      )
    )
  }

  val latestArticles = remember {
    listOf(
      ArticleSummary(
        id = "luteal_understanding",
        title = "Understanding Your Luteal Phase",
        subtitle = "What to expect and how to support your body",
        imageRes = R.drawable.video_hero_balance_meditation_1788023702575
      ),
      ArticleSummary(
        id = "mood_recipes",
        title = "5 Mood-Boosting Recipes",
        subtitle = "Simple, delicious, and hormone-friendly",
        imageRes = R.drawable.article_mood_recipes_thumb_1788023760344
      )
    )
  }

  // Simulated video playback timer
  LaunchedEffect(isVideoPlaying) {
    while (isVideoPlaying) {
      delay(1000)
      if (videoProgress < 1f) {
        videoProgress += 0.015f
      } else {
        videoProgress = 0f
        isVideoPlaying = false
      }
    }
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFCFAF7))
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 90.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Top Status Bar and Navigation Header
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .statusBarsPadding()
          .padding(top = 10.dp, bottom = 12.dp, start = 16.dp, end = 16.dp)
      ) {
        // Back Button
        IconButton(
          onClick = onBack,
          modifier = Modifier
            .align(Alignment.CenterStart)
            .size(38.dp)
            .testTag("discovery_back_button")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color(0xFF1E141B),
            modifier = Modifier.size(22.dp)
          )
        }

        // Title: "Discovery & Video"
        Text(
          text = "Discovery & Video",
          fontSize = 20.sp,
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.SemiBold,
          color = Color(0xFF190F16),
          modifier = Modifier
            .align(Alignment.Center)
            .testTag("discovery_page_title")
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      // Hero Video Card Section
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 500.dp)
          .padding(horizontal = 18.dp)
      ) {
        // Video Thumbnail Container with Shadow & Play Button
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .shadow(
              elevation = 10.dp,
              shape = RoundedCornerShape(22.dp),
              ambientColor = Color(0x24261720),
              spotColor = Color(0x24261720)
            )
            .clip(RoundedCornerShape(22.dp))
            .clickable {
              showVideoPlayerSheet = true
              isVideoPlaying = true
            }
            .testTag("hero_video_thumbnail_card")
        ) {
          Image(
            painter = painterResource(id = R.drawable.video_hero_balance_meditation_1788023702575),
            contentDescription = "Guided Meditation for Hormonal Harmony",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
          )

          // Soft dark scrim for depth and play button contrast
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(Color.Black.copy(alpha = 0.12f))
          )

          // Center Circular Play Button
          Surface(
            modifier = Modifier
              .size(62.dp)
              .align(Alignment.Center)
              .shadow(8.dp, CircleShape),
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.88f)
          ) {
            Box(
              modifier = Modifier.fillMaxSize(),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play Guided Meditation Video",
                tint = Color(0xFF33202C),
                modifier = Modifier
                  .size(34.dp)
                  .padding(start = 2.dp)
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Hero Video Title
        Text(
          text = "Finding Balance: A Guided Meditation for Hormonal Harmony",
          fontSize = 19.5.sp,
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF140B11),
          lineHeight = 25.sp,
          letterSpacing = (-0.2).sp,
          modifier = Modifier.testTag("hero_video_title")
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Metadata Subtitle
        Text(
          text = "15 min • Cycle Phase: Luteal",
          fontSize = 14.sp,
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF6B5F66),
          modifier = Modifier.testTag("hero_video_metadata")
        )
      }

      Spacer(modifier = Modifier.height(28.dp))

      // Section 1: "Featured Categories"
      Column(
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          text = "Featured Categories",
          fontSize = 21.sp,
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF140A10),
          letterSpacing = (-0.3).sp,
          modifier = Modifier
            .padding(horizontal = 18.dp)
            .testTag("featured_categories_heading")
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Horizontal Scrollable Category Cards
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 18.dp),
          horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          categories.forEach { category ->
            FeaturedCategoryCard(
              category = category,
              onCardClick = {
                onOpenPremium()
              },
              modifier = Modifier.width(170.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(30.dp))

      // Section 2: "Latest Articles"
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 500.dp)
          .padding(horizontal = 18.dp)
      ) {
        Text(
          text = "Latest Articles",
          fontSize = 21.sp,
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF140A10),
          letterSpacing = (-0.3).sp,
          modifier = Modifier.testTag("latest_articles_heading")
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Vertical List of Articles
        Column(
          verticalArrangement = Arrangement.spacedBy(16.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          latestArticles.forEach { article ->
            ArticleItemRow(
              article = article,
              onClick = {
                onOpenArticle(article.id)
              }
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(28.dp))
    }

    // Modal Video Player Bottom Sheet
    if (showVideoPlayerSheet) {
      ModalBottomSheet(
        onDismissRequest = {
          showVideoPlayerSheet = false
          isVideoPlaying = false
        },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = Color(0xFF1A1218),
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          // Top bar with close icon
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Guided Video Session",
              fontSize = 17.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color.White
            )
            IconButton(
              onClick = {
                showVideoPlayerSheet = false
                isVideoPlaying = false
              }
            ) {
              Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close Player",
                tint = Color.White
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          // Video Screen Box
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(230.dp)
              .clip(RoundedCornerShape(16.dp))
              .background(Color.Black)
          ) {
            Image(
              painter = painterResource(id = R.drawable.video_hero_balance_meditation_1788023702575),
              contentDescription = "Active Playing Video",
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop
            )

            // Center Play / Pause toggle
            IconButton(
              onClick = { isVideoPlaying = !isVideoPlaying },
              modifier = Modifier
                .align(Alignment.Center)
                .size(60.dp)
                .background(Color.Black.copy(alpha = 0.5f), CircleShape)
            ) {
              Icon(
                imageVector = if (isVideoPlaying) Icons.Default.Pause else Icons.Filled.PlayArrow,
                contentDescription = if (isVideoPlaying) "Pause" else "Play",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Progress Bar
          LinearProgressIndicator(
            progress = { videoProgress },
            modifier = Modifier
              .fillMaxWidth()
              .height(5.dp)
              .clip(RoundedCornerShape(3.dp)),
            color = Color(0xFFD2938E),
            trackColor = Color(0x44FFFFFF)
          )

          Spacer(modifier = Modifier.height(8.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            val totalSeconds = 15 * 60
            val currentSeconds = (videoProgress * totalSeconds).toInt()
            val currentMins = currentSeconds / 60
            val currentSecs = currentSeconds % 60
            Text(
              text = String.format("%02d:%02d", currentMins, currentSecs),
              fontSize = 12.sp,
              color = Color(0xFFC7BAC2)
            )
            Text(
              text = "15:00",
              fontSize = 12.sp,
              color = Color(0xFFC7BAC2)
            )
          }

          Spacer(modifier = Modifier.height(16.dp))

          Text(
            text = "Finding Balance: A Guided Meditation for Hormonal Harmony",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
          )

          Spacer(modifier = Modifier.height(28.dp))
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

    // Snackbar Host
    SnackbarHost(
      hostState = snackbarHostState,
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .padding(bottom = 80.dp)
    )
  }
}

@Composable
private fun FeaturedCategoryCard(
  category: VideoCategory,
  onCardClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .shadow(
        elevation = 6.dp,
        shape = RoundedCornerShape(22.dp),
        ambientColor = Color(0x1F2A1821),
        spotColor = Color(0x1F2A1821)
      )
      .clip(RoundedCornerShape(22.dp))
      .clickable(onClick = onCardClick)
      .testTag("category_card_${category.id}"),
    shape = RoundedCornerShape(22.dp),
    color = category.containerColor
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      horizontalAlignment = Alignment.Start
    ) {
      // Top Row with Premium Badge
      if (category.isPremium) {
        Surface(
          shape = RoundedCornerShape(12.dp),
          color = Color(0x38FFFFFF),
          modifier = Modifier.height(24.dp)
        ) {
          Box(
            modifier = Modifier.padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "Premium",
              fontSize = 11.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color.White
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Illustration in circular / rounded frame
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(96.dp),
        contentAlignment = Alignment.Center
      ) {
        Image(
          painter = painterResource(id = category.imageRes),
          contentDescription = category.title,
          modifier = Modifier
            .size(90.dp)
            .clip(RoundedCornerShape(14.dp)),
          contentScale = ContentScale.Fit
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Category Title
      Text(
        text = category.title,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        color = Color.White,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        lineHeight = 18.sp
      )

      Spacer(modifier = Modifier.height(4.dp))

      // Subtitle
      Text(
        text = category.subtitle,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif,
        color = Color.White.copy(alpha = 0.9f),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        lineHeight = 15.sp
      )

      Spacer(modifier = Modifier.height(12.dp))

      // Bottom Pill with item count
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0x38FFFFFF),
        modifier = Modifier.height(26.dp)
      ) {
        Box(
          modifier = Modifier.padding(horizontal = 10.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = category.itemCountText,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
          )
        }
      }
    }
  }
}

@Composable
private fun ArticleItemRow(
  article: ArticleSummary,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .clickable(onClick = onClick)
      .padding(vertical = 4.dp)
      .testTag("article_row_${article.id}"),
    verticalAlignment = Alignment.CenterVertically
  ) {
    // Thumbnail image
    Image(
      painter = painterResource(id = article.imageRes),
      contentDescription = article.title,
      modifier = Modifier
        .size(62.dp)
        .shadow(4.dp, RoundedCornerShape(14.dp))
        .clip(RoundedCornerShape(14.dp)),
      contentScale = ContentScale.Crop
    )

    Spacer(modifier = Modifier.width(14.dp))

    // Text Content
    Column(
      modifier = Modifier.weight(1f)
    ) {
      Text(
        text = article.title,
        fontSize = 15.5.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        color = Color(0xFF190F16),
        lineHeight = 20.sp
      )

      Spacer(modifier = Modifier.height(3.dp))

      Text(
        text = article.subtitle,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif,
        color = Color(0xFF6B5F66),
        lineHeight = 17.sp
      )
    }
  }
}

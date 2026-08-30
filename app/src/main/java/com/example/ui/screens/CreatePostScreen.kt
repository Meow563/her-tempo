package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.BottomNavBar
import com.example.ui.viewmodel.NavTab
import kotlinx.coroutines.launch

val AVAILABLE_POST_TAGS = listOf(
  "#CycleTracking",
  "#HolisticHealth",
  "#WellnessTips",
  "#Fertility",
  "#Mindfulness",
  "#Nutrition",
  "#CommunitySupport",
  "#AskTheExperts"
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreatePostScreen(
  onBack: () -> Unit = {},
  onPostSuccess: (content: String, tags: List<String>) -> Unit = { _, _ -> },
  activeTab: NavTab = NavTab.ADD,
  onTabSelected: (NavTab) -> Unit = {},
  onAddClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()
  val snackbarHostState = remember { SnackbarHostState() }
  val coroutineScope = rememberCoroutineScope()

  var postContent by remember { mutableStateOf("") }
  val maxChars = 500

  val selectedTags = remember { mutableStateListOf<String>("#CycleTracking", "#HolisticHealth") }

  var isHeartLiked by remember { mutableStateOf(false) }
  var isBookmarked by remember { mutableStateOf(false) }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFBF8F4))
  ) {
    // Scrollable Screen Content
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 100.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Top Botanical Illustration & Header Section
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(260.dp)
      ) {
        // Botanical Artwork
        Image(
          painter = painterResource(id = R.drawable.botanical_create_post_header_1788021903770),
          contentDescription = "Botanical Floral Illustration",
          modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
          contentScale = ContentScale.Crop,
          alignment = Alignment.TopCenter
        )

        // Soft gradient overlay merging the bottom of the botanical banner
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .align(Alignment.BottomCenter)
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Transparent,
                  Color(0x99FBF8F4),
                  Color(0xFFFBF8F4)
                )
              )
            )
        )

        // Floating Back Button
        Surface(
          onClick = onBack,
          modifier = Modifier
            .statusBarsPadding()
            .padding(start = 16.dp, top = 12.dp)
            .size(38.dp)
            .testTag("create_post_back_button"),
          shape = CircleShape,
          color = Color.White.copy(alpha = 0.85f),
          shadowElevation = 2.dp
        ) {
          Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color(0xFF2B1D25),
              modifier = Modifier.size(18.dp)
            )
          }
        }

        // Headline: "Create a New Post"
        Text(
          text = "Create a New Post",
          fontSize = 32.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF140B10),
          letterSpacing = (-0.3).sp,
          textAlign = TextAlign.Center,
          modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 12.dp)
            .testTag("create_post_title")
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Main White Card
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 20.dp)
          .shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(26.dp),
            ambientColor = Color(0x20241620),
            spotColor = Color(0x20241620)
          )
          .border(
            width = 1.dp,
            color = Color(0xFFEFE8E3),
            shape = RoundedCornerShape(26.dp)
          )
          .testTag("create_post_card"),
        shape = RoundedCornerShape(26.dp),
        color = Color.White
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 22.dp)
        ) {
          // Card Title
          Text(
            text = "Share your thoughts, experiences, or questions...",
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF180F15),
            lineHeight = 27.sp,
            letterSpacing = (-0.2).sp,
            modifier = Modifier.testTag("create_post_prompt_title")
          )

          Spacer(modifier = Modifier.height(18.dp))

          // Text Area with Outlined Rounded Border
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(160.dp)
              .border(
                width = 1.dp,
                color = Color(0xFFE2DAD5),
                shape = RoundedCornerShape(14.dp)
              )
              .background(Color.White, shape = RoundedCornerShape(14.dp))
              .padding(horizontal = 16.dp, vertical = 14.dp)
              .testTag("create_post_text_container")
          ) {
            BasicTextField(
              value = postContent,
              onValueChange = {
                if (it.length <= maxChars) {
                  postContent = it
                }
              },
              modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 22.dp)
                .testTag("create_post_text_field"),
              textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF1E141B),
                lineHeight = 22.sp
              ),
              cursorBrush = SolidColor(Color(0xFF54384B)),
              decorationBox = { innerTextField ->
                if (postContent.isEmpty()) {
                  Text(
                    text = "What's on your mind?",
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF8E8388)
                  )
                }
                innerTextField()
              }
            )

            // Character Counter at Bottom-Right inside the box
            Text(
              text = "${postContent.length}/$maxChars",
              fontSize = 13.sp,
              fontFamily = FontFamily.SansSerif,
              color = Color(0xFF8E8388),
              modifier = Modifier
                .align(Alignment.BottomEnd)
                .testTag("create_post_char_count")
            )
          }

          Spacer(modifier = Modifier.height(20.dp))

          // "Add Tags" Heading
          Text(
            text = "Add Tags",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif,
            color = Color(0xFF1E141B),
            modifier = Modifier.testTag("create_post_add_tags_label")
          )

          Spacer(modifier = Modifier.height(12.dp))

          // Flow of Tag Chips matching the screenshot
          FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            AVAILABLE_POST_TAGS.forEach { tag ->
              val isSelected = selectedTags.contains(tag)
              val chipBgColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFFEBE0E6) else Color(0xFFF7F3F1),
                animationSpec = tween(150),
                label = "chipBg"
              )
              val chipBorderColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFF523446) else Color(0xFF4C3342),
                animationSpec = tween(150),
                label = "chipBorder"
              )
              val chipTextColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFF321A2A) else Color(0xFF22151E),
                animationSpec = tween(150),
                label = "chipText"
              )

              Surface(
                onClick = {
                  if (isSelected) {
                    selectedTags.remove(tag)
                  } else {
                    selectedTags.add(tag)
                  }
                },
                shape = RoundedCornerShape(18.dp),
                color = chipBgColor,
                border = androidx.compose.foundation.BorderStroke(1.2.dp, chipBorderColor),
                modifier = Modifier
                  .height(34.dp)
                  .testTag("tag_chip_${tag.removePrefix("#").lowercase()}")
              ) {
                Box(
                  modifier = Modifier
                    .padding(horizontal = 14.dp),
                  contentAlignment = Alignment.Center
                ) {
                  Text(
                    text = tag,
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif,
                    color = chipTextColor
                  )
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(24.dp))

          // Bottom Action Row inside the card: Heart, Chat, Share, Bookmark
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            // Heart Icon
            IconButton(
              onClick = { isHeartLiked = !isHeartLiked },
              modifier = Modifier.size(36.dp).testTag("action_heart_button")
            ) {
              Icon(
                imageVector = if (isHeartLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Like",
                tint = if (isHeartLiked) Color(0xFF8B2B4E) else Color(0xFF523948),
                modifier = Modifier.size(24.dp)
              )
            }

            // Speech Bubble Icon
            IconButton(
              onClick = {
                coroutineScope.launch {
                  snackbarHostState.showSnackbar("Comment threads will be available once posted.")
                }
              },
              modifier = Modifier.size(36.dp).testTag("action_comment_button")
            ) {
              Icon(
                imageVector = Icons.Outlined.ChatBubbleOutline,
                contentDescription = "Comment",
                tint = Color(0xFF523948),
                modifier = Modifier.size(23.dp)
              )
            }

            // Share Icon
            IconButton(
              onClick = {
                coroutineScope.launch {
                  snackbarHostState.showSnackbar("Share link prepared.")
                }
              },
              modifier = Modifier.size(36.dp).testTag("action_share_button")
            ) {
              Icon(
                imageVector = Icons.Outlined.IosShare,
                contentDescription = "Share",
                tint = Color(0xFF523948),
                modifier = Modifier.size(24.dp)
              )
            }

            // Bookmark Icon
            IconButton(
              onClick = { isBookmarked = !isBookmarked },
              modifier = Modifier.size(36.dp).testTag("action_bookmark_button")
            ) {
              Icon(
                imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                contentDescription = "Bookmark",
                tint = if (isBookmarked) Color(0xFF54384B) else Color(0xFF523948),
                modifier = Modifier.size(24.dp)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Primary Action "POST" Button
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 20.dp)
          .shadow(
            elevation = 12.dp,
            shape = RoundedCornerShape(28.dp),
            ambientColor = Color(0x60503244),
            spotColor = Color(0x60503244)
          )
      ) {
        Button(
          onClick = {
            val contentToPost = postContent.ifEmpty { "Shared my thoughts with the community." }
            onPostSuccess(contentToPost, selectedTags.toList())
            coroutineScope.launch {
              snackbarHostState.showSnackbar(
                message = "Post published to the community!",
                duration = SnackbarDuration.Short
              )
            }
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
              brush = Brush.horizontalGradient(
                colors = listOf(
                  Color(0xFF5A374E),
                  Color(0xFF4F2F43),
                  Color(0xFF45273A)
                )
              ),
              shape = RoundedCornerShape(28.dp)
            )
            .testTag("publish_post_button"),
          shape = RoundedCornerShape(28.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
          )
        ) {
          Text(
            text = "POST",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.2.sp,
            color = Color.White
          )
        }
      }

      Spacer(modifier = Modifier.height(20.dp))
    }

    // Snackbar Host
    SnackbarHost(
      hostState = snackbarHostState,
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .padding(bottom = 90.dp)
    )
  }
}

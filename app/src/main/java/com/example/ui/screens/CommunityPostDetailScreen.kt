package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.BottomNavBar
import com.example.ui.viewmodel.NavTab
import kotlinx.coroutines.launch

data class CommunityComment(
  val id: String,
  val authorName: String,
  val timeAgo: String,
  val content: String,
  val avatarRes: Int
)

@Composable
fun CommunityPostDetailScreen(
  onBack: () -> Unit = {},
  activeTab: NavTab = NavTab.HOME,
  onTabSelected: (NavTab) -> Unit = {},
  onAddClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()
  val snackbarHostState = remember { SnackbarHostState() }
  val coroutineScope = rememberCoroutineScope()

  var isLiked by remember { mutableStateOf(false) }
  var likeCount by remember { mutableIntStateOf(145) }

  var commentText by remember { mutableStateOf("") }

  val comments = remember {
    mutableStateListOf(
      CommunityComment(
        id = "c1",
        authorName = "Emily R.",
        timeAgo = "1 hour ago",
        content = "Love this! I've been trying to incorporate more stretching. Thanks for the reminder.",
        avatarRes = R.drawable.avatar_emily_r_1788022630057
      ),
      CommunityComment(
        id = "c2",
        authorName = "Maria G.",
        timeAgo = "45 mins ago",
        content = "Meditation is a game-changer for me. Do you have any app recommendations?",
        avatarRes = R.drawable.avatar_maria_g_1788022652361
      )
    )
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFBF8F4))
  ) {
    // Scrollable Screen Area
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 140.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Top Header Wave Illustration
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(240.dp)
      ) {
        // Fluid Wave Graphic
        Image(
          painter = painterResource(id = R.drawable.community_post_detail_waves_1788022588669),
          contentDescription = "Community Wave Header",
          modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
          contentScale = ContentScale.Crop,
          alignment = Alignment.TopCenter
        )

        // Gradient overlay
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .align(Alignment.BottomCenter)
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Transparent,
                  Color(0x88FBF8F4),
                  Color(0xFFFBF8F4)
                )
              )
            )
        )

        // Header Top Row: Back Button and Centered "Community"
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          IconButton(
            onClick = onBack,
            modifier = Modifier
              .size(40.dp)
              .testTag("community_post_back_button")
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color.White,
              modifier = Modifier.size(24.dp)
            )
          }

          Text(
            text = "Community",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier
              .weight(1f)
              .padding(end = 40.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Main Post White Card
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 18.dp)
          .shadow(
            elevation = 10.dp,
            shape = RoundedCornerShape(26.dp),
            ambientColor = Color(0x1F2B1B25),
            spotColor = Color(0x1F2B1B25)
          )
          .border(
            width = 1.dp,
            color = Color(0xFFEFE8E3),
            shape = RoundedCornerShape(26.dp)
          )
          .testTag("post_detail_main_card"),
        shape = RoundedCornerShape(26.dp),
        color = Color.White
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
          // Post Title
          Text(
            text = "Mindful Morning\nRoutine",
            fontSize = 32.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF140A10),
            lineHeight = 38.sp,
            letterSpacing = (-0.4).sp,
            modifier = Modifier.testTag("post_detail_title")
          )

          Spacer(modifier = Modifier.height(18.dp))

          // Author Row
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
          ) {
            Image(
              painter = painterResource(id = R.drawable.avatar_sarah_j_1788022610469),
              contentDescription = "Sarah J. Avatar",
              modifier = Modifier
                .size(44.dp)
                .clip(CircleShape),
              contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
              Text(
                text = "Sarah J.",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1B1218),
                fontFamily = FontFamily.SansSerif
              )
              Text(
                text = "2 hours ago",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF8E8388),
                fontFamily = FontFamily.SansSerif
              )
            }
          }

          Spacer(modifier = Modifier.height(18.dp))

          // Post Body Paragraph
          Text(
            text = "Sharing my updated morning ritual for hormone balance and energy. It includes gentle stretching, a warm lemon water, and a 10-minute meditation. What are your favorite ways to start the day mindfully? Let's inspire each other!",
            fontSize = 15.5.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF2B1D25),
            lineHeight = 23.sp,
            letterSpacing = (-0.1).sp,
            modifier = Modifier.testTag("post_detail_body")
          )

          Spacer(modifier = Modifier.height(22.dp))

          // Likes & Comments Stats
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            // Like Counter
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier
                .clickable {
                  if (isLiked) {
                    isLiked = false
                    likeCount--
                  } else {
                    isLiked = true
                    likeCount++
                  }
                }
                .padding(vertical = 4.dp)
                .testTag("post_detail_like_button")
            ) {
              Icon(
                imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                contentDescription = "Like",
                tint = if (isLiked) Color(0xFFB34A6A) else Color(0xFFD6788B),
                modifier = Modifier.size(20.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "$likeCount likes",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2C1D26)
              )
            }

            // Comment Counter
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(vertical = 4.dp)
            ) {
              Icon(
                imageVector = Icons.Outlined.ChatBubbleOutline,
                contentDescription = "Comments",
                tint = Color(0xFF6E5F67),
                modifier = Modifier.size(19.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "${21 + comments.size} comments",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2C1D26)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(28.dp))

      // Comments Section Title
      Text(
        text = "Comments",
        fontSize = 22.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF140A10),
        letterSpacing = (-0.3).sp,
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 22.dp)
          .testTag("comments_section_title")
      )

      Spacer(modifier = Modifier.height(14.dp))

      // List of Comments
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        comments.forEach { comment ->
          CommentCardItem(
            comment = comment,
            onReply = {
              commentText = "@${comment.authorName} "
            }
          )
        }
      }

      Spacer(modifier = Modifier.height(24.dp))
    }

    // Bottom Floating Frosted Glass Comment Input Bar
    Surface(
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .fillMaxWidth()
        .padding(bottom = 76.dp, start = 14.dp, end = 14.dp)
        .shadow(
          elevation = 12.dp,
          shape = RoundedCornerShape(32.dp),
          ambientColor = Color(0x2B243329),
          spotColor = Color(0x2B243329)
        )
        .testTag("floating_comment_input_container"),
      shape = RoundedCornerShape(32.dp),
      color = Color(0xCCE1EBE3)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Rounded Pill Input Field
        Box(
          modifier = Modifier
            .weight(1f)
            .height(44.dp)
            .background(Color.White.copy(alpha = 0.92f), shape = RoundedCornerShape(22.dp))
            .border(1.dp, Color(0xFFCCD8D0), shape = RoundedCornerShape(22.dp))
            .padding(horizontal = 16.dp),
          contentAlignment = Alignment.CenterStart
        ) {
          BasicTextField(
            value = commentText,
            onValueChange = { commentText = it },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("comment_text_input_field"),
            textStyle = TextStyle(
              fontSize = 15.sp,
              fontFamily = FontFamily.SansSerif,
              fontWeight = FontWeight.Normal,
              color = Color(0xFF1B1419)
            ),
            cursorBrush = SolidColor(Color(0xFF54384B)),
            decorationBox = { innerTextField ->
              if (commentText.isEmpty()) {
                Text(
                  text = "Add a comment...",
                  fontSize = 15.sp,
                  fontFamily = FontFamily.SansSerif,
                  fontWeight = FontWeight.Normal,
                  color = Color(0xFF7A8880)
                )
              }
              innerTextField()
            }
          )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Send Button
        IconButton(
          onClick = {
            if (commentText.isNotBlank()) {
              comments.add(
                CommunityComment(
                  id = "c_${System.currentTimeMillis()}",
                  authorName = "You",
                  timeAgo = "Just now",
                  content = commentText.trim(),
                  avatarRes = R.drawable.avatar_sarah_j_1788022610469
                )
              )
              commentText = ""
              coroutineScope.launch {
                snackbarHostState.showSnackbar("Comment added!", duration = SnackbarDuration.Short)
              }
            }
          },
          modifier = Modifier
            .size(42.dp)
            .testTag("send_comment_button")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = "Send",
            tint = if (commentText.isNotBlank()) Color(0xFF456B55) else Color(0xFF7E978A),
            modifier = Modifier.size(22.dp)
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

    // Snackbar Host
    SnackbarHost(
      hostState = snackbarHostState,
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .padding(bottom = 150.dp)
    )
  }
}

@Composable
private fun CommentCardItem(
  comment: CommunityComment,
  onReply: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .shadow(
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        ambientColor = Color(0x14281A23),
        spotColor = Color(0x14281A23)
      )
      .border(
        width = 1.dp,
        color = Color(0xFFF0EBE7),
        shape = RoundedCornerShape(20.dp)
      )
      .testTag("comment_card_${comment.id}"),
    shape = RoundedCornerShape(20.dp),
    color = Color.White
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
      // Header: Avatar, Name, TimeAgo
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Image(
          painter = painterResource(id = comment.avatarRes),
          contentDescription = "${comment.authorName} avatar",
          modifier = Modifier
            .size(38.dp)
            .clip(CircleShape),
          contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
          text = comment.authorName,
          fontSize = 15.5.sp,
          fontWeight = FontWeight.SemiBold,
          color = Color(0xFF1A1218),
          fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
          text = comment.timeAgo,
          fontSize = 12.5.sp,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF8E8388),
          fontFamily = FontFamily.SansSerif
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Comment Text
      Text(
        text = comment.content,
        fontSize = 14.5.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF2C1E26),
        lineHeight = 20.sp,
        fontFamily = FontFamily.SansSerif
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Reply Button
      Surface(
        onClick = onReply,
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFEDE6EB),
        modifier = Modifier
          .height(28.dp)
          .testTag("reply_button_${comment.id}")
      ) {
        Box(
          modifier = Modifier.padding(horizontal = 12.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "Reply",
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF4C3041),
            fontFamily = FontFamily.SansSerif
          )
        }
      }
    }
  }
}

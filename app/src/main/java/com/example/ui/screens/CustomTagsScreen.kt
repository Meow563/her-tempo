package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterDrama
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.MoodBad
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.outlined.Adjust
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Bedtime
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.FlashOn
import androidx.compose.material.icons.outlined.LocalFlorist
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ReportProblem
import androidx.compose.material.icons.outlined.SentimentSatisfiedAlt
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
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

enum class TagCategory {
  MOOD,
  SYMPTOM
}

data class TagItem(
  val id: String,
  val name: String,
  val category: TagCategory,
  val backgroundColor: Color,
  val icon: ImageVector,
  val isCustom: Boolean = false
)

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CustomTagsScreen(
  onBack: () -> Unit = {},
  onSaveSelection: (List<String>) -> Unit = {},
  activeTab: NavTab = NavTab.ADD,
  onTabSelected: (NavTab) -> Unit = {},
  onAddClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()
  val snackbarHostState = remember { SnackbarHostState() }
  val coroutineScope = rememberCoroutineScope()

  var searchQuery by remember { mutableStateOf("") }
  var showAddTagDialog by remember { mutableStateOf(false) }
  var newTagCategory by remember { mutableStateOf(TagCategory.MOOD) }
  var newTagName by remember { mutableStateOf("") }
  var selectedColorIndex by remember { mutableStateOf(0) }

  // Initial tag dataset exactly matching the screenshot
  val tags = remember {
    mutableStateListOf(
      // Moods
      TagItem("m_happy", "Happy", TagCategory.MOOD, Color(0xFF98B8A0), Icons.Outlined.SentimentSatisfiedAlt),
      TagItem("m_calm", "Calm", TagCategory.MOOD, Color(0xFF6F7996), Icons.Outlined.Bedtime),
      TagItem("m_anxiety", "Anxiety", TagCategory.MOOD, Color(0xFFC78B8E), Icons.Outlined.ReportProblem),
      TagItem("m_sleepy", "Sleepy", TagCategory.MOOD, Color(0xFF626288), Icons.Filled.Nightlight),
      TagItem("m_focused", "Focused", TagCategory.MOOD, Color(0xFF6A7993), Icons.Outlined.Adjust),
      TagItem("m_fatiguey", "Fatiguey", TagCategory.MOOD, Color(0xFF966F85), Icons.Outlined.AutoAwesome),
      TagItem("m_excited", "Excited", TagCategory.MOOD, Color(0xFFC88894), Icons.Outlined.FavoriteBorder),
      TagItem("m_energetic", "Energetic", TagCategory.MOOD, Color(0xFF92B495), Icons.Outlined.FlashOn),
      TagItem("m_sad", "Sad", TagCategory.MOOD, Color(0xFF72839D), Icons.Filled.SentimentDissatisfied),

      // Symptoms
      TagItem("s_bloating", "Bloating", TagCategory.SYMPTOM, Color(0xFF96B79F), Icons.Outlined.Spa),
      TagItem("s_cramps", "Cramps", TagCategory.SYMPTOM, Color(0xFF6D7A9B), Icons.Filled.Bolt),
      TagItem("s_headache", "Headache", TagCategory.SYMPTOM, Color(0xFF6E7E9A), Icons.Filled.Psychology),
      TagItem("s_acne", "Acne", TagCategory.SYMPTOM, Color(0xFFC78D91), Icons.Filled.WaterDrop),
      TagItem("s_fatigue", "Fatigue", TagCategory.SYMPTOM, Color(0xFF94B39B), Icons.Outlined.AutoAwesome),
      TagItem("s_breast_tenderness", "Breast Tenderness", TagCategory.SYMPTOM, Color(0xFF757D9B), Icons.Filled.Favorite),
      TagItem("s_cravings", "Cravings", TagCategory.SYMPTOM, Color(0xFFC78893), Icons.Outlined.FavoriteBorder)
    )
  }

  // Selected tags state
  val selectedTagIds = remember { mutableStateListOf<String>("m_happy", "s_bloating") }

  val tagColors = listOf(
    Color(0xFF98B8A0), // Sage Green
    Color(0xFF6F7996), // Slate Blue
    Color(0xFFC78B8E), // Dusty Rose
    Color(0xFF966F85), // Mauve Plum
    Color(0xFF626288), // Slate Purple
    Color(0xFF92B495)  // Light Sage
  )

  // Filtered lists based on search query
  val filteredMoods = tags.filter { it.category == TagCategory.MOOD && it.name.contains(searchQuery.trim(), ignoreCase = true) }
  val filteredSymptoms = tags.filter { it.category == TagCategory.SYMPTOM && it.name.contains(searchQuery.trim(), ignoreCase = true) }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 100.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Top Organic Fluid Wave Header with Search Bar
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(230.dp)
      ) {
        // Wave Background Image
        Image(
          painter = painterResource(id = R.drawable.custom_tags_header_waves_1788024306332),
          contentDescription = "Wave Header Banner",
          modifier = Modifier
            .fillMaxWidth()
            .height(230.dp),
          contentScale = ContentScale.Crop,
          alignment = Alignment.TopCenter
        )

        // Soft bottom gradient blending waves into crisp white canvas
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .align(Alignment.BottomCenter)
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Transparent,
                  Color.White.copy(alpha = 0.6f),
                  Color.White
                )
              )
            )
        )

        // Top Status Bar and Back Button Row
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          IconButton(
            onClick = onBack,
            modifier = Modifier
              .size(38.dp)
              .testTag("custom_tags_back_button")
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color(0xFF20131C),
              modifier = Modifier.size(22.dp)
            )
          }
        }

        // Frosted Search and Add Container overlay
        Surface(
          modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .widthIn(max = 480.dp)
            .padding(horizontal = 18.dp, vertical = 12.dp)
            .shadow(
              elevation = 8.dp,
              shape = RoundedCornerShape(26.dp),
              ambientColor = Color(0x221E121A),
              spotColor = Color(0x221E121A)
            )
            .testTag("tags_search_bar_container"),
          shape = RoundedCornerShape(26.dp),
          color = Color(0xD0D9E1E8)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 12.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            // Search Text Field Pill
            Row(
              modifier = Modifier
                .weight(1f)
                .height(44.dp)
                .background(
                  color = Color.White.copy(alpha = 0.82f),
                  shape = RoundedCornerShape(22.dp)
                )
                .border(
                  width = 1.dp,
                  color = Color.White.copy(alpha = 0.95f),
                  shape = RoundedCornerShape(22.dp)
                )
                .padding(horizontal = 14.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFF687482),
                modifier = Modifier.size(19.dp)
              )

              Spacer(modifier = Modifier.width(8.dp))

              BasicTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                  .weight(1f)
                  .testTag("custom_tags_search_input"),
                textStyle = TextStyle(
                  fontSize = 15.sp,
                  fontFamily = FontFamily.SansSerif,
                  fontWeight = FontWeight.Normal,
                  color = Color(0xFF1E141C)
                ),
                cursorBrush = SolidColor(Color(0xFF4C3041)),
                decorationBox = { innerTextField ->
                  if (searchQuery.isEmpty()) {
                    Text(
                      text = "Search or add new tag",
                      fontSize = 15.sp,
                      fontFamily = FontFamily.SansSerif,
                      fontWeight = FontWeight.Normal,
                      color = Color(0xFF707E8E)
                    )
                  }
                  innerTextField()
                },
                singleLine = true
              )

              if (searchQuery.isNotEmpty()) {
                IconButton(
                  onClick = { searchQuery = "" },
                  modifier = Modifier.size(24.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear search",
                    tint = Color(0xFF687482),
                    modifier = Modifier.size(16.dp)
                  )
                }
              }
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Round Plus Button to Create New Tag
            Surface(
              onClick = {
                if (searchQuery.isNotBlank()) {
                  newTagName = searchQuery.trim()
                }
                showAddTagDialog = true
              },
              modifier = Modifier
                .size(42.dp)
                .shadow(4.dp, CircleShape)
                .testTag("add_new_tag_header_button"),
              shape = CircleShape,
              color = Color.White.copy(alpha = 0.88f)
            ) {
              Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.Add,
                  contentDescription = "Add New Tag",
                  tint = Color(0xFF44505F),
                  modifier = Modifier.size(22.dp)
                )
              }
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Section 1: "Moods"
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 22.dp)
      ) {
        Text(
          text = "Moods",
          fontSize = 32.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF140A10),
          letterSpacing = (-0.3).sp,
          modifier = Modifier.testTag("moods_section_header")
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Flow layout of Mood Tag Pills
        FlowRow(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          filteredMoods.forEach { tag ->
            TagPill(
              tag = tag,
              isSelected = selectedTagIds.contains(tag.id),
              onToggle = {
                if (selectedTagIds.contains(tag.id)) {
                  selectedTagIds.remove(tag.id)
                } else {
                  selectedTagIds.add(tag.id)
                }
              }
            )
          }

          if (filteredMoods.isEmpty() && searchQuery.isNotEmpty()) {
            Text(
              text = "No matching moods. Tap '+' to create.",
              fontSize = 13.sp,
              color = Color(0xFF8E8388),
              modifier = Modifier.padding(vertical = 8.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(28.dp))

      // Section 2: "Symptoms"
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 480.dp)
          .padding(horizontal = 22.dp)
      ) {
        Text(
          text = "Symptoms",
          fontSize = 32.sp,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Normal,
          color = Color(0xFF140A10),
          letterSpacing = (-0.3).sp,
          modifier = Modifier.testTag("symptoms_section_header")
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Flow layout of Symptom Tag Pills + "+ Add New" button
        FlowRow(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          filteredSymptoms.forEach { tag ->
            TagPill(
              tag = tag,
              isSelected = selectedTagIds.contains(tag.id),
              onToggle = {
                if (selectedTagIds.contains(tag.id)) {
                  selectedTagIds.remove(tag.id)
                } else {
                  selectedTagIds.add(tag.id)
                }
              }
            )
          }

          // "+ Add New" pill button matching the screenshot design
          Surface(
            onClick = {
              newTagCategory = TagCategory.SYMPTOM
              showAddTagDialog = true
            },
            shape = RoundedCornerShape(20.dp),
            color = Color.White,
            modifier = Modifier
              .height(38.dp)
              .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color(0x18000000),
                spotColor = Color(0x18000000)
              )
              .border(
                width = 1.dp,
                color = Color(0xFFE2DCD7),
                shape = RoundedCornerShape(20.dp)
              )
              .testTag("symptoms_add_new_pill")
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 14.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center
            ) {
              Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add New",
                tint = Color(0xFF2C1E26),
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Add New",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2C1E26),
                fontFamily = FontFamily.SansSerif
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(28.dp))

      // Save Selection Action Button
      if (selectedTagIds.isNotEmpty()) {
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 480.dp)
            .padding(horizontal = 22.dp)
            .shadow(
              elevation = 10.dp,
              shape = RoundedCornerShape(28.dp),
              ambientColor = Color(0x4055374C),
              spotColor = Color(0x4055374C)
            ),
          shape = RoundedCornerShape(28.dp),
          color = Color(0xFF523447)
        ) {
          Button(
            onClick = {
              onSaveSelection(selectedTagIds.toList())
              coroutineScope.launch {
                snackbarHostState.showSnackbar(
                  message = "Updated tags (${selectedTagIds.size} selected)",
                  duration = SnackbarDuration.Short
                )
              }
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = Color.Transparent,
              contentColor = Color.White
            ),
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(54.dp)
              .testTag("save_tags_button")
          ) {
            Text(
              text = "Apply Selected Tags (${selectedTagIds.size})",
              fontSize = 16.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color.White
            )
          }
        }
      }
    }

    // Modal Add Custom Tag Dialog
    if (showAddTagDialog) {
      AlertDialog(
        onDismissRequest = {
          showAddTagDialog = false
          newTagName = ""
        },
        title = {
          Text(
            text = "Create Custom Tag",
            fontFamily = FontFamily.Serif,
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF1B1118)
          )
        },
        text = {
          Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
          ) {
            OutlinedTextField(
              value = newTagName,
              onValueChange = { newTagName = it },
              label = { Text("Tag Name") },
              placeholder = { Text("e.g., Grateful, Low Energy...") },
              singleLine = true,
              shape = RoundedCornerShape(14.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF523447),
                unfocusedBorderColor = Color(0xFFCEBFCA)
              ),
              modifier = Modifier
                .fillMaxWidth()
                .testTag("dialog_new_tag_name_input")
            )

            // Category Selector
            Text(
              text = "Section",
              fontSize = 14.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color(0xFF3B2734)
            )

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              FilterChip(
                selected = newTagCategory == TagCategory.MOOD,
                onClick = { newTagCategory = TagCategory.MOOD },
                label = { Text("Mood") },
                colors = FilterChipDefaults.filterChipColors(
                  selectedContainerColor = Color(0xFF523447),
                  selectedLabelColor = Color.White
                )
              )

              FilterChip(
                selected = newTagCategory == TagCategory.SYMPTOM,
                onClick = { newTagCategory = TagCategory.SYMPTOM },
                label = { Text("Symptom") },
                colors = FilterChipDefaults.filterChipColors(
                  selectedContainerColor = Color(0xFF523447),
                  selectedLabelColor = Color.White
                )
              )
            }

            // Color Palette Selector
            Text(
              text = "Color Theme",
              fontSize = 14.sp,
              fontWeight = FontWeight.SemiBold,
              color = Color(0xFF3B2734)
            )

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              tagColors.forEachIndexed { index, color ->
                Box(
                  modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(color)
                    .clickable { selectedColorIndex = index }
                    .border(
                      width = if (selectedColorIndex == index) 2.5.dp else 0.dp,
                      color = if (selectedColorIndex == index) Color(0xFF1B1118) else Color.Transparent,
                      shape = CircleShape
                    ),
                  contentAlignment = Alignment.Center
                ) {
                  if (selectedColorIndex == index) {
                    Icon(
                      imageVector = Icons.Default.Check,
                      contentDescription = "Selected",
                      tint = Color.White,
                      modifier = Modifier.size(18.dp)
                    )
                  }
                }
              }
            }
          }
        },
        confirmButton = {
          Button(
            onClick = {
              if (newTagName.isNotBlank()) {
                val newTag = TagItem(
                  id = "custom_${System.currentTimeMillis()}",
                  name = newTagName.trim(),
                  category = newTagCategory,
                  backgroundColor = tagColors[selectedColorIndex],
                  icon = if (newTagCategory == TagCategory.MOOD) Icons.Outlined.SentimentSatisfiedAlt else Icons.Outlined.Spa,
                  isCustom = true
                )
                tags.add(newTag)
                selectedTagIds.add(newTag.id)
                showAddTagDialog = false
                newTagName = ""
                searchQuery = ""
                coroutineScope.launch {
                  snackbarHostState.showSnackbar("Added tag '${newTag.name}'")
                }
              }
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF523447),
              contentColor = Color.White
            ),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.testTag("dialog_confirm_add_tag")
          ) {
            Text("Add Tag")
          }
        },
        dismissButton = {
          TextButton(
            onClick = {
              showAddTagDialog = false
              newTagName = ""
            }
          ) {
            Text("Cancel", color = Color(0xFF523447))
          }
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(24.dp)
      )
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
private fun TagPill(
  tag: TagItem,
  isSelected: Boolean,
  onToggle: () -> Unit,
  modifier: Modifier = Modifier
) {
  val scale by animateFloatAsState(
    targetValue = if (isSelected) 1.03f else 1.0f,
    animationSpec = tween(150)
  )

  Surface(
    onClick = onToggle,
    shape = RoundedCornerShape(20.dp),
    color = tag.backgroundColor,
    modifier = modifier
      .height(38.dp)
      .scale(scale)
      .shadow(
        elevation = if (isSelected) 6.dp else 2.dp,
        shape = RoundedCornerShape(20.dp),
        ambientColor = Color(0x28000000),
        spotColor = Color(0x28000000)
      )
      .border(
        width = if (isSelected) 2.dp else 0.dp,
        color = if (isSelected) Color.White else Color.Transparent,
        shape = RoundedCornerShape(20.dp)
      )
      .testTag("tag_pill_${tag.id}")
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 14.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      Icon(
        imageVector = tag.icon,
        contentDescription = tag.name,
        tint = Color.White,
        modifier = Modifier.size(16.dp)
      )

      Spacer(modifier = Modifier.width(6.dp))

      Text(
        text = tag.name,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        fontFamily = FontFamily.SansSerif
      )

      if (isSelected) {
        Spacer(modifier = Modifier.width(6.dp))
        Icon(
          imageVector = Icons.Default.Check,
          contentDescription = "Selected",
          tint = Color.White,
          modifier = Modifier.size(14.dp)
        )
      }
    }
  }
}

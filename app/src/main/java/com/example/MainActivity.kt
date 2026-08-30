package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.BottomNavBar
import com.example.ui.components.LogEntrySheet
import com.example.ui.screens.AppointmentDetailScreen
import com.example.ui.screens.BbtLogScreen
import com.example.ui.screens.BirthControlScreen
import com.example.ui.screens.CalendarScreen
import com.example.ui.screens.CommunityGatewayScreen
import com.example.ui.screens.CommunityPostDetailScreen
import com.example.ui.screens.CreatePostScreen
import com.example.ui.screens.DiscoveryVideoLibraryScreen
import com.example.ui.screens.EmpowerWelcomeScreen
import com.example.ui.screens.HealthProfileScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.HowAreYouFeelingScreen
import com.example.ui.screens.InsightsScreen
import com.example.ui.screens.LoginGatewayScreen
import com.example.ui.screens.LutealNutritionArticleScreen
import com.example.ui.screens.NotificationAlertsScreen
import com.example.ui.screens.OnboardingSuccessScreen
import com.example.ui.screens.PartnerSyncScreen
import com.example.ui.screens.PasscodeLockScreen
import com.example.ui.screens.PersonalizedInsightsScreen
import com.example.ui.screens.PremiumSubscriptionScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.TrackWithEaseScreen
import com.example.ui.screens.YourCycleUnderstoodScreen
import com.example.ui.theme.CreamBackground
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.CycleViewModel
import com.example.ui.viewmodel.NavTab
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        CycleTrackerApp()
      }
    }
  }
}

@Composable
fun CycleTrackerApp(
  viewModel: CycleViewModel = viewModel()
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()

  if (uiState.isPasscodeLockVisible) {
    PasscodeLockScreen(
      onUnlockSuccess = {
        viewModel.hidePasscodeLock()
      }
    )
    return
  }

  if (uiState.isWelcomeVisible) {
    YourCycleUnderstoodScreen(
      onGetStarted = {
        viewModel.showTrackWithEase()
      }
    )
    return
  }

  if (uiState.isCycleUnderstoodVisible) {
    YourCycleUnderstoodScreen(
      onBack = { viewModel.hideCycleUnderstood() },
      onGetStarted = {
        viewModel.showTrackWithEase()
      }
    )
    return
  }

  if (uiState.isTrackWithEaseVisible) {
    TrackWithEaseScreen(
      onBack = { viewModel.showCycleUnderstood() },
      onNext = {
        viewModel.showPersonalizedInsights()
      }
    )
    return
  }

  if (uiState.isPersonalizedInsightsVisible) {
    PersonalizedInsightsScreen(
      onBack = { viewModel.showTrackWithEase() },
      onNext = {
        viewModel.showCommunity()
      }
    )
    return
  }

  if (uiState.isGatewayVisible) {
    LoginGatewayScreen(
      onContinueAsGuest = { viewModel.hideLoginGateway() },
      onSignInSuccess = { name, email, goal ->
        viewModel.handleUserLogin(name, email)
      }
    )
    return
  }

  if (uiState.isCommunityVisible) {
    CommunityGatewayScreen(
      onBack = { viewModel.hideCommunity() },
      onOpenCreatePost = { viewModel.showCreatePost() },
      onOpenPostDetail = { viewModel.showCommunityPostDetail() },
      onCreateAccountSuccess = { name, email ->
        viewModel.hideCommunity()
        viewModel.handleUserLogin(name, email)
      },
      onSignInSuccess = { name, email ->
        viewModel.hideCommunity()
        viewModel.handleUserLogin(name, email)
      }
    )
    return
  }

  if (uiState.isCreatePostVisible) {
    CreatePostScreen(
      onBack = { viewModel.hideCreatePost() },
      onPostSuccess = { content, tags ->
        viewModel.hideCreatePost()
      },
      activeTab = uiState.activeTab,
      onTabSelected = { tab ->
        viewModel.hideCreatePost()
        viewModel.switchTab(tab)
      },
      onAddClick = {
        viewModel.hideCreatePost()
        viewModel.switchTab(NavTab.ADD)
      }
    )
    return
  }

  if (uiState.isCommunityPostDetailVisible) {
    CommunityPostDetailScreen(
      onBack = { viewModel.hideCommunityPostDetail() },
      activeTab = uiState.activeTab,
      onTabSelected = { tab ->
        viewModel.hideCommunityPostDetail()
        viewModel.switchTab(tab)
      },
      onAddClick = {
        viewModel.hideCommunityPostDetail()
        viewModel.switchTab(NavTab.ADD)
      }
    )
    return
  }

  if (uiState.isDiscoveryVideoVisible) {
    DiscoveryVideoLibraryScreen(
      onBack = { viewModel.hideDiscoveryVideo() },
      onOpenArticle = { _ -> viewModel.showArticleDetail() },
      onOpenPremium = { viewModel.showPremium() },
      activeTab = uiState.activeTab,
      onTabSelected = { tab ->
        viewModel.hideDiscoveryVideo()
        viewModel.switchTab(tab)
      },
      onAddClick = {
        viewModel.hideDiscoveryVideo()
        viewModel.switchTab(NavTab.ADD)
      }
    )
    return
  }

  if (uiState.isSuccessScreenVisible) {
    OnboardingSuccessScreen(
      onGoToDashboard = { viewModel.hideSuccessScreen() }
    )
    return
  }

  if (uiState.isPremiumVisible) {
    PremiumSubscriptionScreen(
      onBack = { viewModel.hidePremium() },
      onSubscribe = { viewModel.hidePremium() },
      activeTab = uiState.activeTab,
      onTabSelected = { tab ->
        viewModel.hidePremium()
        viewModel.switchTab(tab)
      },
      onAddClick = {
        viewModel.hidePremium()
        viewModel.showBirthControl()
      }
    )
    return
  }

  if (uiState.isArticleDetailVisible) {
    LutealNutritionArticleScreen(
      onBack = { viewModel.hideArticleDetail() }
    )
    return
  }

  if (uiState.isHealthProfileVisible) {
    HealthProfileScreen(
      onBack = { viewModel.hideHealthProfile() },
      activeTab = uiState.activeTab,
      onTabSelected = { tab ->
        viewModel.hideHealthProfile()
        viewModel.switchTab(tab)
      },
      onAddClick = {
        viewModel.hideHealthProfile()
        viewModel.showBirthControl()
      },
      cycleLengthDays = uiState.userSettings.averageCycleLength,
      periodLengthDays = uiState.userSettings.averagePeriodLength,
      lastPeriodDateText = "Oct 12"
    )
    return
  }

  if (uiState.isPartnerSyncVisible) {
    PartnerSyncScreen(
      onBack = { viewModel.hidePartnerSync() },
      activeTab = uiState.activeTab,
      onTabSelected = { tab ->
        viewModel.hidePartnerSync()
        viewModel.switchTab(tab)
      },
      onAddClick = {
        viewModel.hidePartnerSync()
        viewModel.showBirthControl()
      }
    )
    return
  }

  if (uiState.isNotificationAlertsVisible) {
    NotificationAlertsScreen(
      onBack = { viewModel.hideNotificationAlerts() },
      activeTab = uiState.activeTab,
      onTabSelected = { tab ->
        viewModel.hideNotificationAlerts()
        viewModel.switchTab(tab)
      },
      onAddClick = {
        viewModel.hideNotificationAlerts()
        viewModel.showBirthControl()
      }
    )
    return
  }

  if (uiState.isAppointmentDetailVisible) {
    AppointmentDetailScreen(
      onBack = { viewModel.hideAppointmentDetail() },
      onRescheduleCancel = { viewModel.hideAppointmentDetail() }
    )
    return
  }

  if (uiState.isBirthControlVisible) {
    BirthControlScreen(
      onBack = { viewModel.hideBirthControl() },
      activeTab = uiState.activeTab,
      onTabSelected = { tab ->
        viewModel.hideBirthControl()
        viewModel.switchTab(tab)
      },
      onAddClick = {
        // already on Birth Control
      }
    )
    return
  }

  if (uiState.isBbtLogVisible) {
    BbtLogScreen(
      onBack = { viewModel.hideBbtLog() }
    )
    return
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    containerColor = CreamBackground,
    bottomBar = {
      BottomNavBar(
        activeTab = uiState.activeTab,
        onTabSelected = { tab -> viewModel.switchTab(tab) },
        onAddClick = { viewModel.switchTab(NavTab.ADD) }
      )
    }
  ) { innerPadding ->
    val contentModifier = Modifier
      .fillMaxSize()
      .padding(bottom = innerPadding.calculateBottomPadding())

    when (uiState.activeTab) {
      NavTab.CALENDAR -> {
        CalendarScreen(
          selectedDate = uiState.selectedDate,
          currentMonth = uiState.currentMonth,
          dayLogs = uiState.dayLogs,
          todayDetail = uiState.todayDetail,
          onSelectDate = { date -> viewModel.selectDate(date) },
          onPrevMonth = { viewModel.prevMonth() },
          onNextMonth = { viewModel.nextMonth() },
          onOpenLog = { date -> viewModel.openLogSheet(date) },
          onOpenAppointmentDetail = { viewModel.showAppointmentDetail() },
          modifier = contentModifier
        )
      }
      NavTab.HOME -> {
        HomeScreen(
          selectedDate = uiState.selectedDate,
          userSettings = uiState.userSettings,
          todayDetail = uiState.todayDetail,
          onOpenLog = { date -> viewModel.openLogSheet(date) },
          onGoToCalendar = { viewModel.switchTab(NavTab.CALENDAR) },
          onOpenAlerts = { viewModel.showNotificationAlerts() },
          onOpenArticle = { viewModel.showArticleDetail() },
          onOpenDiscoveryVideo = { viewModel.showDiscoveryVideo() },
          onOpenPremium = { viewModel.showPremium() },
          onOpenCommunity = { viewModel.showCommunity() },
          modifier = contentModifier
        )
      }
      NavTab.ADD -> {
        HowAreYouFeelingScreen(
          onSaveEntry = { mood, note ->
            viewModel.saveMoodAndNote(mood, note)
            viewModel.switchTab(NavTab.HOME)
          },
          onNavigateHome = { viewModel.switchTab(NavTab.HOME) },
          initialMood = uiState.todayDetail.mood.ifEmpty { "Calm" },
          initialNote = uiState.todayDetail.notes,
          modifier = contentModifier
        )
      }
      NavTab.INSIGHTS -> {
        InsightsScreen(
          userSettings = uiState.userSettings,
          dayLogs = uiState.dayLogs,
          onOpenBbtLog = { viewModel.showBbtLog() },
          onOpenBirthControl = { viewModel.showBirthControl() },
          onOpenArticle = { viewModel.showArticleDetail() },
          onOpenDiscoveryVideo = { viewModel.showDiscoveryVideo() },
          onOpenPersonalizedInsights = { viewModel.showPersonalizedInsights() },
          onOpenProfile = { viewModel.switchTab(NavTab.PROFILE) },
          modifier = contentModifier
        )
      }
      NavTab.PROFILE -> {
        ProfileScreen(
          userSettings = uiState.userSettings,
          onSaveSettings = { cycleLength, periodLength, userName, reminders ->
            viewModel.updateUserSettings(cycleLength, periodLength, userName, reminders)
          },
          onOpenGateway = { viewModel.showLoginGateway() },
          onOpenWelcome = { viewModel.showWelcomeScreen() },
          onOpenPasscodeLock = { viewModel.showPasscodeLock() },
          onOpenAlerts = { viewModel.showNotificationAlerts() },
          onOpenPartnerSync = { viewModel.showPartnerSync() },
          onOpenHealthProfile = { viewModel.showHealthProfile() },
          onOpenPremium = { viewModel.showPremium() },
          onOpenCommunity = { viewModel.showCommunity() },
          onOpenCycleUnderstood = { viewModel.showCycleUnderstood() },
          onOpenTrackWithEase = { viewModel.showTrackWithEase() },
          onOpenPersonalizedInsights = { viewModel.showPersonalizedInsights() },
          modifier = contentModifier
        )
      }
    }

    if (uiState.isLogSheetOpen) {
      val logDateKey = uiState.selectedLogDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
      val currentLog = uiState.dayLogs[logDateKey]

      LogEntrySheet(
        date = uiState.selectedLogDate,
        existingLog = currentLog,
        onDismiss = { viewModel.closeLogSheet() },
        onSave = { date, isPeriod, flow, symptoms, events, mood, notes, water ->
          viewModel.saveDayLog(
            date = date,
            isPeriod = isPeriod,
            flow = flow,
            symptoms = symptoms,
            events = events,
            mood = mood,
            notes = notes,
            waterGlasses = water
          )
        }
      )
    }
  }
}

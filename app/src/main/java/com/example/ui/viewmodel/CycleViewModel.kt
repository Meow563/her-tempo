package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.CycleDatabase
import com.example.data.CycleRepository
import com.example.data.DayLogEntity
import com.example.data.UserSettingsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

enum class NavTab {
  HOME,
  CALENDAR,
  ADD,
  INSIGHTS,
  PROFILE
}

data class CalendarUiState(
  val selectedDate: LocalDate = LocalDate.of(2026, 6, 15),
  val currentMonth: YearMonth = YearMonth.of(2026, 6),
  val activeTab: NavTab = NavTab.CALENDAR,
  val isCycleUnderstoodVisible: Boolean = false,
  val isTrackWithEaseVisible: Boolean = false,
  val isPersonalizedInsightsVisible: Boolean = false,
  val isCommunityVisible: Boolean = false,
  val isCreatePostVisible: Boolean = false,
  val isCommunityPostDetailVisible: Boolean = false,
  val isDiscoveryVideoVisible: Boolean = false,
  val isCustomTagsVisible: Boolean = false,
  val isPremiumVisible: Boolean = false,
  val isHealthProfileVisible: Boolean = false,
  val isArticleDetailVisible: Boolean = false,
  val isPartnerSyncVisible: Boolean = false,
  val isNotificationAlertsVisible: Boolean = false,
  val isBirthControlVisible: Boolean = false,
  val isBbtLogVisible: Boolean = false,
  val isAppointmentDetailVisible: Boolean = false,
  val isPasscodeLockVisible: Boolean = false,
  val isWelcomeVisible: Boolean = false,
  val isGatewayVisible: Boolean = false,
  val isSuccessScreenVisible: Boolean = false,
  val isLogSheetOpen: Boolean = false,
  val selectedLogDate: LocalDate = LocalDate.of(2026, 6, 15),
  val userSettings: UserSettingsEntity = UserSettingsEntity(),
  val dayLogs: Map<String, DayLogEntity> = emptyMap(),
  val todayDetail: DayLogEntity = DayLogEntity(
    date = "2026-06-15",
    isPeriod = false,
    flowIntensity = "None",
    isFertile = true,
    isOvulation = true,
    cycleDay = 12,
    phaseName = "Ovulation Phase",
    symptoms = "Mild cramping",
    events = "Yoga at 6 PM"
  )
)

class CycleViewModel(application: Application) : AndroidViewModel(application) {

  private val repository: CycleRepository

  private val _selectedDate = MutableStateFlow(LocalDate.of(2026, 6, 15))
  private val _currentMonth = MutableStateFlow(YearMonth.of(2026, 6))
  private val _activeTab = MutableStateFlow(NavTab.CALENDAR)
  private val _isCycleUnderstoodVisible = MutableStateFlow(false)
  private val _isTrackWithEaseVisible = MutableStateFlow(false)
  private val _isPersonalizedInsightsVisible = MutableStateFlow(false)
  private val _isCommunityVisible = MutableStateFlow(false)
  private val _isCreatePostVisible = MutableStateFlow(false)
  private val _isCommunityPostDetailVisible = MutableStateFlow(false)
  private val _isDiscoveryVideoVisible = MutableStateFlow(false)
  private val _isCustomTagsVisible = MutableStateFlow(false)
  private val _isPremiumVisible = MutableStateFlow(false)
  private val _isHealthProfileVisible = MutableStateFlow(false)
  private val _isArticleDetailVisible = MutableStateFlow(false)
  private val _isPartnerSyncVisible = MutableStateFlow(false)
  private val _isNotificationAlertsVisible = MutableStateFlow(false)
  private val _isBirthControlVisible = MutableStateFlow(false)
  private val _isBbtLogVisible = MutableStateFlow(false)
  private val _isAppointmentDetailVisible = MutableStateFlow(false)
  private val _isPasscodeLockVisible = MutableStateFlow(false)
  private val _isWelcomeVisible = MutableStateFlow(false)
  private val _isGatewayVisible = MutableStateFlow(false)
  private val _isSuccessScreenVisible = MutableStateFlow(false)
  private val _isLogSheetOpen = MutableStateFlow(false)
  private val _selectedLogDate = MutableStateFlow(LocalDate.of(2026, 6, 15))

  val uiState: StateFlow<CalendarUiState>

  private data class NavState(
    val selectedDate: LocalDate,
    val currentMonth: YearMonth,
    val activeTab: NavTab,
    val isCycleUnderstoodVisible: Boolean,
    val isTrackWithEaseVisible: Boolean,
    val isPersonalizedInsightsVisible: Boolean,
    val isCommunityVisible: Boolean,
    val isCreatePostVisible: Boolean,
    val isCommunityPostDetailVisible: Boolean,
    val isDiscoveryVideoVisible: Boolean,
    val isCustomTagsVisible: Boolean,
    val isPremiumVisible: Boolean,
    val isHealthProfileVisible: Boolean,
    val isArticleDetailVisible: Boolean,
    val isPartnerSyncVisible: Boolean,
    val isNotificationAlertsVisible: Boolean,
    val isBirthControlVisible: Boolean,
    val isBbtLogVisible: Boolean,
    val isAppointmentDetailVisible: Boolean,
    val isPasscodeLockVisible: Boolean,
    val isWelcomeVisible: Boolean,
    val isGatewayVisible: Boolean,
    val isSuccessScreenVisible: Boolean,
    val isLogSheetOpen: Boolean,
    val selectedLogDate: LocalDate
  )

  init {
    val database = CycleDatabase.getDatabase(application, viewModelScope)
    repository = CycleRepository(database.cycleDao())

    val calendarNavFlow = combine(
      _selectedDate,
      _currentMonth,
      _activeTab
    ) { selDate, curMonth, tab ->
      Triple(selDate, curMonth, tab)
    }

    val authFlow = combine(
      listOf(
        _isCycleUnderstoodVisible,
        _isTrackWithEaseVisible,
        _isPersonalizedInsightsVisible,
        _isCommunityVisible,
        _isCreatePostVisible,
        _isCommunityPostDetailVisible,
        _isDiscoveryVideoVisible,
        _isCustomTagsVisible,
        _isPremiumVisible,
        _isHealthProfileVisible,
        _isArticleDetailVisible,
        _isPartnerSyncVisible,
        _isNotificationAlertsVisible,
        _isBirthControlVisible,
        _isBbtLogVisible,
        _isAppointmentDetailVisible,
        _isPasscodeLockVisible,
        _isWelcomeVisible,
        _isGatewayVisible,
        _isSuccessScreenVisible
      )
    ) { values ->
      values.toList()
    }

    val sheetFlow = combine(
      _isLogSheetOpen,
      _selectedLogDate
    ) { sheetOpen, logDate ->
      Pair(sheetOpen, logDate)
    }

    val navStateFlow = combine(
      calendarNavFlow,
      authFlow,
      sheetFlow
    ) { cal, auth, sheet ->
      NavState(
        selectedDate = cal.first,
        currentMonth = cal.second,
        activeTab = cal.third,
        isCycleUnderstoodVisible = auth[0],
        isTrackWithEaseVisible = auth[1],
        isPersonalizedInsightsVisible = auth[2],
        isCommunityVisible = auth[3],
        isCreatePostVisible = auth[4],
        isCommunityPostDetailVisible = auth[5],
        isDiscoveryVideoVisible = auth[6],
        isCustomTagsVisible = auth[7],
        isPremiumVisible = auth[8],
        isHealthProfileVisible = auth[9],
        isArticleDetailVisible = auth[10],
        isPartnerSyncVisible = auth[11],
        isNotificationAlertsVisible = auth[12],
        isBirthControlVisible = auth[13],
        isBbtLogVisible = auth[14],
        isAppointmentDetailVisible = auth[15],
        isPasscodeLockVisible = auth[16],
        isWelcomeVisible = auth[17],
        isGatewayVisible = auth[18],
        isSuccessScreenVisible = auth[19],
        isLogSheetOpen = sheet.first,
        selectedLogDate = sheet.second
      )
    }

    uiState = combine(
      repository.allDayLogs,
      repository.userSettings,
      navStateFlow
    ) { logsList, settings, nav ->
      val logsMap = logsList.associateBy { it.date }
      val settingsObj = settings ?: UserSettingsEntity()

      val dateKey = nav.selectedDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
      val detail = logsMap[dateKey] ?: createDefaultDayLog(nav.selectedDate, settingsObj)

      CalendarUiState(
        selectedDate = nav.selectedDate,
        currentMonth = nav.currentMonth,
        activeTab = nav.activeTab,
        isCycleUnderstoodVisible = nav.isCycleUnderstoodVisible,
        isTrackWithEaseVisible = nav.isTrackWithEaseVisible,
        isPersonalizedInsightsVisible = nav.isPersonalizedInsightsVisible,
        isCommunityVisible = nav.isCommunityVisible,
        isCreatePostVisible = nav.isCreatePostVisible,
        isCommunityPostDetailVisible = nav.isCommunityPostDetailVisible,
        isDiscoveryVideoVisible = nav.isDiscoveryVideoVisible,
        isCustomTagsVisible = nav.isCustomTagsVisible,
        isPremiumVisible = nav.isPremiumVisible,
        isHealthProfileVisible = nav.isHealthProfileVisible,
        isArticleDetailVisible = nav.isArticleDetailVisible,
        isPartnerSyncVisible = nav.isPartnerSyncVisible,
        isNotificationAlertsVisible = nav.isNotificationAlertsVisible,
        isBirthControlVisible = nav.isBirthControlVisible,
        isBbtLogVisible = nav.isBbtLogVisible,
        isAppointmentDetailVisible = nav.isAppointmentDetailVisible,
        isPasscodeLockVisible = nav.isPasscodeLockVisible,
        isWelcomeVisible = nav.isWelcomeVisible,
        isGatewayVisible = nav.isGatewayVisible,
        isSuccessScreenVisible = nav.isSuccessScreenVisible,
        isLogSheetOpen = nav.isLogSheetOpen,
        selectedLogDate = nav.selectedLogDate,
        userSettings = settingsObj,
        dayLogs = logsMap,
        todayDetail = detail
      )
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = CalendarUiState()
    )
  }

  private fun createDefaultDayLog(date: LocalDate, settings: UserSettingsEntity): DayLogEntity {
    val lastPeriod = try {
      LocalDate.parse(settings.lastPeriodStartDate)
    } catch (e: Exception) {
      LocalDate.of(2026, 6, 1)
    }
    val (cycleDay, phase) = repository.calculateCycleInfo(
      targetDate = date,
      lastPeriodDate = lastPeriod,
      cycleLength = settings.averageCycleLength,
      periodLength = settings.averagePeriodLength
    )

    return DayLogEntity(
      date = date.format(DateTimeFormatter.ISO_LOCAL_DATE),
      isPeriod = phase == "Menstrual Phase",
      flowIntensity = if (phase == "Menstrual Phase") "Medium" else "None",
      isFertile = phase == "Ovulation Phase",
      isOvulation = phase == "Ovulation Phase" && cycleDay == (settings.averageCycleLength - 14),
      cycleDay = cycleDay,
      phaseName = phase,
      symptoms = if (phase == "Menstrual Phase") "Light cramps" else "",
      events = "",
      mood = "Calm"
    )
  }

  fun selectDate(date: LocalDate) {
    _selectedDate.value = date
  }

  fun nextMonth() {
    _currentMonth.value = _currentMonth.value.plusMonths(1)
  }

  fun prevMonth() {
    _currentMonth.value = _currentMonth.value.minusMonths(1)
  }

  fun setMonth(yearMonth: YearMonth) {
    _currentMonth.value = yearMonth
  }

  fun switchTab(tab: NavTab) {
    _activeTab.value = tab
    _isCycleUnderstoodVisible.value = false
    _isPremiumVisible.value = false
    _isCommunityVisible.value = false
    _isTrackWithEaseVisible.value = false
    _isPersonalizedInsightsVisible.value = false
    _isHealthProfileVisible.value = false
    _isArticleDetailVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun showCycleUnderstood() {
    _isCycleUnderstoodVisible.value = true
    _isTrackWithEaseVisible.value = false
    _isPersonalizedInsightsVisible.value = false
    _isCommunityVisible.value = false
    _isPremiumVisible.value = false
    _isHealthProfileVisible.value = false
    _isArticleDetailVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideCycleUnderstood() {
    _isCycleUnderstoodVisible.value = false
  }

  fun showTrackWithEase() {
    _isTrackWithEaseVisible.value = true
    _isCycleUnderstoodVisible.value = false
    _isPersonalizedInsightsVisible.value = false
    _isCommunityVisible.value = false
    _isPremiumVisible.value = false
    _isHealthProfileVisible.value = false
    _isArticleDetailVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideTrackWithEase() {
    _isTrackWithEaseVisible.value = false
  }

  fun showPersonalizedInsights() {
    _isPersonalizedInsightsVisible.value = true
    _isCommunityVisible.value = false
    _isPremiumVisible.value = false
    _isHealthProfileVisible.value = false
    _isArticleDetailVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hidePersonalizedInsights() {
    _isPersonalizedInsightsVisible.value = false
  }

  fun showCommunity() {
    _isCommunityVisible.value = true
    _isCreatePostVisible.value = false
    _isPersonalizedInsightsVisible.value = false
    _isPremiumVisible.value = false
    _isHealthProfileVisible.value = false
    _isArticleDetailVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideCommunity() {
    _isCommunityVisible.value = false
  }

  fun showCreatePost() {
    _isCreatePostVisible.value = true
    _isCommunityVisible.value = false
    _isPersonalizedInsightsVisible.value = false
    _isPremiumVisible.value = false
    _isHealthProfileVisible.value = false
    _isArticleDetailVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideCreatePost() {
    _isCreatePostVisible.value = false
  }

  fun showCommunityPostDetail() {
    _isCommunityPostDetailVisible.value = true
    _isCreatePostVisible.value = false
    _isCommunityVisible.value = false
    _isPersonalizedInsightsVisible.value = false
    _isPremiumVisible.value = false
    _isHealthProfileVisible.value = false
    _isArticleDetailVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideCommunityPostDetail() {
    _isCommunityPostDetailVisible.value = false
  }

  fun showDiscoveryVideo() {
    _isDiscoveryVideoVisible.value = true
    _isCommunityPostDetailVisible.value = false
    _isCreatePostVisible.value = false
    _isCommunityVisible.value = false
    _isPersonalizedInsightsVisible.value = false
    _isPremiumVisible.value = false
    _isHealthProfileVisible.value = false
    _isArticleDetailVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideDiscoveryVideo() {
    _isDiscoveryVideoVisible.value = false
  }

  fun showCustomTags() {
    _isCustomTagsVisible.value = true
    _isDiscoveryVideoVisible.value = false
    _isCommunityPostDetailVisible.value = false
    _isCreatePostVisible.value = false
    _isCommunityVisible.value = false
    _isPersonalizedInsightsVisible.value = false
    _isPremiumVisible.value = false
    _isHealthProfileVisible.value = false
    _isArticleDetailVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideCustomTags() {
    _isCustomTagsVisible.value = false
  }

  fun showPremium() {
    _isPremiumVisible.value = true
    _isPersonalizedInsightsVisible.value = false
    _isCommunityVisible.value = false
    _isHealthProfileVisible.value = false
    _isArticleDetailVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hidePremium() {
    _isPremiumVisible.value = false
  }

  fun showArticleDetail() {
    _isArticleDetailVisible.value = true
    _isHealthProfileVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideArticleDetail() {
    _isArticleDetailVisible.value = false
  }

  fun showHealthProfile() {
    _isHealthProfileVisible.value = true
    _isArticleDetailVisible.value = false
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideHealthProfile() {
    _isHealthProfileVisible.value = false
  }

  fun showPartnerSync() {
    _isPartnerSyncVisible.value = true
    _isNotificationAlertsVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hidePartnerSync() {
    _isPartnerSyncVisible.value = false
  }

  fun showNotificationAlerts() {
    _isNotificationAlertsVisible.value = true
    _isPartnerSyncVisible.value = false
    _isBirthControlVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideNotificationAlerts() {
    _isNotificationAlertsVisible.value = false
  }

  fun showBirthControl() {
    _isBirthControlVisible.value = true
    _isPartnerSyncVisible.value = false
    _isNotificationAlertsVisible.value = false
    _isBbtLogVisible.value = false
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideBirthControl() {
    _isBirthControlVisible.value = false
  }

  fun showBbtLog() {
    _isBbtLogVisible.value = true
    _isAppointmentDetailVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideBbtLog() {
    _isBbtLogVisible.value = false
  }

  fun showAppointmentDetail() {
    _isAppointmentDetailVisible.value = true
    _isBbtLogVisible.value = false
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideAppointmentDetail() {
    _isAppointmentDetailVisible.value = false
  }

  fun showPasscodeLock() {
    _isPasscodeLockVisible.value = true
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hidePasscodeLock() {
    _isPasscodeLockVisible.value = false
  }

  fun showWelcomeScreen() {
    _isPasscodeLockVisible.value = false
    _isWelcomeVisible.value = true
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = false
  }

  fun hideWelcomeScreen() {
    _isWelcomeVisible.value = false
  }

  fun showLoginGateway() {
    _isWelcomeVisible.value = false
    _isGatewayVisible.value = true
    _isSuccessScreenVisible.value = false
  }

  fun hideLoginGateway() {
    _isGatewayVisible.value = false
  }

  fun showSuccessScreen() {
    _isGatewayVisible.value = false
    _isSuccessScreenVisible.value = true
  }

  fun hideSuccessScreen() {
    _isSuccessScreenVisible.value = false
  }

  fun handleUserLogin(name: String, email: String) {
    viewModelScope.launch {
      val current = uiState.value.userSettings
      val updated = current.copy(
        userName = name.ifEmpty { current.userName }
      )
      repository.saveUserSettings(updated)
      _isGatewayVisible.value = false
      _isSuccessScreenVisible.value = true
    }
  }

  fun openLogSheet(date: LocalDate? = null) {
    _selectedLogDate.value = date ?: _selectedDate.value
    _isLogSheetOpen.value = true
  }

  fun closeLogSheet() {
    _isLogSheetOpen.value = false
  }

  fun saveDayLog(
    date: LocalDate,
    isPeriod: Boolean,
    flow: String,
    symptoms: String,
    events: String,
    mood: String,
    notes: String,
    waterGlasses: Int
  ) {
    viewModelScope.launch {
      val settings = uiState.value.userSettings
      val lastPeriod = try {
        LocalDate.parse(settings.lastPeriodStartDate)
      } catch (e: Exception) {
        LocalDate.of(2026, 6, 1)
      }

      val (cycleDay, phase) = repository.calculateCycleInfo(
        targetDate = date,
        lastPeriodDate = lastPeriod,
        cycleLength = settings.averageCycleLength,
        periodLength = settings.averagePeriodLength
      )

      val dateStr = date.format(DateTimeFormatter.ISO_LOCAL_DATE)
      val newLog = DayLogEntity(
        date = dateStr,
        isPeriod = isPeriod,
        flowIntensity = flow,
        isFertile = phase == "Ovulation Phase",
        isOvulation = phase == "Ovulation Phase" && cycleDay == (settings.averageCycleLength - 14),
        cycleDay = cycleDay,
        phaseName = phase,
        symptoms = symptoms,
        events = events,
        mood = mood,
        notes = notes,
        waterGlasses = waterGlasses
      )

      repository.saveDayLog(newLog)

      // If user logged a new start of period, update lastPeriodStartDate if appropriate
      if (isPeriod && flow in listOf("Light", "Medium", "Heavy")) {
        // Can optionally update lastPeriodDate if requested
      }

      _isLogSheetOpen.value = false
    }
  }

  fun saveMoodAndNote(mood: String, note: String) {
    viewModelScope.launch {
      val date = _selectedDate.value
      val dateKey = date.format(DateTimeFormatter.ISO_LOCAL_DATE)
      val existing = uiState.value.dayLogs[dateKey] ?: createDefaultDayLog(date, uiState.value.userSettings)
      val updated = existing.copy(
        mood = mood,
        notes = if (note.isNotBlank()) note else existing.notes
      )
      repository.saveDayLog(updated)
    }
  }

  fun updateUserSettings(
    cycleLength: Int,
    periodLength: Int,
    userName: String,
    reminders: Boolean
  ) {
    viewModelScope.launch {
      val current = uiState.value.userSettings
      val updated = current.copy(
        averageCycleLength = cycleLength,
        averagePeriodLength = periodLength,
        userName = userName,
        notificationsEnabled = reminders
      )
      repository.saveUserSettings(updated)
    }
  }
}

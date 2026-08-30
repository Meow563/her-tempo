package com.example

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.example.data.DayLogEntity
import com.example.ui.screens.CalendarScreen
import com.example.ui.theme.MyApplicationTheme
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.time.LocalDate
import java.time.YearMonth

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel8, sdk = [36])
class GreetingScreenshotTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun greeting_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        CalendarScreen(
          selectedDate = LocalDate.of(2026, 6, 15),
          currentMonth = YearMonth.of(2026, 6),
          dayLogs = emptyMap<String, DayLogEntity>(),
          todayDetail = DayLogEntity(
            date = "2026-06-15",
            isPeriod = false,
            cycleDay = 12,
            phaseName = "Ovulation Phase",
            symptoms = "Mild cramping",
            events = "Yoga at 6 PM"
          ),
          onSelectDate = {},
          onPrevMonth = {},
          onNextMonth = {},
          onOpenLog = {}
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/greeting.png")
  }

  @Test
  fun login_gateway_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        com.example.ui.screens.LoginGatewayScreen(
          onContinueAsGuest = {},
          onSignInSuccess = { _, _, _ -> }
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/login_gateway.png")
  }

  @Test
  fun onboarding_success_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        com.example.ui.screens.OnboardingSuccessScreen(
          onGoToDashboard = {}
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/onboarding_success.png")
  }

  @Test
  fun empower_welcome_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        com.example.ui.screens.EmpowerWelcomeScreen(
          onLetBegin = {}
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/empower_welcome.png")
  }

  @Test
  fun passcode_lock_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        com.example.ui.screens.PasscodeLockScreen(
          onUnlockSuccess = {},
          appName = "CycleWell"
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/passcode_lock.png")
  }

  @Test
  fun app_preferences_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        com.example.ui.screens.AppPreferencesScreen()
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/app_preferences.png")
  }

  @Test
  fun appointment_detail_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        com.example.ui.screens.AppointmentDetailScreen(
          onBack = {},
          onRescheduleCancel = {}
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/appointment_detail.png")
  }

  @Test
  fun bbt_log_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        com.example.ui.screens.BbtLogScreen(
          onBack = {}
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/bbt_log.png")
  }

  @Test
  fun birth_control_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        com.example.ui.screens.BirthControlScreen(
          onBack = {}
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/birth_control.png")
  }

  @Test
  fun notification_alerts_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        com.example.ui.screens.NotificationAlertsScreen(
          onBack = {}
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/notification_alerts.png")
  }

  @Test
  fun partner_sync_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        com.example.ui.screens.PartnerSyncScreen(
          onBack = {}
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/partner_sync.png")
  }
}

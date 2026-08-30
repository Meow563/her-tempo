package com.example.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class CycleRepository(private val cycleDao: CycleDao) {

  val allDayLogs: Flow<List<DayLogEntity>> = cycleDao.getAllDayLogs()
  val userSettings: Flow<UserSettingsEntity?> = cycleDao.observeUserSettings()

  suspend fun getDayLog(date: String): DayLogEntity? {
    return cycleDao.getDayLog(date)
  }

  suspend fun saveDayLog(dayLog: DayLogEntity) {
    cycleDao.insertOrUpdateDayLog(dayLog)
  }

  suspend fun saveUserSettings(settings: UserSettingsEntity) {
    cycleDao.insertOrUpdateUserSettings(settings)
  }

  /**
   * Helper to compute estimated cycle day and phase for any date based on last period start date.
   */
  fun calculateCycleInfo(
    targetDate: LocalDate,
    lastPeriodDate: LocalDate,
    cycleLength: Int = 28,
    periodLength: Int = 5
  ): Pair<Int, String> {
    val daysBetween = ChronoUnit.DAYS.between(lastPeriodDate, targetDate)
    val normalizedDay = if (daysBetween >= 0) {
      ((daysBetween % cycleLength) + 1).toInt()
    } else {
      val mod = (daysBetween % cycleLength).toInt()
      if (mod == 0) 1 else (cycleLength + mod + 1)
    }

    val ovulationDay = cycleLength - 14
    val phase = when {
      normalizedDay <= periodLength -> "Menstrual Phase"
      normalizedDay < ovulationDay - 2 -> "Follicular Phase"
      normalizedDay <= ovulationDay + 2 -> "Ovulation Phase"
      else -> "Luteal Phase"
    }

    return Pair(normalizedDay, phase)
  }
}

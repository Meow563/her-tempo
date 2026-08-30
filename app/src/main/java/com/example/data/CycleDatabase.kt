package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [DayLogEntity::class, UserSettingsEntity::class], version = 1, exportSchema = false)
abstract class CycleDatabase : RoomDatabase() {
  abstract fun cycleDao(): CycleDao

  companion object {
    @Volatile
    private var INSTANCE: CycleDatabase? = null

    fun getDatabase(context: Context, scope: CoroutineScope): CycleDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          CycleDatabase::class.java,
          "cycle_tracker_db"
        )
          .addCallback(CycleDatabaseCallback(scope))
          .fallbackToDestructiveMigration()
          .build()
        INSTANCE = instance
        instance
      }
    }

    private class CycleDatabaseCallback(
      private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
      override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        INSTANCE?.let { database ->
          scope.launch(Dispatchers.IO) {
            populateInitialData(database.cycleDao())
          }
        }
      }

      suspend fun populateInitialData(dao: CycleDao) {
        val settings = UserSettingsEntity(
          id = "primary_user",
          averageCycleLength = 28,
          averagePeriodLength = 5,
          lastPeriodStartDate = "2026-06-01",
          userName = "Sarah",
          notificationsEnabled = true
        )
        dao.insertOrUpdateUserSettings(settings)

        val initialLogs = mutableListOf<DayLogEntity>()

        // Prepopulate June 2026 days to match exact screenshot design
        for (day in 1..30) {
          val dateStr = String.format("2026-06-%02d", day)
          val isPeriod = day in listOf(1, 5, 12, 13)
          val isFertile = day in listOf(10, 11, 14, 22, 23, 24, 26, 28)
          val isOvulation = day in listOf(14, 15)

          val flow = when (day) {
            1 -> "Medium"
            5 -> "Light"
            12 -> "Spotting"
            13 -> "Light"
            else -> "None"
          }

          val symptoms = when (day) {
            12, 15 -> "Mild cramping"
            1 -> "Lower back ache, Fatigue"
            5 -> "Energy returning"
            14 -> "Increased energy"
            24 -> "Mild bloating"
            else -> ""
          }

          val events = when (day) {
            12, 15 -> "Yoga at 6 PM"
            5 -> "Meditation 20 min"
            10 -> "Pilates session"
            20 -> "Dinner with Emma"
            26 -> "Gentle Walk & Stretch"
            else -> ""
          }

          val phase = when {
            day <= 5 -> "Menstrual Phase"
            day in 6..11 -> "Follicular Phase"
            day in 12..16 -> "Ovulation Phase"
            else -> "Luteal Phase"
          }

          initialLogs.add(
            DayLogEntity(
              date = dateStr,
              isPeriod = isPeriod,
              flowIntensity = flow,
              isFertile = isFertile,
              isOvulation = isOvulation,
              cycleDay = day,
              phaseName = phase,
              symptoms = symptoms,
              events = events,
              mood = if (day in 12..16) "Calm" else "Happy",
              notes = if (day == 15) "Felt peaceful after yoga class." else "",
              waterGlasses = 7
            )
          )
        }

        dao.insertAll(initialLogs)
      }
    }
  }
}

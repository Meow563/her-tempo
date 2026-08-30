package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CycleDao {
  @Query("SELECT * FROM day_logs")
  fun getAllDayLogs(): Flow<List<DayLogEntity>>

  @Query("SELECT * FROM day_logs WHERE date = :date LIMIT 1")
  suspend fun getDayLog(date: String): DayLogEntity?

  @Query("SELECT * FROM day_logs WHERE date = :date LIMIT 1")
  fun observeDayLog(date: String): Flow<DayLogEntity?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOrUpdateDayLog(dayLog: DayLogEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(dayLogs: List<DayLogEntity>)

  @Query("SELECT * FROM user_settings WHERE id = 'primary_user' LIMIT 1")
  fun observeUserSettings(): Flow<UserSettingsEntity?>

  @Query("SELECT * FROM user_settings WHERE id = 'primary_user' LIMIT 1")
  suspend fun getUserSettings(): UserSettingsEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertOrUpdateUserSettings(settings: UserSettingsEntity)
}

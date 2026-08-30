package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_settings")
data class UserSettingsEntity(
  @PrimaryKey
  val id: String = "primary_user",
  val averageCycleLength: Int = 28,
  val averagePeriodLength: Int = 5,
  val lastPeriodStartDate: String = "2026-06-01",
  val userName: String = "Sarah",
  val notificationsEnabled: Boolean = true
)

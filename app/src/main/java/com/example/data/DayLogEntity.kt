package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day_logs")
data class DayLogEntity(
  @PrimaryKey
  val date: String, // Format: YYYY-MM-DD
  val isPeriod: Boolean = false,
  val flowIntensity: String = "None", // None, Spotting, Light, Medium, Heavy
  val isFertile: Boolean = false,
  val isOvulation: Boolean = false,
  val cycleDay: Int = 1,
  val phaseName: String = "Follicular Phase", // Menstrual Phase, Follicular Phase, Ovulation Phase, Luteal Phase
  val symptoms: String = "", // Comma-separated (e.g. "Mild cramping, Bloating")
  val events: String = "", // e.g. "Yoga at 6 PM"
  val mood: String = "Calm", // Calm, Happy, Energetic, Sensitive, Tired, Anxious
  val notes: String = "",
  val temperature: Float? = null,
  val waterGlasses: Int = 0
)

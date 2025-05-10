package com.example.voca.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voice")
data class VoiceDao(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String?,
    val fileName: String,
    val filePath: String,
    val recordedAt: Long
)

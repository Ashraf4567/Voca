package com.example.voca.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voice_notes")
data class VoiceNoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val audioFilePath: String,
    val description: String?,
    val aiSummary: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val syncStatus: String,
    val lastSyncedAt: Long?,
    val serverVersion: Int = 0,
    val localVersion: Int = 0
)

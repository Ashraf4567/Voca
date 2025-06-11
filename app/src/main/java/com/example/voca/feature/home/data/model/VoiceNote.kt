package com.example.voca.feature.home.data.model

import com.example.voca.util.SyncStatus

data class VoiceNote(
    val id: String,
    val title: String,
    val audioFilePath: String,
    val description: String?,
    val aiSummary: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val syncStatus: SyncStatus,
    val lastSyncedAt: Long?
)
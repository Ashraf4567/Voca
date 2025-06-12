package com.example.voca.feature.home.data.model

import com.example.voca.util.SyncStatus
import java.util.UUID

data class VoiceNote(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val audioFilePath: String,
    val description: String?,
    val aiSummary: String? = null,
    val createdAt: Long,
    val updatedAt: Long,
    val syncStatus: SyncStatus,
    val lastSyncedAt: Long? = null
)
package com.example.voca.feature.home.data

import com.example.voca.core.data.model.VoiceNoteEntity
import com.example.voca.feature.home.data.model.VoiceNote
import com.example.voca.util.SyncStatus

fun VoiceNoteEntity.toVoiceNote(): VoiceNote {
    return VoiceNote(
        id = id,
        title = title,
        audioFilePath = audioFilePath,
        description = description,
        aiSummary = aiSummary,
        createdAt = createdAt,
        updatedAt = updatedAt,
        syncStatus = SyncStatus.valueOf(syncStatus),
        lastSyncedAt = lastSyncedAt
    )
}

fun VoiceNote.toVoiceNoteDao(): VoiceNoteEntity {
    return VoiceNoteEntity(
        id = id,
        title = title,
        audioFilePath = audioFilePath,
        description = description,
        aiSummary = aiSummary,
        createdAt = createdAt,
        updatedAt = updatedAt,
        syncStatus = syncStatus.name,
        lastSyncedAt = lastSyncedAt,
        serverVersion = 0,
    )
}
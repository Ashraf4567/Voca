package com.example.voca.feature.home.data

import com.example.voca.core.data.model.VoiceDao
import com.example.voca.feature.home.data.model.VoiceNote

fun VoiceDao.toVoiceNote(): VoiceNote {
    return VoiceNote(
        id = id,
        title = title,
        description = description,
        fileName = fileName,
        filePath = filePath,
        recordedAt = recordedAt
    )
}

fun VoiceNote.toVoiceNoteDao(): VoiceDao {
    return VoiceDao(
        id = id,
        title = title ?: "",
        description = description,
        fileName = fileName ?: "",
        filePath = filePath ?: "",
        recordedAt = recordedAt ?: 0L
    )
}
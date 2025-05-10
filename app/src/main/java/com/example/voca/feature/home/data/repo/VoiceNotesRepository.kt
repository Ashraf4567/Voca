package com.example.voca.feature.home.data.repo

import com.example.voca.feature.home.data.model.VoiceNote
import kotlinx.coroutines.flow.Flow
import java.io.File

interface VoiceNotesRepository {
    fun getAllVoiceNotes(): Flow<List<VoiceNote>>
    suspend fun getVoiceNoteById(id: Int): VoiceNote?
    suspend fun insertVoiceNote(voiceNote: VoiceNote)
    fun getVoiceFile(): File

}
package com.example.voca.feature.home.data.repo

import android.content.Context
import com.example.voca.feature.home.data.VoiceNoteDao
import com.example.voca.feature.home.data.model.VoiceNote
import com.example.voca.feature.home.data.toVoiceNote
import com.example.voca.feature.home.data.toVoiceNoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.File

class VoiceNotesRepositoryImpl(
    private val dao: VoiceNoteDao,
    context: Context
): VoiceNotesRepository {

    private val voiceDir = File(context.filesDir, "voice")
    init {
        if (!voiceDir.exists()) {
            voiceDir.mkdir()
        }
    }

    override fun getVoiceFile(): File {
        return File(voiceDir, "${System.currentTimeMillis()}.mp3")
    }

    override fun getAllVoiceNotes(): Flow<List<VoiceNote>> = flow {
        dao.getAllVoiceNotes().map { list ->
            list.map { it.toVoiceNote() }
        }
    }

    override suspend fun getVoiceNoteById(id: Int): VoiceNote? {
        return dao.getVoiceNoteById(id)?.toVoiceNote()
    }

    override suspend fun insertVoiceNote(voiceNote: VoiceNote) {
        dao.insertVoiceNote(voiceNote.toVoiceNoteDao())
    }
}
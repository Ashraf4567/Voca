package com.example.voca.feature.home.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.voca.core.data.model.VoiceNoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class VoiceNoteDao {
    @Query("SELECT * FROM voice_notes")
    abstract fun getAllVoiceNotes(): Flow<List<VoiceNoteEntity>>

    @Query("SELECT * FROM voice_notes WHERE id = :id")
    abstract suspend fun getVoiceNoteById(id: Int): VoiceNoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertVoiceNote(voiceNote: VoiceNoteEntity)

}
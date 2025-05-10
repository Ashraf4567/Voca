package com.example.voca.feature.home.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.voca.core.data.model.VoiceDao
import kotlinx.coroutines.flow.Flow

@Dao
abstract class VoiceNoteDao {
    @Query("SELECT * FROM voice")
    abstract fun getAllVoiceNotes(): Flow<List<VoiceDao>>

    @Query("SELECT * FROM voice WHERE id = :id")
    abstract suspend fun getVoiceNoteById(id: Int): VoiceDao?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertVoiceNote(voiceNote: VoiceDao)

}
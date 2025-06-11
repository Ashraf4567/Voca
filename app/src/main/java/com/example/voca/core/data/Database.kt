package com.example.voca.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.voca.core.data.model.VoiceNoteEntity
import com.example.voca.feature.home.data.VoiceNoteDao

@Database(
    entities = [
        VoiceNoteEntity::class
    ], version = 1, exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun voiceNotesDao(): VoiceNoteDao
}
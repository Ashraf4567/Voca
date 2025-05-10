package com.example.voca.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.voca.core.data.model.VoiceDao
import com.example.voca.feature.home.data.VoiceNoteDao

@Database(
    entities = [
        VoiceDao::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun voiceNotesDao(): VoiceNoteDao
}
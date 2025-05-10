package com.example.voca.di

import androidx.room.Room
import com.example.voca.core.data.AppDatabase
import com.example.voca.core.media.player.AndroidAudioPlayer
import com.example.voca.core.media.player.AudioPlayer
import com.example.voca.core.media.recorder.AndroidAudioRecorder
import com.example.voca.core.media.recorder.AudioRecorder
import com.example.voca.feature.home.data.repo.VoiceNotesRepository
import com.example.voca.feature.home.data.repo.VoiceNotesRepositoryImpl
import com.example.voca.feature.home.presentation.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AudioPlayer> { AndroidAudioPlayer(get()) }
    single<AudioRecorder> { AndroidAudioRecorder(get()) }
    //room database
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_database").build() }
    single { get<AppDatabase>().voiceNotesDao() }
    single<VoiceNotesRepository> { VoiceNotesRepositoryImpl(get(), get())  }

    viewModel { HomeViewModel(get(), get()) }

}
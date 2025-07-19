package com.example.voca.di

import androidx.room.Room
import com.example.voca.BuildConfig
import com.example.voca.MainViewModel
import com.example.voca.core.data.AppDatabase
import com.example.voca.core.media.player.AndroidAudioPlayer
import com.example.voca.core.media.player.AudioPlayer
import com.example.voca.core.media.recorder.AndroidAudioRecorder
import com.example.voca.core.media.recorder.AudioRecorder
import com.example.voca.feature.auth.data.AuthRepositoryImpl
import com.example.voca.feature.auth.data.UserMapper
import com.example.voca.feature.auth.domain.AuthRepository
import com.example.voca.feature.auth.domain.GoogleSignInUseCase
import com.example.voca.feature.auth.helper.CredentialsHelper
import com.example.voca.feature.auth.presentation.LoginViewModel
import com.example.voca.feature.home.data.repo.VoiceNotesRepository
import com.example.voca.feature.home.data.repo.VoiceNotesRepositoryImpl
import com.example.voca.feature.home.presentation.HomeViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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

    single<SupabaseClient> {
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_KEY
        ){
            install(Auth)
        }
    }

    viewModel { HomeViewModel(get(), get()) }
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<AuthRepository> {
        AuthRepositoryImpl(
            supabaseClient = get(),
            ioDispatcher = get()
        )
    }
    single {
        CredentialsHelper()
    }
    factory {
        GoogleSignInUseCase(
            authRepository = get(),
            credentialsHelper = get()
        )
    }
    factory { UserMapper() }
    viewModel { LoginViewModel(get() , get()) }
    viewModel { MainViewModel(get()) }

}
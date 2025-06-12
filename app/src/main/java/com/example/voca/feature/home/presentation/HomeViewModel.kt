package com.example.voca.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voca.core.media.recorder.AudioRecorder
import com.example.voca.feature.home.data.repo.VoiceNotesRepository
import com.example.voca.feature.home.data.model.VoiceNote
import com.example.voca.util.SyncStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class HomeViewModel(
    private val audioRecorder: AudioRecorder,
    private val voiceNotesRepository: VoiceNotesRepository
): ViewModel()  {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        HomeState()
    )


    private var currentAudioFile: File? = null

    fun startRecording() {
        _state.update {
            it.copy(
                isRecording = true
            )
        }
        currentAudioFile = voiceNotesRepository.getVoiceFile()
        audioRecorder.start(currentAudioFile!!)

    }
    fun stopRecording() {
        _state.update {
            it.copy(
                isRecording = false
            )
        }
        audioRecorder.stop()
        currentAudioFile?.let { file ->
            viewModelScope.launch {
                voiceNotesRepository.insertVoiceNote(
                    VoiceNote(
                        title = "test",
                        description = "test",
                        audioFilePath = file.absolutePath,
                        createdAt = System.currentTimeMillis(),
                        updatedAt = System.currentTimeMillis(),
                        syncStatus = SyncStatus.PENDING_UPDATE,
                    )
                )
            }
        }
        currentAudioFile = null
    }

    fun discardRecord(){
        _state.update {
            it.copy(
                isRecording = false
            )
        }
        currentAudioFile?.delete()
        currentAudioFile = null
        audioRecorder.stop()
    }



}
package com.example.voca.feature.home.data.model

data class VoiceNote(
    val id: Int? = null,
    val title: String?,
    val description: String?,
    val fileName: String?,
    val filePath: String?,
    val recordedAt: Long? = null
)

package com.example.voca.core.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class AddRecordScreen(
    val recordPath: String
)
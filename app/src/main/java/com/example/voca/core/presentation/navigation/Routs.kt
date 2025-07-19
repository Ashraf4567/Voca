package com.example.voca.core.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object LoginScreen

@Serializable
data class AddRecordScreen(
    val recordPath: String
)
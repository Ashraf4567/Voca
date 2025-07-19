package com.example.voca.feature.auth.data

import com.example.voca.feature.auth.data.model.User

sealed interface AuthResponse {
    data object Loading : AuthResponse
    data class Success(val user: User) : AuthResponse
    data class Error(val message: String) : AuthResponse
}
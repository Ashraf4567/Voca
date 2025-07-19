package com.example.voca.feature.auth.data.model

import io.github.jan.supabase.auth.user.UserInfo

sealed class AuthState {
    data object Initial : AuthState()
    data object Loading : AuthState()
    data class Authenticated(val user: UserInfo) : AuthState()
    data object Unauthenticated : AuthState()
    data class Error(val message: String) : AuthState()
}
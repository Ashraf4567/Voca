package com.example.voca.feature.auth.domain

import com.example.voca.core.domain.util.Result
import com.example.voca.feature.auth.data.model.User
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInWithGoogle(googleIdToken: String, nonce: String): Result<UserInfo>
    fun checkAuthenticationStatus(): Flow<SessionStatus>
    suspend fun signOut(): Boolean
    suspend fun saveUserProfile(user: User): Boolean

}
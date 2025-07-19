package com.example.voca.feature.auth.data

import com.example.voca.core.domain.util.Result
import com.example.voca.feature.auth.data.model.User
import com.example.voca.feature.auth.domain.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.IDToken
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val supabaseClient: SupabaseClient,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthRepository {

    override suspend fun signInWithGoogle(googleIdToken: String, nonce: String): Result<UserInfo> {

        return withContext(ioDispatcher) {
            try {
                supabaseClient.auth.signInWith(IDToken) {
                    idToken = googleIdToken
                    provider = Google
                }
                val supabaseUser = supabaseClient.auth.currentUserOrNull()
                if (supabaseUser != null) {
                    Result.Success(supabaseUser)
                } else {
                    Result.Error(Exception("User not found"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Result.Error(e)
            }
        }

    }

    override fun checkAuthenticationStatus(): Flow<SessionStatus> = flow {
        supabaseClient.auth.sessionStatus.collect{sessionStatus ->
            emit(sessionStatus)
        }
    }

    override suspend fun signOut(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveUserProfile(user: User): Boolean {
        TODO("Not yet implemented")
    }

}
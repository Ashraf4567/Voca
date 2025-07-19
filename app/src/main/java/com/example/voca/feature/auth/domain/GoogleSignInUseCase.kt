package com.example.voca.feature.auth.domain

import android.app.Activity
import com.example.voca.core.domain.util.Result
import com.example.voca.feature.auth.helper.CredentialsHelper
import io.github.jan.supabase.auth.user.UserInfo

class GoogleSignInUseCase(
    private val authRepository: AuthRepository,
    private val credentialsHelper: CredentialsHelper,
) {

    suspend operator fun invoke(
        activity: Activity
    ): Result<UserInfo> {
        return try {
            val idToken = credentialsHelper.getGoogleIdToken(activity)
                ?: return Result.Error(Exception("Google ID token is null"))

            val nonce = credentialsHelper.createNonce()

            authRepository.signInWithGoogle(idToken, nonce)

        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}
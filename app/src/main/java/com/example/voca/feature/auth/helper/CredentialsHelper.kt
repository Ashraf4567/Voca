package com.example.voca.feature.auth.helper

import android.app.Activity
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.example.voca.BuildConfig
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import java.security.MessageDigest
import java.util.UUID

class CredentialsHelper {


    fun createNonce(): String{
        val rowNonce = UUID.randomUUID().toString()
        val bytes = rowNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    suspend fun getGoogleIdToken(
        context: Activity
    ): String? {
        return try {
            val nonce = createNonce()
            val googleIdOption = GetGoogleIdOption.Builder()
                .setServerClientId(BuildConfig.GOOGLE_SERVER_CLIENT_ID)
                .setNonce(nonce)
                .setAutoSelectEnabled(true)
                .setFilterByAuthorizedAccounts(false)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val credentialManager = CredentialManager.create(context)
            val result = credentialManager.getCredential(
                context = context,
                request = request
            )

            // return the id token

            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(result.credential.data)
            Log.d("CredentialsHelper", "getGoogleIdToken: ${googleIdTokenCredential.idToken}")
            googleIdTokenCredential.idToken

        } catch (e: GetCredentialException) {
            e.printStackTrace()
            Log.e("CredentialsHelper", "GetCredentialException: ${e.message}")
            null
        }
        catch (e: Exception) {
            e.printStackTrace()
            Log.e("CredentialsHelper", "Unexpected error: ${e.message}")
            null

        }

    }

}
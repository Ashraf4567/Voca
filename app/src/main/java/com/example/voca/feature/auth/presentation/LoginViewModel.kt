package com.example.voca.feature.auth.presentation

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voca.core.domain.util.onError
import com.example.voca.core.domain.util.onSuccess
import com.example.voca.feature.auth.data.UserMapper
import com.example.voca.feature.auth.domain.GoogleSignInUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val googleSignInUseCase: GoogleSignInUseCase,
    private val userMapper: UserMapper
) : ViewModel() {

    fun signInWithGoogle(
        activity: Activity
    ) {
        Log.d("LoginViewModel", "signInWithGoogle: Loading..")
        viewModelScope.launch {
            googleSignInUseCase(activity)
                .onSuccess {supabaseUser ->
                    val user = userMapper.mapToUser(supabaseUser)
                    Log.d("LoginViewModel", "signInWithGoogle Success: $user")
                }.onError {
                    Log.d("LoginViewModel", "signInWithGoogle Error: ${it.message}")
                }
        }
    }
}
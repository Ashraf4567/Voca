package com.example.voca

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voca.core.presentation.navigation.HomeScreen
import com.example.voca.core.presentation.navigation.LoginScreen
import com.example.voca.feature.auth.domain.AuthRepository
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _startDestination = MutableStateFlow<Any>(LoginScreen)
    val startDestination: StateFlow<Any> = _startDestination.asStateFlow()

    init {
        checkAuthenticationStatus()
    }

    private fun checkAuthenticationStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.checkAuthenticationStatus()
                .collect { status ->
                    if (status !is SessionStatus.Initializing){
                        _isLoading.update { false }
                    }
                when (status) {
                    is SessionStatus.Authenticated -> {
                        _startDestination.update { HomeScreen }
                    }
                    is SessionStatus.NotAuthenticated -> {
                        _startDestination.update { LoginScreen }

                    }
                    SessionStatus.Initializing -> {
                        _isLoading.update { true }
                    }
                    is SessionStatus.RefreshFailure -> {
                        _startDestination.update { LoginScreen }
                    }
                }
            }
        }
    }
}

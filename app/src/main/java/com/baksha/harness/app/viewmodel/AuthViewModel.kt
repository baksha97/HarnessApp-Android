package com.baksha.harness.harness.viewmodel

import androidx.lifecycle.ViewModel
import com.baksha.harness.harness.repository.LogEvent
import com.baksha.harness.harness.repository.LogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * This application does not have any authentication methods setup since it's a template.
 * Here is where you'd implement your concrete implementation.
 * For now, we'll stub something out.
 */
sealed class AuthState {
    data object Unauthenticated: AuthState()
    data class Authenticated(val name: String) : AuthState()
}

class AuthViewModel : ViewModel() {

    val state: StateFlow<AuthState>
        get() = _state

    private val _state: MutableStateFlow<AuthState>
        get() =  MutableStateFlow(AuthState.Authenticated(name = "Travis"))

    fun signOut() {
        _state.value = AuthState.Unauthenticated
    }
}

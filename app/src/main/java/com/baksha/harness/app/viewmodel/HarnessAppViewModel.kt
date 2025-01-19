package com.baksha.harness.harness.viewmodel

import androidx.lifecycle.ViewModel
import com.baksha.harness.harness.repository.LogEvent
import com.baksha.harness.harness.repository.LogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class HarnessAppEnvironment {
    data object QA : HarnessAppEnvironment()
    data object Production : HarnessAppEnvironment()

    val name: String
        get() = when (this) {
            Production -> "Prod"
            QA -> "QA"
        }
}

data class HarnessAppState(val environment: HarnessAppEnvironment) {
    companion object {
        val default: HarnessAppState = HarnessAppState(HarnessAppEnvironment.QA)
    }
}


class HarnessAppViewModel(
    private val logRepository: LogRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        HarnessAppState(environment = HarnessAppEnvironment.QA)
    )
    val state: StateFlow<HarnessAppState> = _state

    val logs: StateFlow<List<LogEvent>>
        get() = logRepository.logs

    fun activate(environment: HarnessAppEnvironment) {
        _state.value = HarnessAppState(environment = environment)
    }

    fun clear() {
        logRepository.clear()
    }
}

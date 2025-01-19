package com.baksha.sample.harness.viewmodel

import androidx.lifecycle.ViewModel
import com.baksha.sample.harness.repository.LogEvent
import com.baksha.sample.harness.repository.LogRepository
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

    val state: StateFlow<HarnessAppState>
        get() = _state


    private val _state: MutableStateFlow<HarnessAppState>
        get() =  MutableStateFlow(HarnessAppState.default)

    val logs: StateFlow<List<LogEvent>>
        get() = logRepository.logs

    fun activate(environment: HarnessAppEnvironment) {
        _state.value = _state.value.copy(
            environment = environment
        )
    }

    fun clear() {
        logRepository.clear()
    }
}

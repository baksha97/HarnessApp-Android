package com.baksha.sample.harness.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

data class LogEvent(val priority: Int, val tag: String, val message: String)

class LogRepository : Timber.DebugTree() {
    private val _logs = MutableStateFlow(mutableListOf<LogEvent>())
    val logs: StateFlow<List<LogEvent>> =
        _logs

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val logEvent = LogEvent(
            priority,
            tag ?: "<no tag>",
            message
        )
        _logs.value.add(logEvent)
    }

    fun clear() {
        _logs.value = mutableListOf()
    }
}

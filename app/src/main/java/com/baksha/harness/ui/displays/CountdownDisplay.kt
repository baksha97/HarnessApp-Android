package com.baksha.sample.ui.displays

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.baksha.sample.ui.theme.md_theme_dark_onSecondary
import com.baksha.sample.ui.theme.md_theme_light_tertiary
import kotlinx.coroutines.delay

private const val ONE_SECOND_IN_MILLIS: Long = 1000
private const val SECONDS_MODULO: Long = 60

@Composable
fun CountdownDisplay(initialSeconds: Long, modifier: Modifier = Modifier) {
    var remainingTime by remember { mutableStateOf(initialSeconds) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(ONE_SECOND_IN_MILLIS)
            remainingTime -= 1
        }
    }
    val textColor = remember(remainingTime) {
        derivedStateOf {
            val seconds = (remainingTime % SECONDS_MODULO).toInt()
            val isExpired = seconds < 0
            if (isExpired) md_theme_dark_onSecondary else md_theme_light_tertiary
        }
    }

    val countdown = remember(remainingTime) {
        derivedStateOf {
            val minutes = (remainingTime / SECONDS_MODULO).toInt()
            val seconds = (remainingTime % SECONDS_MODULO).toInt()
            val isExpired = seconds < 0
            val factor = if (isExpired) -1 else 1
            "%02d:%02d".format(minutes * factor, seconds * factor)
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = countdown.value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = textColor.value
        )
    }
}

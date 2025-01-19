package com.baksha.sample.harness.view.harness

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.baksha.sample.harness.repository.LogEvent
import com.baksha.sample.harness.viewmodel.HarnessAppViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LogViewerScreen(viewModel: HarnessAppViewModel = koinViewModel()) {
    val logs = viewModel.logs.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = viewModel::clear) {
            Text("Clear all")
        }
        LazyColumn(state = rememberLazyListState()) {
            items(logs.value.reversed()) { log ->
                LogEventDisplay(
                    uiModel = LogEventUiModel(log)
                )
            }
        }
    }
}

@Composable
private fun LogEventDisplay(uiModel: LogEventUiModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = uiModel.icon,
                    contentDescription = null,
                    tint = uiModel.color,
                    modifier = Modifier.size(24.dp)
                )
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        uiModel.title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(uiModel.message, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

private data class LogEventUiModel(private val event: LogEvent) {
    val title: String
        get() = event.tag

    val message: String
        get() = event.message

    val color: Color
        get() = when (event.priority) {
            Log.VERBOSE -> Color.Gray
            Log.DEBUG -> Color.Green
            Log.INFO -> Color.Blue
            Log.WARN -> Color.Yellow
            Log.ERROR -> Color.Red
            else -> Color.Black
        }.copy(alpha = 0.45f)

    val icon: ImageVector
        get() = when (event.priority) {
            Log.VERBOSE -> Icons.Default.Book
            Log.DEBUG -> Icons.Default.BugReport
            Log.INFO -> Icons.Default.Info
            Log.WARN -> Icons.Default.Warning
            Log.ERROR -> Icons.Default.Error
            else -> Icons.Default.AccountCircle
        }
}

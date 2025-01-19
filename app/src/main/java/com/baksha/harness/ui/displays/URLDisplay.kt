package com.baksha.harness.ui.displays

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.net.URL

@Composable
fun URLDisplay(
    url: URL,
    clipboard: ClipboardManager = LocalClipboardManager.current,
    context: Context = LocalContext.current
) {
    val scope = rememberCoroutineScope()
    fun Context.showToast(message: String, length: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, length).show()
    }

    fun copyToClipboard() = scope.launch {
        context.showToast("Copied url to clipboard")
        clipboard.setText(AnnotatedString(url.toExternalForm()))
    }
    Column(Modifier.padding(16.dp)) {
        Text(
            text = url.toString(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = ::copyToClipboard) {
            Text(text = "Copy")
        }
    }
}

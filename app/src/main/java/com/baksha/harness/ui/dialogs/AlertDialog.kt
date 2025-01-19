package com.baksha.harness.ui.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AlertDialog(title: String, onOkayTap: () -> Unit, message: String? = null) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = title)
        },
        text = {
            message?.let {
                Text(it)
            }
        },
        confirmButton = {
            Button(onOkayTap) {
                Text("Okay")
            }
        }
    )
}

@Composable
fun AlertDialogOnError(error: Throwable?, onOkayTap: () -> Unit) = error?.let {
    AlertDialog(
        title = it.javaClass.simpleName,
        message = it.message,
        onOkayTap = onOkayTap
    )
}

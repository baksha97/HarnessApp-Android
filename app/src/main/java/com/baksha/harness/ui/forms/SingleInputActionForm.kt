package com.baksha.harness.ui.forms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SingleInputForm(
    title: String,
    initialValue: String,
    onClickButton: (String) -> Unit
) {

    var field by rememberSaveable {
        mutableStateOf(initialValue)
    }

    Column(
        modifier = Modifier
            .padding(12.dp)
    ) {
        OutlinedTextField(
            value = field,
            onValueChange = { field = it },
            label = { Text(title) },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )

        Button(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            onClick = { onClickButton(field) }
        ) {
            Text(title)
        }
    }
}

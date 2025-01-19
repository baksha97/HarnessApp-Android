package com.baksha.harness.ui.forms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SingleActionForm(title: String, description: String, onClickButton: (Unit) -> Unit) {
    Column(
        modifier = Modifier
            .padding(12.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            style = MaterialTheme.typography.headlineLarge,
            text = title
        )
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            style = MaterialTheme.typography.bodyLarge,
            text = description
        )

        Button(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            onClick = { onClickButton(Unit) }
        ) {
            Text(title)
        }
    }
}

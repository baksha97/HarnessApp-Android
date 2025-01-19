package com.baksha.harness.harness.view.harness

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baksha.harness.harness.viewmodel.HarnessAppEnvironment
import com.baksha.harness.harness.viewmodel.HarnessAppViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EnvironmentDropdownMenu(
    viewModel: HarnessAppViewModel = koinViewModel(),
    primaryColor: Color = MaterialTheme.colorScheme.primary
) {
    var isExpanded by remember { mutableStateOf(false) }
    val environment =
        viewModel
            .state
            .collectAsState()
            .value
            .environment

    Box(
        modifier =
        Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        Surface(
            modifier = Modifier.padding(start = 16.dp),
            color = primaryColor,
            shape = RoundedCornerShape(percent = 50),
            tonalElevation = 8.dp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = environment.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                IconButton(onClick = { isExpanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
                }
            }
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            EnvironmentDropdownMenuItem(
                environment = HarnessAppEnvironment.QA,
                onActivateEnvironment = { isExpanded = false }
            )
            EnvironmentDropdownMenuItem(
                environment = HarnessAppEnvironment.Production,
                onActivateEnvironment = { isExpanded = false }
            )
        }
    }
}

@Composable
private fun EnvironmentDropdownMenuItem(
    environment: HarnessAppEnvironment,
    onActivateEnvironment: () -> Unit,
    viewModel: HarnessAppViewModel = koinViewModel()
) {
    val activeEnvironment =
        viewModel
            .state
            .collectAsState()
            .value
            .environment
    DropdownMenuItem(
        text = { Text(environment.name) },
        enabled = environment != activeEnvironment,
        onClick = {
            viewModel.activate(environment)
            onActivateEnvironment()
        }
    )
}

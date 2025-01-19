package com.baksha.sample.ui.screens

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.baksha.sample.ui.dialogs.AlertDialog
import com.baksha.sample.ui.displays.ResultDisplay
import com.baksha.sample.ui.displays.ResultSuccessContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

/**
 * Unfortunately, I could not find any existing frameworks to dynamically generate screens based on
 * inputs and outputs.
 *
 * This "Screen" allows you to leverage a simple scaffold for inputs and outputs for functional
 * testing. This allows you to display various "Displays" for input forms and output results.
 */

typealias FormAction<
    FormData,
    ActionResultType
    > = suspend (input: FormData) -> Result<ActionResultType>

typealias FormConsumer<
    FunctionalInputValue
    > = (FunctionalInputValue) -> Unit

typealias FormContent<
    FormData
    > = @Composable (consume: FormConsumer<FormData>) -> Unit

@Composable
fun <FormData : Any, ResultSuccess> FunctionalFormScreen(
    successContent: ResultSuccessContent<ResultSuccess>,
    title: String,
    formContent: FormContent<FormData>,
    action: FormAction<FormData, ResultSuccess>,
    alertMessage: String? = null,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    var uiModel: FunctionalScreenUiModel<ResultSuccess> by rememberSaveable {
        mutableStateOf(FunctionalScreenUiModel())
    }

    suspend fun onSubmit(input: FormData) {
        fun makeAlert(result: Result<ResultSuccess>): FunctionalScreenUiModel.Alert =
            FunctionalScreenUiModel.Alert(
                title = title,
                message = result.fold(
                    onSuccess = { alertMessage },
                    onFailure = { it.toString() }
                ),
                onAlertClose = {
                    uiModel = uiModel.copy(alert = null)
                }
            )
        uiModel = uiModel
            .copy(
                result = null,
                isLoading = true,
                alert = null
            )
        val result = action(input)
        val shouldShowAlerts: Boolean = alertMessage != null
        val alert = if (shouldShowAlerts) makeAlert(result) else null
        uiModel = uiModel.copy(
            result = result,
            isLoading = false,
            alert = alert
        )
    }

    uiModel.alert?.let { alert ->
        AlertDialog(
            title = alert.title,
            message = alert.message,
            onOkayTap = alert.onAlertClose
        )
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        formContent { data ->
            scope.launch {
                onSubmit(input = data)
            }
        }

        if (uiModel.isLoading) {
            CircularProgressIndicator()
        }

        uiModel.result?.let { result ->
            HorizontalDivider()
            Text(
                "$title Result:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            ResultDisplay(
                result = result,
                successContent = successContent
            )

            Button(onClick = { uiModel = uiModel.copy(result = null) }) {
                Text(text = "Clear last result")
            }
        }
    }
}

@Parcelize
private data class FunctionalScreenUiModel<FunctionalResultValue>(
    val result: Result<FunctionalResultValue>? = null,
    val isLoading: Boolean = false,
    val alert: Alert? = null
) : Parcelable {
    @Parcelize
    data class Alert(
        val title: String,
        val message: String? = null,
        val onAlertClose: () -> Unit
    ) : Parcelable
}

package com.baksha.sample.ui.displays

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

typealias ResultSuccessContent<
    ResultSuccess
    > = @Composable (model: ResultSuccess) -> Unit

@Composable
fun <ActionSuccessResultType> ResultDisplay(
    result: Result<ActionSuccessResultType>,
    successContent: ResultSuccessContent<ActionSuccessResultType>? = null
) = result.fold(
    onSuccess = {
        if (successContent != null) {
            successContent(it)
        } else {
            Text(text = it.toString())
        }
    },
    onFailure = {
        ErrorDisplay(error = it)
    }
)

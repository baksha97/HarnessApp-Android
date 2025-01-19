package com.baksha.sample.ui.screens

import android.annotation.SuppressLint
import android.webkit.WebSettings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewScreen(url: String) {
    val state = rememberWebViewState(url)
    Scaffold(
        topBar = {
            Row {
                TopAppBar(
                    title = {
                        Column {
                            if (state.isLoading) {
                                LinearProgressIndicator()
                            }
                            Text(
                                text = state.pageTitle ?: "Web View",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        titleContentColor = MaterialTheme.colorScheme.onSecondary
                    )
                )
            }
        },
        content = { padding ->
            WebView(
                state = state,
                onCreated = {
                    it.settings.javaScriptEnabled = true
                    it.settings.cacheMode = WebSettings.LOAD_NO_CACHE
                },
                modifier = Modifier.padding(padding)
            )
        }
    )
}

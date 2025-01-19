package com.baksha.harness.harness.navigation.drawer.item

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.baksha.harness.harness.view.harness.LogViewerScreen

@Composable
fun NavigationDrawerItem.Screen() = when (this) {
    is NavigationDrawerItem.DemoItem -> DemoScreen()
    is NavigationDrawerItem.FeatureAItem -> Text("$this")
    is NavigationDrawerItem.FeatureBItem -> Text("$this")
    is NavigationDrawerItem.DebugItem -> DebugScreen()
}

@Composable
fun NavigationDrawerItem.DemoItem.DemoScreen() = when (this) {
    is NavigationDrawerItem.DemoItem.FeatureA -> Text("$this")
    is NavigationDrawerItem.DemoItem.FeatureB -> Text("$this")
}

@Composable
fun NavigationDrawerItem.FeatureAItem.FeatureAUseCaseScreen() = when (this) {
    is NavigationDrawerItem.FeatureAItem.Func1 -> Text("$this")
    is NavigationDrawerItem.FeatureAItem.Func2 -> Text("$this")
}

@Composable
fun NavigationDrawerItem.FeatureBItem.FeatureBUseCaseScreen() = when (this) {
    is NavigationDrawerItem.FeatureBItem.Func1 -> Text("$this")
}

@Composable
fun NavigationDrawerItem.DebugItem.DebugScreen() = when (this) {
    is NavigationDrawerItem.DebugItem.LogViewer -> LogViewerScreen()
}

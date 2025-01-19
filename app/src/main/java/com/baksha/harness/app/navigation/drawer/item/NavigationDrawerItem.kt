package com.baksha.sample.harness.navigation.drawer.item

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.filled.WebStories
import androidx.compose.ui.graphics.vector.ImageVector
import timber.log.Timber

sealed class NavigationDrawerItem(
    val title: String,
    val icon: ImageVector,
    val priority: Int
) {

    sealed class DemoItem(
        title: String,
        icon: ImageVector,
        priority: Int
    ) : NavigationDrawerItem(title, icon, priority) {
        data object FeatureA : DemoItem(
            title = "Feature A Demo",
            icon = Icons.Filled.SupervisedUserCircle,
            priority = 1
        )

        data object FeatureB : DemoItem(
            title = "FeatureB Demo",
            icon = Icons.Default.Public,
            priority = 2
        )
    }

    sealed class FeatureAItem(
        title: String,
        icon: ImageVector,
        priority: Int
    ) : NavigationDrawerItem(title, icon, priority) {
        data object Func1 : FeatureAItem(
            title = "FeatureA Function 1",
            icon = Icons.Default.LockOpen,
            priority = 1
        )
        data object Func2 : FeatureAItem(
            title = "FeatureA Function 2",
            icon = Icons.Default.LockOpen,
            priority = 2
        )
    }

    sealed class FeatureBItem(
        title: String,
        icon: ImageVector,
        priority: Int
    ) : NavigationDrawerItem(title, icon, priority) {
        data object Func1 : FeatureBItem(
            title = "FeatureB Function 1",
            icon = Icons.Default.WebStories,
            priority = 1
        )
    }

    sealed class DebugItem(
        title: String,
        icon: ImageVector,
        priority: Int
    ) : NavigationDrawerItem(title, icon, priority) {
        data object LogViewer : DebugItem(
            title = "Log Viewer",
            icon = Icons.Default.BugReport,
            priority = 1
        )
    }

    companion object {
        val allDemoItems: List<DemoItem> = DemoItem::class
            .sealedSubclasses
            .mapNotNull { it.objectInstance }
            .sortedBy { it.priority }

        val allFeatureAItems: List<FeatureAItem> = (FeatureAItem::class)
            .sealedSubclasses
            .mapNotNull { it.objectInstance }
            .sortedBy { it.priority }

        val allFeatureBItems: List<FeatureBItem> = FeatureBItem::class
            .sealedSubclasses
            .mapNotNull { it.objectInstance }
            .sortedBy { it.priority }

        val allDebugItems: List<DebugItem> = DebugItem::class
            .sealedSubclasses
            .mapNotNull { it.objectInstance }
            .sortedBy { it.priority }

        val allItems: List<NavigationDrawerItem> =
            allDemoItems + allFeatureAItems + allFeatureBItems + allDebugItems

        fun fromRoute(route: String?, default: NavigationDrawerItem): NavigationDrawerItem =
            allItems.firstOrNull { it.title == route?.substringBefore("/") }
                ?: default.also { Timber.e("Unable to find item for [$route].") }
    }
}

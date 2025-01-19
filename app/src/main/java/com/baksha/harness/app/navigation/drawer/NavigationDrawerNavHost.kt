package com.baksha.harness.harness.navigation.drawer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.baksha.harness.harness.navigation.drawer.item.NavigationDrawerItem
import com.baksha.harness.harness.navigation.drawer.item.Screen

@Composable
fun NavigationDrawerNavHost(
    navController: NavHostController,
    startDestination: NavigationDrawerItem,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.title,
        modifier = modifier
    ) {
        NavigationDrawerItem.allItems.forEach { item ->
            composable(item.title) {
                item.Screen()
            }
        }
    }
}

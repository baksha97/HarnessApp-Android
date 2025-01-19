package com.baksha.harness.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baksha.harness.harness.navigation.NavigationTopAppBar
import com.baksha.harness.harness.navigation.drawer.NavigationDrawerContent
import com.baksha.harness.harness.navigation.drawer.NavigationDrawerNavHost
import com.baksha.harness.harness.navigation.drawer.item.NavigationDrawerItem
import com.baksha.harness.ui.theme.MaterialYouTheme
import kotlinx.coroutines.launch

@Composable
fun HarnessAppMain() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var selectedNavigationDrawerItem by remember {
        mutableStateOf(
            NavigationDrawerItem.fromRoute(
                navBackStackEntry?.destination?.route,
                default = NavigationDrawerItem.DemoItem.FeatureA
            )
        )
    }
    val coroutineScope = rememberCoroutineScope()
    MaterialYouTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            HarnessAppModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    NavigationDrawerContent(
                        selectedItem = selectedNavigationDrawerItem,
                        onItemSelect = {
                            selectedNavigationDrawerItem = it
                            navController.navigate(it.title) {
                                launchSingleTop = true
                                restoreState = true
                            }
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }
                    )
                },
                content = {
                    NavigationDrawerScaffold(
                        navController = navController,
                        selectedNavigationDrawerItem = selectedNavigationDrawerItem,
                        onNavIconClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
            )
        }
    }
}

@Composable
private fun HarnessAppModalNavigationDrawer(
    drawerState: DrawerState,
    drawerContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerTonalElevation = 8.dp,
                content = {
                    drawerContent()
                }
            )
        }
    ) {
        content()
    }
}

@Composable
private fun NavigationDrawerScaffold(
    navController: NavHostController,
    selectedNavigationDrawerItem: NavigationDrawerItem,
    onNavIconClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NavigationTopAppBar(
                title = selectedNavigationDrawerItem.title,
                onNavIconClick = onNavIconClick
            )
        }
    ) { padding ->
        NavigationDrawerNavHost(
            navController,
            startDestination = selectedNavigationDrawerItem,
            modifier = Modifier
                .padding(padding)
        )
    }
}

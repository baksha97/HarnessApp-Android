package com.baksha.harness.harness.navigation.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baksha.harness.harness.view.harness.EnvironmentDropdownMenu
import com.baksha.harness.harness.viewmodel.AuthState
import com.baksha.harness.harness.viewmodel.AuthViewModel
import com.baksha.harness.harness.navigation.drawer.item.NavigationDrawerItem
import com.baksha.harness.harness.navigation.drawer.item.NavigationDrawerItemDisplay
import com.baksha.harness.ui.resources.IDKLogo
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationDrawerContent(
    selectedItem: NavigationDrawerItem?,
    onItemSelect: (NavigationDrawerItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HarnessHeaderSection()
        HorizontalDivider()
        ActiveUserNavigationSection()
        HorizontalDivider()
        NavigableSection(
            title = "Demos",
            NavigationDrawerItem.allDemoItems,
            selectedItem = selectedItem,
            onItemSelect = onItemSelect
        )
        HorizontalDivider()
        NavigableSection(
            title = "FeatureA",
            NavigationDrawerItem.allFeatureAItems,
            selectedItem = selectedItem,
            onItemSelect = onItemSelect
        )
        HorizontalDivider()
        NavigableSection(
            title = "FeatureB",
            NavigationDrawerItem.allFeatureBItems,
            selectedItem = selectedItem,
            onItemSelect = onItemSelect
        )
        HorizontalDivider()
        NavigableSection(
            title = "Debug",
            NavigationDrawerItem.allDebugItems,
            selectedItem = selectedItem,
            onItemSelect = onItemSelect
        )
    }
}

@Composable
private fun HarnessHeaderSection() {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IDKLogo()
                Spacer(modifier = Modifier.weight(1.0f))
                EnvironmentDropdownMenu()
            }
        }
    }
}

@Composable
fun ActiveUserNavigationSection(viewModel: AuthViewModel = koinViewModel()) {
    val state = viewModel
        .state
        .collectAsState()
        .value
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            when(state) {
                is AuthState.Authenticated -> Text(
                    text = state.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                is AuthState.Unauthenticated -> Text(
                    text = "Unauthenticated",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun NavigableSection(
    title: String,
    items: List<NavigationDrawerItem>,
    selectedItem: NavigationDrawerItem?,
    onItemSelect: (NavigationDrawerItem) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
            modifier = Modifier.padding(8.dp)
        )

        items.forEach { item ->
            NavigationDrawerItemDisplay(
                item = item,
                isSelected = selectedItem == item,
                onClick = { onItemSelect(item) }
            )
        }
    }
}

package com.baksha.sample.ui.resources

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IDKLogo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier, // Add some padding if needed
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
//        Image(
//            painter = painterResource(R.drawable.logo_no_bg),
//            contentDescription = "IDK Logo",
//            colorFilter = ColorFilter.tint(
//                MaterialTheme.colorScheme.primary
//            ),
//            alignment = Alignment.Center
//        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun IDKLogoPreview() {
    IDKLogo()
}

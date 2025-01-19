package com.baksha.sample.harness.navigation.drawer.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val ROUNDED_CORNER_SHAPE_PERCENT = 50
private val NAVIGATION_ITEM_SHAPE = RoundedCornerShape(ROUNDED_CORNER_SHAPE_PERCENT)

@Composable
fun NavigationDrawerItemDisplay(
    item: NavigationDrawerItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val textColor: Color =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
    val backgroundColor: Color =
        if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f) else Color.Transparent

    Surface(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(4.dp),
        color = backgroundColor,
        shape = NAVIGATION_ITEM_SHAPE
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.title,
                color = textColor,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold
            )
        }
    }
}

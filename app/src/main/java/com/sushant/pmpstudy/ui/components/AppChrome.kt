package com.sushant.pmpstudy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class BadgeVariant { DEFAULT, SUCCESS, WARNING, ERROR, INFO }

@Composable
fun ScreenHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(bottom = 12.dp)) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Text(
            subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}

@Composable
fun StatRow(items: List<Pair<String, String>>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { (value, label) ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = 10.dp, vertical = 12.dp)
            ) {
                Text(value, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun KindBadge(
    label: String,
    modifier: Modifier = Modifier,
    variant: BadgeVariant = BadgeVariant.DEFAULT
) {
    val bg: Color
    val fg: Color
    when (variant) {
        BadgeVariant.SUCCESS -> {
            bg = MaterialTheme.colorScheme.secondary.copy(alpha = 0.18f)
            fg = MaterialTheme.colorScheme.secondary
        }
        BadgeVariant.WARNING -> {
            bg = MaterialTheme.colorScheme.primary.copy(alpha = 0.18f)
            fg = MaterialTheme.colorScheme.primary
        }
        BadgeVariant.ERROR -> {
            bg = MaterialTheme.colorScheme.error.copy(alpha = 0.18f)
            fg = MaterialTheme.colorScheme.error
        }
        BadgeVariant.INFO -> {
            bg = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.14f)
            fg = MaterialTheme.colorScheme.onSurfaceVariant
        }
        BadgeVariant.DEFAULT -> {
            bg = MaterialTheme.colorScheme.primary.copy(alpha = 0.18f)
            fg = MaterialTheme.colorScheme.primary
        }
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bg)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = fg)
    }
}

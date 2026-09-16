package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sushant.pmpstudy.domain.FontScale
import com.sushant.pmpstudy.domain.Settings
import com.sushant.pmpstudy.domain.ThemeMode
import com.sushant.pmpstudy.ui.components.ScreenHeader

private const val PREVIEW_TEXT =
    "All changes must go through Integrated Change Control — regardless of who " +
        "requests it or how minor it seems."

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            ScreenHeader(
                title = "Settings",
                subtitle = "Make the guide comfortable to read."
            )
        }

        item {
            SettingsCard(title = "Theme") {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ThemeMode.values().forEach { mode ->
                        FilterChip(
                            selected = Settings.themeMode == mode,
                            onClick = { Settings.setThemeMode(mode) },
                            label = {
                                Text(mode.label, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Text(
                    "\"System\" follows your device's light or dark setting.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }

        item {
            SettingsCard(title = "Text size") {
                FontScale.values().forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { Settings.setFontScale(option) }
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = Settings.fontScale == option,
                            onClick = { Settings.setFontScale(option) }
                        )
                        Column(modifier = Modifier.padding(start = 4.dp)) {
                            Text(option.label, style = MaterialTheme.typography.bodyMedium)
                            // Absolute sp (not the themed style) so each row previews
                            // its own size rather than the currently applied one.
                            Text(
                                "Aa — sample text",
                                fontSize = 14.sp * option.scale,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        item {
            SettingsCard(title = "Preview") {
                Text(PREVIEW_TEXT, style = MaterialTheme.typography.bodyMedium)
                Text(
                    "This paragraph uses your current settings.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        item {
            Text(
                "PMP® Prep Guide · Independent study resource. Not affiliated with, " +
                    "endorsed by, or sponsored by PMI.",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )
        }
    }
}

@Composable
private fun SettingsCard(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(Modifier.padding(14.dp)) {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            content()
        }
    }
}

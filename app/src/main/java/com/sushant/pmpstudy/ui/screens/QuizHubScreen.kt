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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.StudyRepository
import com.sushant.pmpstudy.domain.AppState
import com.sushant.pmpstudy.ui.components.BadgeVariant
import com.sushant.pmpstudy.ui.components.KindBadge
import com.sushant.pmpstudy.ui.components.ScreenHeader

@Composable
fun QuizHubScreen(onOpenPack: (String) -> Unit) {
    val packs = StudyRepository.quizPacks
    LazyColumn(
        modifier       = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            ScreenHeader(
                title    = "Practice quizzes",
                subtitle = "${StudyRepository.allQuestions.size} questions · Start with the mixed set, then drill by chapter."
            )
        }
        items(packs, key = { it.id }) { pack ->
            val featured    = pack.id == "mixed"
            val lastResult  = AppState.lastResult(pack.id)
            Card(
                modifier  = Modifier.fillMaxWidth().clickable { onOpenPack(pack.id) },
                colors    = CardDefaults.cardColors(
                    containerColor = if (featured)
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.14f)
                    else
                        MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = if (featured) 3.dp else 1.dp)
            ) {
                Row(
                    modifier          = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        if (featured) {
                            KindBadge(
                                label    = "⭐  START HERE",
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                        Text(pack.title, style = MaterialTheme.typography.titleMedium)
                        Text(
                            pack.subtitle,
                            style    = MaterialTheme.typography.bodySmall,
                            color    = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        // Quiz history: show last attempt score
                        if (lastResult != null) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                modifier = Modifier.padding(top = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                KindBadge(
                                    label   = "Last: ${lastResult.percent}%",
                                    variant = when {
                                        lastResult.percent >= 80 -> BadgeVariant.SUCCESS
                                        lastResult.percent >= 60 -> BadgeVariant.WARNING
                                        else                     -> BadgeVariant.ERROR
                                    }
                                )
                                Text(
                                    "${lastResult.correct}/${lastResult.total} · ${lastResult.date}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                    Icon(
                        Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = "Open",
                        tint               = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

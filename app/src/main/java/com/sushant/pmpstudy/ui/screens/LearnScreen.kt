package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.Chapter
import com.sushant.pmpstudy.data.StudyRepository
import com.sushant.pmpstudy.domain.AppState
import com.sushant.pmpstudy.ui.components.KindBadge
import com.sushant.pmpstudy.ui.components.ScreenHeader
import com.sushant.pmpstudy.ui.components.StatRow

@Composable
fun LearnScreen(onOpenChapter: (String) -> Unit) {
    val context = LocalContext.current
    val versionName = remember {
        runCatching {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
        }.getOrNull() ?: "2.6.3"
    }
    var query by rememberSaveable { mutableStateOf("") }
    val filtered = if (query.isBlank()) {
        StudyRepository.chapters
    } else {
        val q = query.lowercase().trim()
        StudyRepository.chapters.filter { it.matches(q) }
    }
    val grouped    = filtered.groupBy { it.category }
    val quizCount  = StudyRepository.allQuestions.size
    val totalChaps = StudyRepository.chapters.size
    val readCount  = AppState.readChapterIds.size

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = remember { LazyListState() },
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            ScreenHeader(
                title    = "PMP® Prep Guide",
                subtitle = "By Sushant Kulkarni · 2026 ECO Edition"
            )
            StatRow(
                items = listOf(
                    "$readCount/$totalChaps" to "Read",
                    quizCount.toString()     to "Questions",
                    "v$versionName"          to "Build"
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value         = query,
                onValueChange = { query = it },
                modifier      = Modifier.fillMaxWidth(),
                singleLine    = true,
                label         = { Text("Search chapters & notes") },
                leadingIcon   = { Icon(Icons.Outlined.Search, contentDescription = null) }
            )
        }

        if (filtered.isEmpty()) {
            item {
                Text(
                    "No chapters match \"$query\". Try a topic like EVM, Scrum, or ethics.",
                    style    = MaterialTheme.typography.bodyMedium,
                    color    = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        grouped.forEach { (category, chapters) ->
            item(key = "h-$category") {
                Text(
                    category.uppercase(),
                    style    = MaterialTheme.typography.labelSmall,
                    color    = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                )
            }
            items(chapters, key = { it.id }) { chapter ->
                val n       = StudyRepository.questionsForChapter(chapter.id).size
                val isRead  = AppState.isRead(chapter.id)
                Card(
                    modifier  = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenChapter(chapter.id) },
                    colors    = CardDefaults.cardColors(
                        containerColor = if (isRead)
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.08f)
                        else
                            MaterialTheme.colorScheme.surfaceVariant
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier          = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text(chapter.title, style = MaterialTheme.typography.titleMedium)
                            Text(
                                chapter.subtitle,
                                style    = MaterialTheme.typography.bodySmall,
                                color    = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 3.dp),
                                maxLines = 2
                            )
                            if (n > 0) {
                                Row(modifier = Modifier.padding(top = 8.dp)) {
                                    KindBadge("$n questions")
                                }
                            }
                        }
                        if (isRead) {
                            Icon(
                                Icons.Outlined.CheckCircle,
                                contentDescription = "Read",
                                tint     = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(20.dp).padding(start = 4.dp)
                            )
                        } else {
                            Icon(
                                Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                                contentDescription = "Open",
                                tint     = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// ── Helpers ──────────────────────────────────────────────────────────────────

private fun Chapter.matches(q: String): Boolean {
    if (title.lowercase().contains(q) ||
        subtitle.lowercase().contains(q) ||
        category.lowercase().contains(q)) return true
    return sections.any { section ->
        section.heading.lowercase().contains(q) ||
            section.body.lowercase().contains(q) ||
            section.tableHeaders.any { it.lowercase().contains(q) } ||
            section.tableRows.any { row -> row.any { it.lowercase().contains(q) } }
    }
}



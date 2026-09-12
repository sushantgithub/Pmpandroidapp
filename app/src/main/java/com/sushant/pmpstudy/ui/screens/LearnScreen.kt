package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.StudyRepository

@Composable
fun LearnScreen(onOpenChapter: (String) -> Unit) {
    val versionName = LocalContext.current.packageManager
        .getPackageInfo(LocalContext.current.packageName, 0).versionName
    var query by rememberSaveable { mutableStateOf("") }
    val filtered = if (query.isBlank()) {
        StudyRepository.chapters
    } else {
        val q = query.lowercase()
        StudyRepository.chapters.filter { chapter ->
            val haystack = (chapter.title + chapter.subtitle + chapter.category +
                chapter.sections.joinToString { it.body }).lowercase()
            haystack.contains(q)
        }
    }
    val grouped = filtered.groupBy { it.category }
    val quizCount = StudyRepository.allQuestions.size

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Text("PMP® Prep Guide", style = MaterialTheme.typography.headlineMedium)
            Text(
                "By Sushant Kulkarni · 2026 ECO Edition",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                "${StudyRepository.chapters.size} chapters · $quizCount practice questions · v$versionName · PMBOK 8 aligned. Not affiliated with PMI.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
            )
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Search notes") }
            )
        }
        grouped.forEach { (category, chapters) ->
            item(key = "h-$category") {
                Text(
                    category,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 12.dp, bottom = 2.dp)
                )
            }
            items(chapters, key = { it.id }) { chapter ->
                val n = StudyRepository.questionsForChapter(chapter.id).size
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onOpenChapter(chapter.id) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(chapter.title, style = MaterialTheme.typography.titleMedium)
                        Text(
                            chapter.subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        if (n > 0) {
                            Text(
                                "$n section quiz items",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

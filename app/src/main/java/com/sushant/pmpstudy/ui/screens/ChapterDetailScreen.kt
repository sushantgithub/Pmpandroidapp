package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.StudyRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterDetailScreen(chapterId: String, onBack: () -> Unit) {
    val chapter = StudyRepository.chapters.firstOrNull { it.id == chapterId }
    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(chapter?.title ?: "Chapter") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back")
                }
            }
        )
        if (chapter == null) {
            Text("Chapter not found.", modifier = Modifier.padding(16.dp))
            return
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(chapter.sections, key = { it.heading }) { section ->
                Text(section.heading, style = MaterialTheme.typography.titleMedium)
                Text(
                    section.body,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }
    }
}

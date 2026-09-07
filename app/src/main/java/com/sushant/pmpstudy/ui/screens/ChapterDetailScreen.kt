package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.SectionKind
import com.sushant.pmpstudy.data.StudyRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterDetailScreen(
    chapterId: String,
    onBack: () -> Unit,
    onQuiz: () -> Unit
) {
    val chapter = StudyRepository.chapter(chapterId)
    val quizCount = StudyRepository.questionsForChapter(chapterId).size
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(chapter.subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            items(chapter.sections, key = { it.heading }) { section ->
                val colors = when (section.kind) {
                    SectionKind.BODY -> MaterialTheme.colorScheme.surfaceVariant to MaterialTheme.colorScheme.onSurface
                    SectionKind.NOTE -> Color(0xFF1E3A6E) to Color(0xFFD6E4FF)
                    SectionKind.TIP -> Color(0xFF134E2E) to Color(0xFFD1FAE5)
                    SectionKind.WARN -> Color(0xFF78350F) to Color(0xFFFEF3C7)
                    SectionKind.DANGER -> Color(0xFF7F1D1D) to Color(0xFFFECACA)
                }
                Card(
                    colors = CardDefaults.cardColors(containerColor = colors.first),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(14.dp)) {
                        Text(section.heading, style = MaterialTheme.typography.titleSmall, color = colors.second)
                        Text(
                            section.body,
                            style = MaterialTheme.typography.bodyMedium,
                            color = colors.second,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                }
            }
            if (quizCount > 0) {
                item {
                    Button(onClick = onQuiz, modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                        Text("Quiz this chapter ($quizCount)")
                    }
                }
            }
        }
    }
}

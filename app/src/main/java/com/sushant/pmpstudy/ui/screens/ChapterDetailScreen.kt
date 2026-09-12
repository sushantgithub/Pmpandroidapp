package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.SectionKind
import com.sushant.pmpstudy.data.StudyRepository
import com.sushant.pmpstudy.ui.components.KindBadge
import com.sushant.pmpstudy.ui.components.StudyContentView
import com.sushant.pmpstudy.ui.theme.CalloutDanger
import com.sushant.pmpstudy.ui.theme.CalloutKey
import com.sushant.pmpstudy.ui.theme.CalloutNote
import com.sushant.pmpstudy.ui.theme.CalloutTip
import com.sushant.pmpstudy.ui.theme.CalloutWarn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterDetailScreen(
    chapterId: String,
    onBack: () -> Unit,
    onQuiz: () -> Unit
) {
    val chapter = StudyRepository.chapter(chapterId)
    val quizCount = StudyRepository.questionsForChapter(chapterId).size
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        chapter?.title ?: "Chapter",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        bottomBar = {
            if (quizCount > 0 && chapter != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp)
                ) {
                    Button(onClick = onQuiz, modifier = Modifier.fillMaxWidth()) {
                        Text("Practice $quizCount questions")
                    }
                }
            }
        }
    ) { inner ->
        if (chapter == null) {
            Text("Chapter not found.", modifier = Modifier.padding(16.dp))
            return@Scaffold
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(inner),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    chapter.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    chapter.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
            itemsIndexed(chapter.sections, key = { index, _ -> "${chapter.id}-$index" }) { _, section ->
                val (bg, fg, accent, badge) = when (section.kind) {
                    SectionKind.BODY -> Quad(
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.colorScheme.onSurface,
                        MaterialTheme.colorScheme.primary,
                        null
                    )
                    SectionKind.NOTE -> Quad(CalloutNote, Color(0xFFD6E4FF), Color(0xFF7EB6FF), "NOTE")
                    SectionKind.TIP -> Quad(CalloutTip, Color(0xFFD1FAE5), Color(0xFF3DDC97), "TIP")
                    SectionKind.WARN -> Quad(CalloutWarn, Color(0xFFFEF3C7), Color(0xFFE8C547), "WATCH")
                    SectionKind.DANGER -> Quad(CalloutDanger, Color(0xFFFECACA), Color(0xFFFF8A8A), "EXAM TRAP")
                    SectionKind.KEY -> Quad(CalloutKey, Color(0xFFEDE9FE), Color(0xFFC4B5FD), "KEY")
                }
                Card(
                    colors = CardDefaults.cardColors(containerColor = bg),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .fillMaxHeight()
                                .background(accent)
                        )
                        Column(Modifier.padding(14.dp).weight(1f)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                if (badge != null) KindBadge(badge)
                            }
                            Text(
                                section.heading,
                                style = MaterialTheme.typography.titleSmall,
                                color = fg,
                                modifier = Modifier.padding(top = if (badge != null) 8.dp else 0.dp)
                            )
                            StudyContentView(section = section, textColor = fg)
                        }
                    }
                }
            }
        }
    }
}

private data class Quad(
    val bg: Color,
    val fg: Color,
    val accent: Color,
    val badge: String?
)

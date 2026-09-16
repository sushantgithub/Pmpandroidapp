package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.SectionKind
import com.sushant.pmpstudy.data.StudyRepository
import com.sushant.pmpstudy.ui.components.KindBadge
import com.sushant.pmpstudy.ui.components.StudyContentView
import com.sushant.pmpstudy.ui.theme.CalloutStyle
import com.sushant.pmpstudy.ui.theme.LocalCalloutPalette
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterDetailScreen(
    chapterId: String,
    onBack: () -> Unit,
    onQuiz: () -> Unit
) {
    val chapter = StudyRepository.chapter(chapterId)
    val quizCount = StudyRepository.questionCount(chapterId)
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val toc = remember(chapter) {
        chapter?.sections
            ?.mapIndexed { index, section -> section.heading to index }
            ?.distinctBy { it.first }
            ?.take(12)
            .orEmpty()
    }
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
            Text("Chapter not found.", modifier = Modifier.padding(inner).padding(16.dp))
            return@Scaffold
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(inner),
            state = listState,
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
                    "${chapter.category} · ${chapter.sections.size} notes",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
            if (toc.size > 3) {
                item {
                    Text(
                        "Jump to",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        itemsIndexed(toc, key = { i, pair -> "${pair.second}-$i" }) { _, pair ->
                            FilterChip(
                                selected = false,
                                onClick = {
                                    scope.launch {
                                        listState.animateScrollToItem(pair.second + 2)
                                    }
                                },
                                label = {
                                    Text(
                                        pair.first,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            )
                        }
                    }
                }
            }
            itemsIndexed(chapter.sections, key = { index, _ -> "${chapter.id}-$index" }) { index, section ->
                val callouts = LocalCalloutPalette.current
                val (bg, fg, accent, badge) = when (section.kind) {
                    SectionKind.BODY -> Quad(
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.colorScheme.onSurface,
                        MaterialTheme.colorScheme.primary,
                        null
                    )
                    SectionKind.NOTE -> callouts.note.quad("NOTE")
                    SectionKind.TIP -> callouts.tip.quad("TIP")
                    SectionKind.WARN -> callouts.warn.quad("WATCH")
                    SectionKind.DANGER -> callouts.danger.quad("EXAM TRAP")
                    SectionKind.KEY -> callouts.key.quad("KEY")
                }
                Card(
                    colors = CardDefaults.cardColors(containerColor = bg),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                                .width(4.dp)
                                .height(48.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(accent)
                        )
                        Column(Modifier.padding(14.dp).weight(1f)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                if (badge != null) KindBadge(badge)
                            }
                            // A body card and its follow-up callout share one heading in
                            // the catalog. Printing it on both reads as the title stuttering,
                            // so the repeat is dropped and the badge carries the context.
                            val repeatsHeading =
                                index > 0 && chapter.sections[index - 1].heading == section.heading
                            if (!repeatsHeading) {
                                Text(
                                    section.heading,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = fg,
                                    modifier = Modifier.padding(top = if (badge != null) 8.dp else 0.dp)
                                )
                            }
                            StudyContentView(section = section, textColor = fg)
                        }
                    }
                }
            }
        }
    }
}

private fun CalloutStyle.quad(badge: String) = Quad(bg, fg, accent, badge)

private data class Quad(
    val bg: Color,
    val fg: Color,
    val accent: Color,
    val badge: String?
)

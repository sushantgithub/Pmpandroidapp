package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.SectionKind
import com.sushant.pmpstudy.data.StudyRepository
import com.sushant.pmpstudy.domain.AppState
import com.sushant.pmpstudy.ui.components.KindBadge
import com.sushant.pmpstudy.ui.components.StudyContentView
import com.sushant.pmpstudy.ui.theme.calloutColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterDetailScreen(
    chapterId      : String,
    initialSection : Int = -1,
    onBack         : () -> Unit,
    onQuiz         : () -> Unit
) {
    val chapter    = StudyRepository.chapter(chapterId)
    val quizCount  = StudyRepository.questionsForChapter(chapterId).size
    val listState  = remember { LazyListState() }
    val scope      = rememberCoroutineScope()
    // Scroll to bookmarked section on open (header=0, toc=1 if present, sections start at offset)
    val hasToc = (chapter?.sections?.size ?: 0) > 3
    LaunchedEffect(initialSection) {
        if (initialSection >= 0) {
            delay(300L) // wait for LazyColumn to lay out before scrolling
            val offset = if (hasToc) 2 else 1
            listState.animateScrollToItem(initialSection + offset)
        }
    }
    val dark       = isSystemInDarkTheme()
    val callouts   = calloutColors(dark)
    val isRead     = AppState.isRead(chapterId)

    // ── Reading progress ──────────────────────────────────────────────────
    val totalItems = 1 + // header item
        (if ((chapter?.sections?.size ?: 0) > 3) 1 else 0) + // toc item
        (chapter?.sections?.size ?: 0)

    val readingProgress by remember(listState) {
        derivedStateOf {
            if (totalItems <= 1) 1f
            else (listState.firstVisibleItemIndex.toFloat() / (totalItems - 1)).coerceIn(0f, 1f)
        }
    }

    // Auto-mark as read when scrolled past 80%
    if (readingProgress >= 0.80f && chapter != null) {
        AppState.markRead(chapterId)
    }

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
                actions = {
                    if (isRead) {
                        Icon(
                            Icons.Outlined.CheckCircle,
                            contentDescription = "Marked as read",
                            tint     = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(end = 12.dp).size(20.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor    = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        bottomBar = {
            if (chapter != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Reading progress bar above bottom buttons
                    LinearProgressIndicator(
                        progress     = { readingProgress },
                        modifier     = Modifier.fillMaxWidth(),
                        color        = MaterialTheme.colorScheme.secondary,
                        trackColor   = MaterialTheme.colorScheme.surfaceVariant
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        // Mark read / unread toggle
                        OutlinedButton(
                            onClick  = {
                                if (isRead) AppState.markUnread(chapterId)
                                else AppState.markRead(chapterId)
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(if (isRead) "Mark unread" else "Mark as read")
                        }
                        if (quizCount > 0) {
                            Button(
                                onClick  = onQuiz,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Practice $quizCount Qs")
                            }
                        }
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
            modifier            = Modifier.fillMaxSize().padding(inner),
            state               = listState,
            contentPadding      = PaddingValues(16.dp),
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
                    style    = MaterialTheme.typography.labelSmall,
                    color    = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            // Table of contents chips
            if (toc.size > 3) {
                item {
                    Text(
                        "Jump to",
                        style    = MaterialTheme.typography.labelSmall,
                        color    = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        itemsIndexed(toc, key = { i, pair -> "${pair.second}-$i" }) { _, pair ->
                            FilterChip(
                                selected = false,
                                onClick  = {
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

            itemsIndexed(chapter.sections, key = { idx, _ -> "${chapter.id}-$idx" }) { sectionIdx, section ->
                val bookmarked = AppState.isBookmarked(chapterId, sectionIdx)

                val (bg, fg, accent, badge) = when (section.kind) {
                    SectionKind.BODY   -> Quad(
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.colorScheme.onSurface,
                        MaterialTheme.colorScheme.primary,
                        null
                    )
                    SectionKind.NOTE   -> Quad(callouts.note,   if (dark) Color(0xFFD6E4FF) else Color(0xFF1A3A6E), Color(0xFF7EB6FF), "NOTE")
                    SectionKind.TIP    -> Quad(callouts.tip,    if (dark) Color(0xFFD1FAE5) else Color(0xFF155742), Color(0xFF3DDC97), "TIP")
                    SectionKind.WARN   -> Quad(callouts.warn,   if (dark) Color(0xFFFEF3C7) else Color(0xFF6B4200), Color(0xFFE8C547), "WATCH")
                    SectionKind.DANGER -> Quad(callouts.danger, if (dark) Color(0xFFFECACA) else Color(0xFF7A1E2A), Color(0xFFFF8A8A), "EXAM TRAP")
                    SectionKind.KEY    -> Quad(callouts.key,    if (dark) Color(0xFFEDE9FE) else Color(0xFF3B2A6B), Color(0xFFC4B5FD), "KEY")
                }
                Card(
                    colors    = CardDefaults.cardColors(containerColor = bg),
                    modifier  = Modifier.fillMaxWidth(),
                    shape     = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
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
                            Row(
                                modifier          = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(Modifier.weight(1f)) {
                                    if (badge != null) {
                                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                            KindBadge(badge)
                                        }
                                    }
                                    Text(
                                        section.heading,
                                        style    = MaterialTheme.typography.titleSmall,
                                        color    = fg,
                                        modifier = Modifier.padding(top = if (badge != null) 8.dp else 0.dp)
                                    )
                                }
                                // Bookmark toggle icon
                                IconButton(
                                    onClick  = { AppState.toggleBookmark(chapterId, sectionIdx) },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector        = if (bookmarked) Icons.Outlined.Bookmark
                                                             else Icons.Outlined.BookmarkBorder,
                                        contentDescription = if (bookmarked) "Remove bookmark" else "Bookmark",
                                        tint               = if (bookmarked) MaterialTheme.colorScheme.primary
                                                             else MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier           = Modifier.size(18.dp)
                                    )
                                }
                            }
                            StudyContentView(section = section, textColor = fg)
                        }
                    }
                }
            }
        }
    }
}

private data class Quad(
    val bg     : Color,
    val fg     : Color,
    val accent : Color,
    val badge  : String?
)

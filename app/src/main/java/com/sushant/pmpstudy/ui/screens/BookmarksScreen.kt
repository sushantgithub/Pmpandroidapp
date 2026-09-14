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
import androidx.compose.material.icons.outlined.BookmarkBorder
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
import com.sushant.pmpstudy.ui.components.KindBadge
import com.sushant.pmpstudy.ui.components.ScreenHeader

private data class BookmarkItem(
    val chapterId: String,
    val chapterTitle: String,
    val sectionIdx: Int,
    val heading: String,
    val body: String
)

@Composable
fun BookmarksScreen(onOpenChapter: (String) -> Unit) {
    // Build list from AppState bookmarks + repository data
    val items: List<BookmarkItem> = AppState.bookmarkedKeys.mapNotNull { key ->
        val parts = key.split("::")
        if (parts.size != 2) return@mapNotNull null
        val chapterId = parts[0]
        val sectionIdx = parts[1].toIntOrNull() ?: return@mapNotNull null
        val chapter = StudyRepository.chapter(chapterId) ?: return@mapNotNull null
        val section = chapter.sections.getOrNull(sectionIdx) ?: return@mapNotNull null
        BookmarkItem(
            chapterId = chapterId,
            chapterTitle = chapter.title,
            sectionIdx = sectionIdx,
            heading = section.heading,
            body = section.body.take(120).let { if (section.body.length > 120) "$it…" else it }
        )
    }.sortedBy { it.chapterTitle }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            ScreenHeader(
                title = "Bookmarks",
                subtitle = if (items.isEmpty()) "Tap the bookmark icon on any section to save it here."
                else "${items.size} saved section${if (items.size == 1) "" else "s"}"
            )
        }

        if (items.isEmpty()) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            Icons.Outlined.BookmarkBorder,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            "Open any chapter and tap the bookmark icon on a section you want to review later.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        items(items, key = { "${it.chapterId}::${it.sectionIdx}" }) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenChapter(item.chapterId) },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        KindBadge(
                            label = item.chapterTitle,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(item.heading, style = MaterialTheme.typography.titleSmall)
                        if (item.body.isNotBlank()) {
                            Text(
                                item.body,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 4.dp),
                                maxLines = 2
                            )
                        }
                    }
                    Icon(
                        Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = "Open chapter",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

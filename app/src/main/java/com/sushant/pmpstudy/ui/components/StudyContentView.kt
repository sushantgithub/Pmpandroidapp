package com.sushant.pmpstudy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.Section
import com.sushant.pmpstudy.domain.ContentBlock
import com.sushant.pmpstudy.domain.StudyContentParser

@Composable
fun StudyContentView(
    section: Section,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(top = 6.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        if (section.hasTable) {
            StudyTable(
                headers = section.tableHeaders,
                rows = section.tableRows,
                textColor = textColor
            )
        }
        if (section.body.isNotBlank()) {
            val blocks = StudyContentParser.parse(section.body)
            blocks.forEach { block ->
                when (block) {
                    is ContentBlock.Paragraph -> Text(
                        text = block.text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = textColor
                    )
                    is ContentBlock.BulletList -> Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        block.items.forEach { item ->
                            Row {
                                Text("• ", style = MaterialTheme.typography.bodyMedium, color = textColor)
                                Text(
                                    text = item,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = textColor,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                    is ContentBlock.Table -> StudyTable(
                        headers = block.headers,
                        rows = block.rows,
                        textColor = textColor
                    )
                }
            }
        }
    }
}

@Composable
private fun StudyTable(
    headers: List<String>,
    rows: List<List<String>>,
    textColor: Color
) {
    val colCount = maxOf(headers.size, rows.maxOfOrNull { it.size } ?: 0).coerceAtLeast(1)
    val borderColor = textColor.copy(alpha = 0.22f)
    val headerBg = textColor.copy(alpha = 0.14f)
    val rowBg = textColor.copy(alpha = 0.05f)
    val shape = RoundedCornerShape(8.dp)

    when {
        colCount == 2 -> KeyValueTable(rows, textColor, borderColor, rowBg, shape)
        colCount <= 4 -> CompactRowTable(headers, rows, colCount, textColor, borderColor, headerBg, rowBg, shape)
        else -> WrappedGridTable(headers, rows, colCount, textColor, borderColor, headerBg, rowBg, shape)
    }
}

/** Two-column tables: label on top, value below — no horizontal scroll. */
@Composable
private fun KeyValueTable(
    rows: List<List<String>>,
    textColor: Color,
    borderColor: Color,
    rowBg: Color,
    shape: RoundedCornerShape
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .border(1.dp, borderColor, shape)
    ) {
        rows.forEachIndexed { index, row ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (index % 2 == 0) rowBg else Color.Transparent)
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Text(
                    text = row.getOrElse(0) { "" },
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = textColor
                )
                Text(
                    text = row.getOrElse(1) { "" },
                    style = MaterialTheme.typography.bodySmall,
                    color = textColor.copy(alpha = 0.92f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            if (index < rows.lastIndex) {
                HorizontalDivider(color = borderColor, thickness = 0.5.dp)
            }
        }
    }
}

/** 3–4 column tables: each row is a card with labeled fields. */
@Composable
private fun CompactRowTable(
    headers: List<String>,
    rows: List<List<String>>,
    colCount: Int,
    textColor: Color,
    borderColor: Color,
    headerBg: Color,
    rowBg: Color,
    shape: RoundedCornerShape
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        rows.forEachIndexed { index, row ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .border(1.dp, borderColor, shape)
                    .background(if (index % 2 == 0) rowBg else Color.Transparent)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val title = row.firstOrNull()?.takeIf { it.isNotBlank() }
                if (title != null && colCount > 2) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = textColor
                    )
                }
                val dataStart = if (colCount > 2) 1 else 0
                val chunks = row.drop(dataStart)
                chunks.forEachIndexed { i, value ->
                    if (value.isBlank()) return@forEachIndexed
                    val headerIndex = dataStart + i
                    val label = headers.getOrElse(headerIndex) { "" }
                    if (label.isNotBlank()) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                            color = textColor.copy(alpha = 0.75f)
                        )
                    }
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodySmall,
                        color = textColor,
                        modifier = Modifier.padding(bottom = if (i < chunks.lastIndex) 2.dp else 0.dp)
                    )
                }
            }
        }
    }
}

/** Wide tables: equal-weight columns that wrap within screen width. */
@Composable
private fun WrappedGridTable(
    headers: List<String>,
    rows: List<List<String>>,
    colCount: Int,
    textColor: Color,
    borderColor: Color,
    headerBg: Color,
    rowBg: Color,
    shape: RoundedCornerShape
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .border(1.dp, borderColor, shape)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerBg)
                .padding(vertical = 4.dp)
        ) {
            repeat(colCount) { col ->
                TableCell(
                    text = headers.getOrElse(col) { "" },
                    textColor = textColor,
                    bold = true,
                    weight = 1f
                )
            }
        }
        HorizontalDivider(color = borderColor, thickness = 0.5.dp)
        rows.forEachIndexed { index, row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (index % 2 == 0) rowBg else Color.Transparent)
                    .padding(vertical = 2.dp)
            ) {
                repeat(colCount) { col ->
                    TableCell(
                        text = row.getOrElse(col) { "" },
                        textColor = textColor,
                        bold = false,
                        weight = 1f
                    )
                }
            }
            if (index < rows.lastIndex) {
                HorizontalDivider(color = borderColor, thickness = 0.5.dp)
            }
        }
    }
}

@Composable
private fun RowScope.TableCell(
    text: String,
    textColor: Color,
    bold: Boolean,
    weight: Float
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall.copy(
            fontWeight = if (bold) FontWeight.SemiBold else FontWeight.Normal,
            lineHeight = MaterialTheme.typography.bodySmall.lineHeight
        ),
        color = textColor,
        modifier = Modifier
            .weight(weight)
            .padding(horizontal = 8.dp, vertical = 8.dp)
    )
}

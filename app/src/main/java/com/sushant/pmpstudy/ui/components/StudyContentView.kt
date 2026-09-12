package com.sushant.pmpstudy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.domain.ContentBlock
import com.sushant.pmpstudy.domain.StudyContentParser

@Composable
fun StudyContentView(
    body: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    val blocks = StudyContentParser.parse(body)
    Column(modifier = modifier.padding(top = 6.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
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

@Composable
private fun StudyTable(
    headers: List<String>,
    rows: List<List<String>>,
    textColor: Color
) {
    val colCount = maxOf(headers.size, rows.maxOfOrNull { it.size } ?: 0).coerceAtLeast(1)
    val headerBg = textColor.copy(alpha = 0.15f)
    val borderColor = textColor.copy(alpha = 0.25f)
    val evenRowBg = textColor.copy(alpha = 0.06f)
    val shape = RoundedCornerShape(8.dp)

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .clip(shape)
            .border(1.dp, borderColor, shape)
    ) {
        Row(modifier = Modifier.background(headerBg)) {
            for (col in 0 until colCount) {
                TableCell(
                    text = headers.getOrElse(col) { "" },
                    textColor = textColor,
                    bold = true,
                    minWidth = columnWidth(colCount)
                )
            }
        }
        rows.forEachIndexed { index, row ->
            Row(
                modifier = if (index % 2 == 0) Modifier.background(evenRowBg) else Modifier
            ) {
                for (col in 0 until colCount) {
                    TableCell(
                        text = row.getOrElse(col) { "" },
                        textColor = textColor,
                        bold = false,
                        minWidth = columnWidth(colCount)
                    )
                }
            }
        }
    }
}

@Composable
private fun TableCell(
    text: String,
    textColor: Color,
    bold: Boolean,
    minWidth: Int
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall.copy(
            fontWeight = if (bold) FontWeight.SemiBold else FontWeight.Normal
        ),
        color = textColor,
        modifier = Modifier
            .widthIn(min = minWidth.dp)
            .padding(horizontal = 10.dp, vertical = 8.dp)
    )
}

private fun columnWidth(colCount: Int): Int = when {
    colCount <= 2 -> 140
    colCount == 3 -> 120
    else -> 100
}

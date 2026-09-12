package com.sushant.pmpstudy.domain

sealed class ContentBlock {
    data class Paragraph(val text: String) : ContentBlock()
    data class BulletList(val items: List<String>) : ContentBlock()
    data class Table(val headers: List<String>, val rows: List<List<String>>) : ContentBlock()
}

object StudyContentParser {

    fun parse(body: String): List<ContentBlock> {
        if (body.isBlank()) return emptyList()

        val blocks = mutableListOf<ContentBlock>()
        val lines = body.split('\n')
        var i = 0
        val paragraphBuffer = mutableListOf<String>()

        fun flushParagraph() {
            val text = paragraphBuffer.joinToString("\n").trim()
            if (text.isNotEmpty()) blocks.add(ContentBlock.Paragraph(text))
            paragraphBuffer.clear()
        }

        while (i < lines.size) {
            val trimmed = lines[i].trim()
            when {
                trimmed.startsWith("|") -> {
                    flushParagraph()
                    val tableRows = mutableListOf<List<String>>()
                    while (i < lines.size && lines[i].trim().startsWith("|")) {
                        parseRow(lines[i].trim())?.let { tableRows.add(it) }
                        i++
                    }
                    if (tableRows.isNotEmpty()) {
                        blocks.add(
                            ContentBlock.Table(
                                headers = tableRows.first(),
                                rows = tableRows.drop(1)
                            )
                        )
                    }
                }
                trimmed.startsWith("•") -> {
                    flushParagraph()
                    val items = mutableListOf<String>()
                    while (i < lines.size && lines[i].trim().startsWith("•")) {
                        items.add(lines[i].trim().removePrefix("•").trim())
                        i++
                    }
                    if (items.isNotEmpty()) blocks.add(ContentBlock.BulletList(items))
                }
                else -> {
                    if (trimmed.isEmpty() && paragraphBuffer.isEmpty()) {
                        i++
                        continue
                    }
                    paragraphBuffer.add(lines[i])
                    i++
                }
            }
        }
        flushParagraph()
        return blocks
    }

    private fun parseRow(line: String): List<String>? {
        val cells = line.trim()
            .removePrefix("|")
            .trim()
            .split("|")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
        return cells.takeIf { it.isNotEmpty() }
    }
}

package com.sushant.pmpstudy.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class StudyContentParserTest {
    @Test
    fun parsesTwoColumnTable() {
        val body = "| \n | Item | Detail\n | Total Questions | 180 total"
        val blocks = StudyContentParser.parse(body)
        assertEquals(1, blocks.size)
        val table = blocks[0] as ContentBlock.Table
        assertEquals(listOf("Item", "Detail"), table.headers)
        assertEquals(1, table.rows.size)
        assertEquals("Total Questions", table.rows[0][0])
        assertEquals("180 total", table.rows[0][1])
    }

    @Test
    fun parsesMixedParagraphAndTable() {
        val body = "Intro text.\n\n| \n | A | B\n | 1 | 2"
        val blocks = StudyContentParser.parse(body)
        assertEquals(2, blocks.size)
        assertTrue(blocks[0] is ContentBlock.Paragraph)
        assertTrue(blocks[1] is ContentBlock.Table)
    }

    @Test
    fun parsesBulletList() {
        val body = "• First point\n• Second point"
        val blocks = StudyContentParser.parse(body)
        assertEquals(1, blocks.size)
        val list = blocks[0] as ContentBlock.BulletList
        assertEquals(listOf("First point", "Second point"), list.items)
    }
}

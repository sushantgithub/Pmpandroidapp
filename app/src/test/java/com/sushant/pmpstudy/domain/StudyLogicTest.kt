package com.sushant.pmpstudy.domain

import com.sushant.pmpstudy.data.StudyRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EarnedValueMathTest {
    @Test
    fun behindScheduleAndOverBudget() {
        val result = EarnedValueMath.compute(ev = 80_000.0, pv = 100_000.0, ac = 90_000.0, bac = 200_000.0)
        assertEquals(-20_000.0, result.sv, 0.01)
        assertEquals(-10_000.0, result.cv, 0.01)
        assertEquals(0.8, result.spi, 0.0001)
        assertEquals(80_000.0 / 90_000.0, result.cpi, 0.0001)
        assertEquals(200_000.0 / result.cpi, result.eacTypical, 0.01)
    }

    @Test
    fun zeroPvExplainsWhichField() {
        runCatching { EarnedValueMath.compute(ev = 1.0, pv = 0.0, ac = 1.0, bac = 2.0) }
            .onFailure { assertEquals("PV must not be zero", it.message) }
            .onSuccess { throw AssertionError("expected failure") }
    }
}

class QuizGraderTest {
    @Test
    fun packLookupIsCached() {
        assertEquals(StudyRepository.quizPacks, StudyRepository.quizPacks)
        assertEquals(20, StudyRepository.pack("mixed")?.questions?.size)
    }

    @Test
    fun everyChapterWithQuizHasUniqueIds() {
        val ids = StudyRepository.allQuestions.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
        assertTrue(StudyRepository.allQuestions.size >= 40)
    }

    @Test
    fun perfectScoreOnMixed() {
        val pack = StudyRepository.mixedExam
        val answers = pack.questions.associate { it.id to it.correctIndex }
        val result = QuizGrader.grade(pack.questions, answers)
        assertEquals(20, result.correct)
        assertEquals(100, result.percent)
    }

    @Test
    fun emptyAnswersScoreZero() {
        val result = QuizGrader.grade(StudyRepository.allQuestions, emptyMap())
        assertEquals(0, result.correct)
        assertEquals(0, result.percent)
    }

    @Test
    fun has171PracticeQuestions() {
        assertEquals(171, StudyRepository.allQuestions.size)
    }

    @Test
    fun has23ChaptersWithUniqueIds() {
        val ids = StudyRepository.chapters.map { it.id }
        assertEquals(23, ids.size)
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun guideInfoSitsAfterBlueprint() {
        val ids = StudyRepository.chapters.map { it.id }
        assertEquals("blueprint", ids[0])
        assertEquals("about", ids[1])
        assertEquals("pmbok8", ids[2])
        assertEquals("casestudies", ids[ids.lastIndex - 1])
        assertEquals("cheatsheet", ids.last())
        assertTrue("external-env" in ids)
        assertEquals("external-env", ids[ids.indexOf("benefits") + 1])
        val categories = StudyRepository.chapters.map { it.category }.distinct()
        assertEquals("Start here", categories[0])
        assertEquals("Guide info", categories[1])
        assertEquals("Case study", categories[categories.lastIndex - 1])
        assertEquals("Review", categories.last())
        val grouped = StudyRepository.chapters.groupBy { it.category }
        assertEquals(listOf("blueprint"), grouped.getValue("Start here").map { it.id })
        assertEquals(listOf("about"), grouped.getValue("Guide info").map { it.id })
        assertEquals(listOf("casestudies"), grouped.getValue("Case study").map { it.id })
    }

    @Test
    fun hasWorkedExamplesAndAboutChapter() {
        assertTrue(StudyRepository.chapters.any { it.id == "about" })
        val examples = StudyRepository.chapters.flatMap { it.sections }.count { section ->
            section.heading.startsWith("Worked example")
        }
        assertTrue(examples >= 20)
        assertEquals(10, StudyRepository.questionsForChapter("casestudies").size)
        assertEquals(6, StudyRepository.questionsForChapter("external-env").size)
        assertEquals(12, StudyRepository.questionsForChapter("benefits").size)
    }
}

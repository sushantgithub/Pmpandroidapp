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
    fun eightPeopleHaveTwentyEightChannels() {
        assertEquals(28, EarnedValueMath.communicationChannels(8))
    }
}

class QuizGraderTest {
    @Test
    fun mixedExamHasTwentyItems() {
        assertEquals(20, StudyRepository.mixedExam.questions.size)
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
    fun has149PracticeQuestions() {
        assertEquals(149, StudyRepository.allQuestions.size)
    }

    @Test
    fun chapterIdsAreUnique() {
        val ids = StudyRepository.chapters.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun hasWorkedExamplesAndAboutChapter() {
        assertTrue(StudyRepository.chapters.any { it.id == "about" })
        val examples = StudyRepository.chapters.flatMap { it.sections }.count { section ->
            section.heading.startsWith("Worked example")
        }
        assertTrue(examples >= 20)
    }
}

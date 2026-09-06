package com.sushant.pmpstudy.domain

import com.sushant.pmpstudy.data.StudyRepository
import org.junit.Assert.assertEquals
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
    fun perfectScore() {
        val answers = StudyRepository.questions.associate { it.id to it.correctIndex }
        val result = QuizGrader.grade(StudyRepository.questions, answers)
        assertEquals(15, result.correct)
        assertEquals(100, result.percent)
    }

    @Test
    fun emptyAnswersScoreZero() {
        val result = QuizGrader.grade(StudyRepository.questions, emptyMap())
        assertEquals(0, result.correct)
        assertEquals(0, result.percent)
    }
}

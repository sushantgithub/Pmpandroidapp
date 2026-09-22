package com.sushant.pmpstudy.domain

import com.sushant.pmpstudy.data.FormulaCatalog
import com.sushant.pmpstudy.data.StudyRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
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
        assertEquals(StudyRepository.fullExam, StudyRepository.pack(StudyRepository.FULL_EXAM_ID))
    }

    @Test
    fun everyChapterWithQuizHasUniqueIds() {
        val ids = StudyRepository.allQuestions.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
        assertTrue(StudyRepository.allQuestions.size >= 40)
    }

    @Test
    fun perfectScoreOnFormulaDrill() {
        val pack = StudyRepository.formulaDrill
        val answers = pack.questions.associate { it.id to it.correctIndex }
        val result = QuizGrader.grade(pack.questions, answers)
        assertEquals(pack.questions.size, result.correct)
        assertEquals(100, result.percent)
    }

    @Test
    fun emptyAnswersScoreZero() {
        val result = QuizGrader.grade(StudyRepository.allQuestions, emptyMap())
        assertEquals(0, result.correct)
        assertEquals(0, result.percent)
    }

    @Test
    fun fullExamUsesEveryUniqueQuestionAndCorrectTiming() {
        val exam = StudyRepository.fullExam
        assertTrue(exam.examMode)
        assertEquals(240, exam.timeLimitMinutes)
        assertEquals(180, exam.questions.size)
        assertEquals(180, exam.questions.map { it.id }.toSet().size)
        assertEquals(StudyRepository.allQuestions.map { it.id }.toSet(), exam.questions.map { it.id }.toSet())
    }

    @Test
    fun has180PracticeQuestions() {
        assertEquals(180, StudyRepository.allQuestions.size)
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
        assertEquals("casestudies", ids[2])
        assertEquals("cheatsheet", ids.last())
        assertTrue("external-env" in ids)
        assertEquals("external-env", ids[ids.indexOf("benefits") + 1])
        val categories = StudyRepository.chapters.map { it.category }.distinct()
        assertEquals("Start here", categories[0])
        assertEquals("Guide info", categories[1])
        assertEquals("Review", categories.last())
    }

    @Test
    fun blankSearchReturnsEveryChapter() {
        assertEquals(StudyRepository.chapters, StudyRepository.search(""))
        assertEquals(StudyRepository.chapters, StudyRepository.search("   "))
    }

    @Test
    fun searchIsCaseInsensitiveAndTrimsQuery() {
        val lower = StudyRepository.search("monte carlo")
        assertEquals(lower, StudyRepository.search("MONTE CARLO"))
        assertEquals(lower, StudyRepository.search("  Monte Carlo  "))
        assertTrue(lower.isNotEmpty())
    }

    @Test
    fun searchMatchesTableCellContentNotJustTitles() {
        // "Resource Smoothing" only appears inside a table, never in a chapter title.
        val hits = StudyRepository.search("resource smoothing")
        assertTrue(hits.isNotEmpty())
        assertTrue(hits.none { it.title.lowercase().contains("resource smoothing") })
    }

    @Test
    fun searchWithNoMatchReturnsEmpty() {
        assertTrue(StudyRepository.search("zzqqxx").isEmpty())
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
        assertEquals(6, StudyRepository.questionsForChapter("benefits").size)
    }
}

class QuizDataIntegrityTest {
    @Test
    fun everyQuestionIsWellFormed() {
        StudyRepository.allQuestions.forEach { question ->
            assertEquals("${question.id} choice count", 4, question.choices.size)
            assertTrue(
                "${question.id} correctIndex ${question.correctIndex} out of range",
                question.correctIndex in question.choices.indices
            )
            assertTrue("${question.id} blank prompt", question.prompt.isNotBlank())
            assertTrue("${question.id} blank explanation", question.explanation.isNotBlank())
            assertTrue("${question.id} blank choice", question.choices.none { it.isBlank() })
        }
    }

    @Test
    fun questionCountAgreesWithTheFilteredList() {
        StudyRepository.chapters.forEach { chapter ->
            assertEquals(
                "count mismatch for ${chapter.id}",
                StudyRepository.questionsForChapter(chapter.id).size,
                StudyRepository.questionCount(chapter.id)
            )
        }
        assertEquals(0, StudyRepository.questionCount("no-such-chapter"))
    }

    @Test
    fun everyQuestionBelongsToARealChapter() {
        val chapterIds = StudyRepository.chapters.map { it.id }.toSet()
        StudyRepository.allQuestions.forEach { question ->
            assertTrue(
                "${question.id} references unknown chapter ${question.chapterId}",
                question.chapterId in chapterIds
            )
        }
    }
}

class FormulaDrillTest {
    @Test
    fun coversEveryFormulaInTheCatalog() {
        assertEquals(FormulaCatalog.all.size, StudyRepository.formulaDrill.questions.size)
    }

    @Test
    fun eachQuestionMarksItsOwnFormulaAsCorrect() {
        StudyRepository.formulaDrill.questions.forEachIndexed { index, question ->
            val formula = FormulaCatalog.all[index]
            assertTrue(question.correctIndex in question.choices.indices)
            assertTrue(question.choices[question.correctIndex].endsWith(formula.expression))
            assertTrue(question.prompt.contains(formula.name))
        }
    }

    @Test
    fun everyQuestionOffersFourDistinctOptions() {
        StudyRepository.formulaDrill.questions.forEach { question ->
            assertEquals(4, question.choices.size)
            // Drop the "A. " / "B. " prefix before comparing the expressions.
            val expressions = question.choices.map { it.substring(3) }
            assertEquals(expressions.size, expressions.toSet().size)
        }
    }

    @Test
    fun drillIsReachableAsAQuizPack() {
        assertEquals(
            StudyRepository.formulaDrill,
            StudyRepository.pack(StudyRepository.FORMULA_DRILL_ID)
        )
    }

    @Test
    fun drillIsDeterministicAcrossAccesses() {
        assertEquals(StudyRepository.formulaDrill, StudyRepository.formulaDrill)
    }

    @Test
    fun drillDoesNotInflateTheChapterQuestionCount() {
        assertEquals(180, StudyRepository.allQuestions.size)
        assertTrue(StudyRepository.allQuestions.none { it.id.startsWith("formula-") })
    }
}

/**
 * AppState without [AppState.init]: no SharedPreferences is attached, so writes
 * stay in memory and the in-memory bookkeeping is what these exercise.
 */
class ProgressTest {

    @Before
    fun clearProgress() = AppState.reset()

    @Test
    fun firstAttemptCountsAsABest() {
        assertTrue(AppState.saveQuizResult("scope", 55))
        val progress = AppState.progressFor("scope")
        assertEquals(55, progress?.bestPercent)
        assertEquals(55, progress?.lastPercent)
        assertEquals(1, progress?.attempts)
    }

    @Test
    fun bestKeepsTheHighScoreWhileLastFollowsEveryAttempt() {
        AppState.saveQuizResult("scope", 80)
        assertTrue("a higher score is a new best", AppState.saveQuizResult("scope", 90))
        assertFalse("a lower score is not", AppState.saveQuizResult("scope", 40))

        val progress = AppState.progressFor("scope")
        assertEquals(90, progress?.bestPercent)
        assertEquals(40, progress?.lastPercent)
        assertEquals(3, progress?.attempts)
    }

    @Test
    fun matchingThePreviousBestIsNotANewBest() {
        AppState.saveQuizResult("risk", 70)
        assertFalse(AppState.saveQuizResult("risk", 70))
        assertEquals(70, AppState.progressFor("risk")?.bestPercent)
    }

    @Test
    fun averageSpansEveryAttemptedPackAndIgnoresTheRest() {
        assertNull(AppState.averageBest)
        assertEquals(0, AppState.attemptedPacks)

        AppState.saveQuizResult("scope", 90)
        AppState.saveQuizResult("risk", 61)

        assertEquals(2, AppState.attemptedPacks)
        // Mean of the bests, rounded: (90 + 61) / 2 = 75.5 -> 76
        assertEquals(76, AppState.averageBest)
        assertNull(AppState.progressFor("never-attempted"))
    }

    @Test
    fun resetClearsEverything() {
        AppState.saveQuizResult("scope", 90)
        AppState.reset()
        assertEquals(0, AppState.attemptedPacks)
        assertNull(AppState.averageBest)
        assertNull(AppState.progressFor("scope"))
    }

    @Test
    fun readingAndBookmarksToggleIndependently() {
        AppState.markUnread("scope")
        if (AppState.isBookmarked("scope", 0)) AppState.toggleBookmark("scope", 0)

        AppState.markRead("scope")
        AppState.toggleBookmark("scope", 0)

        assertTrue(AppState.isRead("scope"))
        assertTrue(AppState.isBookmarked("scope", 0))

        AppState.markUnread("scope")
        AppState.toggleBookmark("scope", 0)

        assertFalse(AppState.isRead("scope"))
        assertFalse(AppState.isBookmarked("scope", 0))
    }

    @Test
    fun quizResetPreservesReadingAndBookmarks() {
        AppState.markUnread("risk")
        if (AppState.isBookmarked("risk", 1)) AppState.toggleBookmark("risk", 1)

        AppState.markRead("risk")
        AppState.toggleBookmark("risk", 1)
        AppState.saveQuizResult("risk", 85)

        AppState.reset()

        assertNull(AppState.progressFor("risk"))
        assertTrue(AppState.isRead("risk"))
        assertTrue(AppState.isBookmarked("risk", 1))

        AppState.markUnread("risk")
        AppState.toggleBookmark("risk", 1)
    }
}

class DisplaySettingsTest {
    @Test
    fun defaultFontScaleIsUnscaled() {
        assertEquals(1.0f, FontScale.MEDIUM.scale, 0.0001f)
    }

    @Test
    fun fontScalesAscendAndStayReadable() {
        val scales = FontScale.values().map { it.scale }
        assertEquals(scales.sorted(), scales)
        assertTrue(scales.all { it in 0.5f..2.0f })
    }

    @Test
    fun everyOptionIsLabelled() {
        assertTrue(FontScale.values().all { it.label.isNotBlank() })
        assertTrue(ThemeMode.values().all { it.label.isNotBlank() })
    }
}

package com.sushant.pmpstudy.data

import kotlin.random.Random

object StudyRepository {
    const val FORMULA_DRILL_ID = "formulas-drill"
    const val FULL_EXAM_ID = "full-exam-180"

    private val chapterOrder = listOf(
        "blueprint",
        "about",
        "casestudies",
        "pmbok8",
        "integration",
        "scope",
        "schedule",
        "cost",
        "quality",
        "risk",
        "stakeholders",
        "procurement",
        "mindset",
        "leadership",
        "people-advanced",
        "ethics",
        "business",
        "benefits",
        "external-env",
        "ai",
        "tailoring",
        "agile",
        "cheatsheet"
    )

    val chapters: List<Chapter> = ordered(ChapterCatalog.all)
    val formulas: List<Formula> = FormulaCatalog.all
    val allQuestions: List<QuizQuestion> = QuizBank.all + ExamReadinessQuestions.all

    private fun ordered(source: List<Chapter>): List<Chapter> {
        val byId = source.associateBy { it.id }
        val ranked = chapterOrder.mapNotNull { byId[it] }
        val rest = source.filter { it.id !in chapterOrder }
        return ranked + rest
    }

    fun chapter(id: String): Chapter? = chapters.firstOrNull { it.id == id }

    /**
     * Lowercased haystack per chapter, built once on first search.
     *
     * Matching against the live [Chapter] tree instead means lowercasing every
     * section body and table cell (~450KB of text) on every keystroke. This
     * trades one upfront pass for cheap substring lookups afterwards.
     */
    private val searchIndex: Map<String, String> by lazy {
        chapters.associate { chapter -> chapter.id to chapter.haystack() }
    }

    private fun Chapter.haystack(): String = buildString {
        appendLine(title)
        appendLine(subtitle)
        appendLine(category)
        sections.forEach { section ->
            appendLine(section.heading)
            appendLine(section.body)
            section.tableHeaders.forEach { appendLine(it) }
            section.tableRows.forEach { row -> row.forEach { appendLine(it) } }
        }
    }.lowercase()

    /** Chapters matching [query] across titles, bodies and table cells. Blank query returns all. */
    fun search(query: String): List<Chapter> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return chapters
        return chapters.filter { searchIndex[it.id]?.contains(q) == true }
    }

    fun questionsForChapter(chapterId: String): List<QuizQuestion> =
        allQuestions.filter { it.chapterId == chapterId }

    private val questionCounts: Map<String, Int> =
        allQuestions.groupingBy { it.chapterId }.eachCount()

    /**
     * Question count for a chapter, without walking the whole bank.
     *
     * The chapter list renders one card per chapter and the detail screen reads
     * this on every recomposition, so the filtering version turned each keystroke
     * in the search box into a full scan per visible card.
     */
    fun questionCount(chapterId: String): Int = questionCounts[chapterId] ?: 0

    /**
     * Multiple-choice recall drill generated from [FormulaCatalog]: the formula
     * name is the prompt, expressions are the options.
     *
     * Built with a fixed seed so the option order is stable across launches and
     * across recompositions — a per-run shuffle would reorder the answers under
     * the user mid-quiz.
     */
    val formulaDrill: QuizPack = buildFormulaDrill()

    private fun buildFormulaDrill(): QuizPack {
        val source = FormulaCatalog.all
        val rng = Random(seed = 20260101)
        val letters = listOf("A. ", "B. ", "C. ", "D. ")
        val questions = source.mapIndexed { index, formula ->
            // Expressions are unique across the catalog, so a distractor can never
            // duplicate the right answer and indexOf resolves unambiguously.
            val distractors = source
                .filter { it.expression != formula.expression }
                .shuffled(rng)
                .take(letters.size - 1)
                .map { it.expression }
            val options = (distractors + formula.expression).shuffled(rng)
            QuizQuestion(
                id = "formula-$index",
                chapterId = FORMULA_DRILL_ID,
                prompt = "Which formula gives ${formula.name}?",
                choices = options.mapIndexed { position, expression -> letters[position] + expression },
                correctIndex = options.indexOf(formula.expression),
                explanation = "${formula.name} = ${formula.expression}. ${formula.meaning}"
            )
        }
        return QuizPack(
            id = FORMULA_DRILL_ID,
            title = "Formula drill",
            subtitle = "${questions.size} questions · Recall every formula from memory",
            questions = questions
        )
    }

    /** Full-length practice mode: 180 unique questions, 240 minutes, no instant feedback. */
    val fullExam: QuizPack = QuizPack(
        id = FULL_EXAM_ID,
        title = "Full-length exam practice",
        subtitle = "180 questions · 240 minutes · Feedback after submission",
        questions = allQuestions.shuffled(Random(seed = 20260922)),
        examMode = true,
        timeLimitMinutes = 240
    )

    val quizPacks: List<QuizPack> = listOf(fullExam, formulaDrill) + chapters.mapNotNull { chapter ->
        val qs = questionsForChapter(chapter.id)
        if (qs.isEmpty()) null
        else QuizPack(
            id = chapter.id,
            title = chapter.title,
            subtitle = "${qs.size} questions · ${chapter.category}",
            questions = qs
        )
    }

    fun pack(id: String): QuizPack? = quizPacks.firstOrNull { it.id == id }
}

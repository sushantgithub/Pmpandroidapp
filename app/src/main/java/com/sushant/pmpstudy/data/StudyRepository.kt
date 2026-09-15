package com.sushant.pmpstudy.data

object StudyRepository {
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
    val allQuestions: List<QuizQuestion> = QuizBank.all

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

    val mixedExam: QuizPack = QuizPack(
        id = "mixed",
        title = "Mixed exam set",
        subtitle = "20 questions across People, Process, Business Environment & Agile",
        questions = QuizBank.mixedTwenty
    )

    val quizPacks: List<QuizPack> = listOf(mixedExam) + chapters.mapNotNull { chapter ->
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

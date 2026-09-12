package com.sushant.pmpstudy.data

object StudyRepository {
    val chapters: List<Chapter> = ChapterCatalog.all
    val formulas: List<Formula> = FormulaCatalog.all
    val allQuestions: List<QuizQuestion> = QuizBank.all

    fun chapter(id: String): Chapter? = chapters.firstOrNull { it.id == id }

    fun questionsForChapter(chapterId: String): List<QuizQuestion> =
        allQuestions.filter { it.chapterId == chapterId }

    val mixedExam: QuizPack = QuizPack(
        id = "mixed",
        title = "Mixed exam set",
        subtitle = "20 questions across People, Process, Business Environment & Agile",
        questions = QuizBank.mixedTwenty
    )

    fun quizPacks(): List<QuizPack> {
        val sectionPacks = chapters.mapNotNull { chapter ->
            val qs = questionsForChapter(chapter.id)
            if (qs.isEmpty()) null
            else QuizPack(
                id = chapter.id,
                title = chapter.title,
                subtitle = "${qs.size} questions · ${chapter.category}",
                questions = qs
            )
        }
        return listOf(mixedExam) + sectionPacks
    }

    fun pack(id: String): QuizPack? = quizPacks().firstOrNull { it.id == id }
}

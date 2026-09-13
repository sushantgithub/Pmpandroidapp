package com.sushant.pmpstudy.data

enum class SectionKind { BODY, NOTE, TIP, WARN, DANGER, KEY }

data class Section(
    val heading: String,
    val body: String = "",
    val kind: SectionKind = SectionKind.BODY,
    val tableHeaders: List<String> = emptyList(),
    val tableRows: List<List<String>> = emptyList()
) {
    val hasTable: Boolean get() = tableHeaders.isNotEmpty() && tableRows.isNotEmpty()
}

data class Chapter(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val sections: List<Section>
)

data class Formula(
    val name: String,
    val expression: String,
    val meaning: String
)

data class QuizQuestion(
    val id: String,
    val chapterId: String,
    val prompt: String,
    val choices: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class QuizPack(
    val id: String,
    val title: String,
    val subtitle: String,
    val questions: List<QuizQuestion>
)

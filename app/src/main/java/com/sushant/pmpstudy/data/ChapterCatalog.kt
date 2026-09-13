package com.sushant.pmpstudy.data

internal fun s(
    heading: String,
    body: String = "",
    kind: SectionKind = SectionKind.BODY,
    tableHeaders: List<String> = emptyList(),
    tableRows: List<List<String>> = emptyList()
) = Section(heading, body, kind, tableHeaders, tableRows)

object ChapterCatalog {
    val all: List<Chapter> = ChapterCatalogA.chapters + ChapterCatalogB.chapters
}

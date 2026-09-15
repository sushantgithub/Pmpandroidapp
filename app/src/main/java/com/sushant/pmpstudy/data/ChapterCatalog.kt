package com.sushant.pmpstudy.data

// `internal` (not `private`): this helper is shared across the ChapterCatalog*
// files in this module. A `private` top-level function is file-scoped, so the
// calls in ChapterCatalogA.kt / ChapterCatalogB.kt would not compile.
internal fun s(
    heading: String,
    body: String = "",
    kind: SectionKind = SectionKind.BODY,
    tableHeaders: List<String> = emptyList(),
    tableRows: List<List<String>> = emptyList()
) = Section(heading, body, kind, tableHeaders, tableRows)

object ChapterCatalog {
    // Chapter content lives in ChapterCatalogA/B, which split it across two
    // objects to stay under the JVM 64KB per-method bytecode limit. Keep new
    // chapters there — this file only holds the shared s() helper.
    val all: List<Chapter> = ChapterCatalogA.chapters + ChapterCatalogB.chapters
}

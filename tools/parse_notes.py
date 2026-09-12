#!/usr/bin/env python3
"""Parse Sushant's PMP HTML notes into Kotlin data files."""
import html as htmlmod
import re
import sys

INPUT = sys.argv[1] if len(sys.argv) > 1 else "/home/ubuntu/.cursor/projects/workspace/uploads/PMP_Study_Notesupdated_1c9b.txt"
OUT_DIR = sys.argv[2] if len(sys.argv) > 2 else "/workspace/app/src/main/java/com/sushant/pmpstudy/data"

CATEGORIES = {
    "blueprint": "Start here",
    "pmbok8": "PMBOK 8",
    "mindset": "People domain",
    "integration": "Knowledge domains",
    "scope": "Knowledge domains",
    "schedule": "Knowledge domains",
    "cost": "Knowledge domains",
    "quality": "Knowledge domains",
    "risk": "Knowledge domains",
    "stakeholders": "Knowledge domains",
    "procurement": "Knowledge domains",
    "leadership": "People domain",
    "people-advanced": "People domain",
    "ethics": "People domain",
    "business": "Business & tailoring",
    "benefits": "Business & tailoring",
    "ai": "Business & tailoring",
    "tailoring": "Business & tailoring",
    "agile": "Agile & Scrum",
    "cheatsheet": "Review",
}


def strip_tags(s: str) -> str:
    s = re.sub(r"<br\s*/?>", "\n", s)
    s = re.sub(r"<li[^>]*>", "• ", s)
    s = re.sub(r"</li>", "\n", s)
    s = re.sub(r"<tr[^>]*>", "\n", s)
    s = re.sub(r"</tr>", "", s)
    s = re.sub(r"<t[hd][^>]*>", " | ", s)
    s = re.sub(r"</t[hd]>", "", s)
    s = re.sub(r"<[^>]+>", "", s)
    s = htmlmod.unescape(s)
    s = re.sub(r"\n{3,}", "\n\n", s)
    s = re.sub(r"[ \t]+", " ", s)
    return s.strip()


def kotlin_str(s: str) -> str:
    return '"' + s.replace("\\", "\\\\").replace('"', '\\"').replace("$", "\\$").replace("\n", "\\n") + '"'


def section_for_pos(raw: str, pos: int, ranges: list) -> str:
    for i in range(len(ranges) - 1):
        if ranges[i][0] <= pos < ranges[i + 1][0]:
            return ranges[i][1]
    return "blueprint"


def parse_table_html(table_html: str):
    rows = []
    for tr in re.findall(r"<tr[^>]*>(.*?)</tr>", table_html, re.DOTALL):
        cells = [strip_tags(c) for c in re.findall(r"<t[hd][^>]*>(.*?)</t[hd]>", tr, re.DOTALL)]
        cells = [c for c in cells if c]
        if cells:
            rows.append(cells)
    if len(rows) < 1:
        return None
    return rows[0], rows[1:]


def parse_blocks(content: str) -> list:
    """Split section HTML into ordered content blocks.
    Each block: dict with keys heading, kind, body, table_headers, table_rows.
    """
    content = re.sub(
        r'<div class="quiz-block">.*?</div>\s*(?=<h[34]|</section>|$)',
        "",
        content,
        flags=re.DOTALL,
    )
    blocks = []
    pattern = re.compile(
        r"<h3[^>]*>(.*?)</h3>|"
        r"<h4[^>]*>(.*?)</h4>|"
        r'<div class="callout (\w+)">.*?<div>(.*?)</div></div>|'
        r'<div class="formula-box">(.*?)</div>|'
        r'<div class="tbl-wrap"><table>(.*?)</table></div>|'
        r'<div class="(?:two-col|card-grid|flow|art-box|example-box)[^"]*">(.*?)</div>|'
        r"<p[^>]*>(.*?)</p>|"
        r"<ul[^>]*>(.*?)</ul>|"
        r"<ol[^>]*>(.*?)</ol>",
        re.DOTALL,
    )
    current_h3 = ""
    for m in pattern.finditer(content):
        if m.group(1) is not None:
            current_h3 = strip_tags(m.group(1))
            continue
        if m.group(2) is not None:
            current_h3 = strip_tags(m.group(2))
            continue
        if m.group(3) is not None:
            kind_map = {"info": "NOTE", "tip": "TIP", "warn": "WARN", "danger": "DANGER", "key": "KEY"}
            kind = kind_map.get(m.group(3), "BODY")
            body = strip_tags(m.group(4))
            if body:
                blocks.append({"heading": current_h3 or kind.title(), "body": body, "kind": kind})
            continue
        if m.group(5) is not None:
            body = strip_tags(m.group(5))
            if body:
                blocks.append({"heading": current_h3 or "Formula", "body": body, "kind": "BODY"})
            continue
        if m.group(6) is not None:
            parsed = parse_table_html(m.group(6))
            if parsed:
                headers, rows = parsed
                blocks.append(
                    {
                        "heading": current_h3 or "Table",
                        "body": "",
                        "kind": "BODY",
                        "table_headers": headers,
                        "table_rows": rows,
                    }
                )
            continue
        for gi in (7, 8, 9, 10):
            if m.group(gi) is not None:
                body = strip_tags(m.group(gi))
                if body and len(body) > 8:
                    blocks.append({"heading": current_h3 or "Notes", "body": body, "kind": "BODY"})
                break

    merged = []
    for block in blocks:
        if not block.get("body") and not block.get("table_headers"):
            continue
        if (
            merged
            and merged[-1]["heading"] == block["heading"]
            and merged[-1]["kind"] == block["kind"] == "BODY"
            and not merged[-1].get("table_headers")
            and not block.get("table_headers")
        ):
            merged[-1]["body"] = f"{merged[-1]['body']}\n\n{block['body']}"
        else:
            merged.append(block)
    return merged


def kotlin_list_str(items) -> str:
    if not items:
        return "emptyList()"
    inner = ", ".join(kotlin_str(str(x)) for x in items)
    return f"listOf({inner})"


def kotlin_table_rows(rows) -> str:
    if not rows:
        return "emptyList()"
    row_strs = []
    for row in rows:
        row_strs.append(f"listOf({', '.join(kotlin_str(str(c)) for c in row)})")
    return "listOf(" + ", ".join(row_strs) + ")"


def main():
    with open(INPUT, encoding="utf-8") as f:
        raw = f.read()

    section_pattern = re.compile(
        r'<section class="sec" id="([^"]+)"><h2 class="sec-title">([^<]+)</h2>(.*?)</section>',
        re.DOTALL,
    )
    sections = section_pattern.findall(raw)

    ranges = [(m.start(), m.group(1)) for m in re.finditer(r'<section class="sec" id="([^"]+)"', raw)]
    ranges.append((len(raw), ""))

    quiz_questions = []
    for i, m in enumerate(re.finditer(r'<div class="q-item"', raw)):
        chunk = raw[m.start() : m.start() + 4000]
        qm = re.search(r'<div class="q-text"><span class="q-num">Q</span>\s*(.*?)</div>', chunk, re.DOTALL)
        if not qm:
            continue
        prompt = strip_tags(qm.group(1))
        opts_html = re.search(r'<div class="opts">(.*?)</div>', chunk, re.DOTALL)
        explain_html = re.search(r'<div class="q-explain[^"]*">(.*?)</div>', chunk, re.DOTALL)
        choices = []
        correct_idx = 0
        if opts_html:
            for om in re.finditer(
                r'<button class="opt-btn"[^>]*data-correct="(true|false)"[^>]*>(.*?)</button>',
                opts_html.group(1),
                re.DOTALL,
            ):
                if om.group(1) == "true":
                    correct_idx = len(choices)
                choices.append(strip_tags(om.group(2)))
        explain = ""
        if explain_html:
            explain = strip_tags(explain_html.group(1))
            explain = re.sub(r"^✅ Answer: [A-D]\s*", "", explain).strip()
        chapter_id = section_for_pos(raw, m.start(), ranges)
        quiz_questions.append(
            {
                "id": f"q{i + 1}",
                "chapterId": chapter_id,
                "prompt": prompt,
                "choices": choices,
                "correctIndex": correct_idx,
                "explanation": explain,
            }
        )

    print(f"Parsed {len(sections)} chapters, {len(quiz_questions)} questions")

    chapter_lines = [
        "package com.sushant.pmpstudy.data",
        "",
        "private fun s(",
        "    heading: String,",
        "    body: String = \"\",",
        "    kind: SectionKind = SectionKind.BODY,",
        "    tableHeaders: List<String> = emptyList(),",
        "    tableRows: List<List<String>> = emptyList()",
        ") = Section(heading, body, kind, tableHeaders, tableRows)",
        "",
        "object ChapterCatalog {",
        "    val all: List<Chapter> = listOf(",
    ]

    for sid, title, content in sections:
        title_clean = strip_tags(title)
        subs = parse_blocks(content)
        h3m = re.search(r"<h3[^>]*>(.*?)</h3>", content)
        subtitle = strip_tags(h3m.group(1))[:90] if h3m else f"PMP exam prep · {CATEGORIES.get(sid, 'Study')}"
        category = CATEGORIES.get(sid, "Study")

        chapter_lines.append("        Chapter(")
        chapter_lines.append(f"            {kotlin_str(sid)},")
        chapter_lines.append(f"            {kotlin_str(title_clean)},")
        chapter_lines.append(f"            {kotlin_str(subtitle)},")
        chapter_lines.append(f"            {kotlin_str(category)},")
        chapter_lines.append("            listOf(")

        if not subs:
            body = strip_tags(re.sub(r'<div class="quiz-block">.*', "", content, flags=re.DOTALL))
            if body:
                subs = [{"heading": "Overview", "body": body[:2000], "kind": "BODY"}]

        for block in subs[:50]:
            heading = block["heading"]
            body = block.get("body", "")
            kind = block.get("kind", "BODY")
            headers = block.get("table_headers", [])
            rows = block.get("table_rows", [])
            if body and len(body) > 1800:
                body = body[:1797] + "..."
            if headers:
                chapter_lines.append(
                    f"                s({kotlin_str(heading)}, tableHeaders = {kotlin_list_str(headers)}, "
                    f"tableRows = {kotlin_table_rows(rows)}),"
                )
            elif kind == "BODY":
                chapter_lines.append(f"                s({kotlin_str(heading)}, {kotlin_str(body)}),")
            else:
                chapter_lines.append(
                    f"                s({kotlin_str(heading)}, {kotlin_str(body)}, SectionKind.{kind}),"
                )

        chapter_lines.append("            )")
        chapter_lines.append("        ),")

    chapter_lines.append("    )")
    chapter_lines.append("}")

    with open(f"{OUT_DIR}/ChapterCatalog.kt", "w", encoding="utf-8") as f:
        f.write("\n".join(chapter_lines))

    quiz_lines = [
        "package com.sushant.pmpstudy.data",
        "",
        "object QuizBank {",
        "    val all: List<QuizQuestion> = listOf(",
    ]
    for q in quiz_questions:
        choices_k = ", ".join(kotlin_str(c) for c in q["choices"])
        quiz_lines.append(
            f"        QuizQuestion({kotlin_str(q['id'])}, {kotlin_str(q['chapterId'])}, "
            f"{kotlin_str(q['prompt'])}, listOf({choices_k}), {q['correctIndex']}, {kotlin_str(q['explanation'])}),"
        )
    quiz_lines.append("    )")
    quiz_lines.append("")
    quiz_lines.append("    val mixedTwenty: List<QuizQuestion> = listOf(")
    # Fixed mixed set: first 20 from diverse chapters
    seen_ch = set()
    mixed = []
    for q in quiz_questions:
        if q["chapterId"] not in seen_ch or len(mixed) < 20:
            mixed.append(q)
            seen_ch.add(q["chapterId"])
        if len(mixed) >= 20:
            break
    if len(mixed) < 20:
        mixed = quiz_questions[:20]
    for q in mixed:
        choices_k = ", ".join(kotlin_str(c) for c in q["choices"])
        quiz_lines.append(
            f"        QuizQuestion({kotlin_str(q['id'])}, {kotlin_str(q['chapterId'])}, "
            f"{kotlin_str(q['prompt'])}, listOf({choices_k}), {q['correctIndex']}, {kotlin_str(q['explanation'])}),"
        )
    quiz_lines.append("    )")
    quiz_lines.append("}")

    with open(f"{OUT_DIR}/QuizBank.kt", "w", encoding="utf-8") as f:
        f.write("\n".join(quiz_lines))

    formula_patterns = [
        ("Communication channels", "n(n-1)/2", "Channels for n people including the PM"),
        ("PERT estimate", "(O + 4M + P) / 6", "Weighted average: Optimistic, Most likely, Pessimistic"),
        ("PERT std deviation", "(P - O) / 6", "Uncertainty range of the estimate"),
        ("SPI", "EV / PV", "Schedule Performance Index — >1 ahead, <1 behind"),
        ("CPI", "EV / AC", "Cost Performance Index — >1 under budget, <1 over"),
        ("CV", "EV - AC", "Cost Variance — positive means under budget"),
        ("SV", "EV - PV", "Schedule Variance — positive means ahead of schedule"),
        ("EAC (typical)", "BAC / CPI", "Estimate at Completion when current trend continues"),
        ("EAC (re-estimate)", "AC + ETC", "When original estimate is no longer valid"),
        ("ETC", "EAC - AC", "Estimate to Complete remaining work"),
        ("VAC", "BAC - EAC", "Variance at Completion — positive means under budget at end"),
        ("TCPI", "(BAC - EV) / (BAC - AC)", "Performance needed on remaining work to hit BAC"),
        ("Present Value", "FV / (1+r)^n", "Discount future value to today"),
        ("ROI", "(Gain - Cost) / Cost", "Return on Investment as a ratio"),
        ("Payback Period", "Investment / Annual Cash Flow", "Years to recover the investment"),
        ("Float / Slack", "LS - ES or LF - EF", "Schedule flexibility without delaying the project"),
        ("Free Float", "ES(successor) - EF(current)", "Delay allowed without affecting successor"),
    ]

    formula_lines = [
        "package com.sushant.pmpstudy.data",
        "",
        "object FormulaCatalog {",
        "    val all: List<Formula> = listOf(",
    ]
    for name, expr, meaning in formula_patterns:
        formula_lines.append(f"        Formula({kotlin_str(name)}, {kotlin_str(expr)}, {kotlin_str(meaning)}),")
    formula_lines.append("    )")
    formula_lines.append("}")

    with open(f"{OUT_DIR}/FormulaCatalog.kt", "w", encoding="utf-8") as f:
        f.write("\n".join(formula_lines))

    for sid, title, content in sections:
        n = sum(1 for q in quiz_questions if q["chapterId"] == sid)
        print(f"  {sid}: {len(parse_blocks(content))} sections, {n} quizzes")


if __name__ == "__main__":
    main()

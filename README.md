# PMP® Prep Guide — Android App

Interactive Android study companion built from **Sushant Kulkarni**'s PMP Exam Prep Guide (2026 ECO Edition).

## What's inside

- **23 chapters** covering exam blueprint, 2026 case-study practice, PMBOK 8, knowledge domains, people skills, business environment, agile/scrum, worked examples, and a full cheat sheet
- **165 practice questions** with explanations, plus a dedicated formula drill
- **Earned-value formulas** with an interactive EVM calculator
- **Search** across study notes and table content
- **Persistent quiz progress** with best score, last score, attempt count, and average-best tracking
- **Reading progress and bookmarks** that survive app restarts
- **Light / dark / system themes** and adjustable text size
- Color-coded callouts for notes, tips, warnings, exam traps, and key concepts

This project is an independent study resource. **Not affiliated with, endorsed by, or sponsored by PMI.**

## Current build

Current application version: **2.7.0** (`versionCode 31`).

GitHub Actions runs unit tests and builds a fresh debug APK on every push. Open the **Build Debug APK** workflow and download the `pmp-study-debug-apk` artifact from a successful run:

https://github.com/sushantgithub/Pmpandroidapp/actions/workflows/build-apk.yml

Android 8.0 (API 26) or newer is required.

## Study notes

The source study material is kept under `study-notes/`, including:

- `study-notes/PMP_Prep_Guide_Sushant_Kulkarni.html`
- `study-notes/PMP_Study_Notes.html`
- `study-notes/PMP_Study_Notes.md`

## Build from source

```bash
export ANDROID_HOME=/path/to/android-sdk
echo "sdk.dir=$ANDROID_HOME" > local.properties
./gradlew test assembleDebug
```

The debug APK is written to:

```
app/build/outputs/apk/debug/app-debug.apk
```

To regenerate Kotlin study data from the HTML notes:

```bash
python3 tools/parse_notes.py
```

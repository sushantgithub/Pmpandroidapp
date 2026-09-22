# PMP® Prep Guide — Android App

Interactive Android study companion built from **Sushant Kulkarni**'s PMP Exam Prep Guide and aligned to the **PMI PMP Examination Content Outline — July 2026**.

## What's inside

- **23 chapters** covering the 2026 ECO, project-management knowledge, people skills, business environment, agile/hybrid practices, worked examples, and a review cheat sheet
- **180 unique practice questions** with explanations
- **Full-length exam practice:** 180 questions, 240-minute timer, answer feedback only after submission
- Chapter-level quizzes plus a dedicated formula drill
- **Earned-value formulas** with an interactive EVM calculator
- **Search** across study notes and table content
- **Persistent quiz progress** with best score, last score, attempt count, and average-best tracking
- **Reading progress and bookmarks** that survive app restarts
- **Light / dark / system themes** and adjustable text size
- Context-based scenario guidance rather than rigid "always/never" answer shortcuts

This project is an independent study resource. **Not affiliated with, endorsed by, or sponsored by PMI.**

## Current build

Current application version: **2.8.0** (`versionCode 32`).

- `compileSdk = 36`
- `targetSdk = 36`
- Minimum supported Android version: Android 8.0 / API 26
- GitHub Actions runs unit tests and builds a debug APK on every push

Download artifacts from the **Build Debug APK** workflow:

https://github.com/sushantgithub/Pmpandroidapp/actions/workflows/build-apk.yml

## Release signing

Release builds no longer use the debug signing key. To sign a release build, provide these environment variables locally or through your secure CI secret store:

```text
ANDROID_KEYSTORE_PATH
ANDROID_KEYSTORE_PASSWORD
ANDROID_KEY_ALIAS
ANDROID_KEY_PASSWORD
```

Then build:

```bash
./gradlew bundleRelease
```

Do **not** commit the keystore or signing passwords to the repository.

## Build from source

```bash
export ANDROID_HOME=/path/to/android-sdk
echo "sdk.dir=$ANDROID_HOME" > local.properties
./gradlew test assembleDebug
```

The debug APK is written to:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## Study notes

The source study material is kept under `study-notes/`, including:

- `study-notes/PMP_Prep_Guide_Sushant_Kulkarni.html`
- `study-notes/PMP_Study_Notes.html`
- `study-notes/PMP_Study_Notes.md`

To regenerate Kotlin study data from the HTML notes:

```bash
python3 tools/parse_notes.py
```

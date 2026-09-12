# PMP® Prep Guide — Android App

Interactive Android study companion built from **Sushant Kulkarni**'s PMP Exam Prep Guide (2026 ECO Edition).

## What's inside

- **20 chapters** covering exam blueprint, PMBOK 8, knowledge domains, people skills, business environment, agile/scrum, and a full cheat sheet
- **149 practice questions** with explanations — section-wise quizzes plus a mixed exam set
- **Earned-value formulas** with an interactive EVM calculator
- **Search** across all study notes
- Color-coded callouts (notes, tips, warnings, key concepts)

This project is an independent study resource. **Not affiliated with, endorsed by, or sponsored by PMI.**

## Download APK

Prebuilt debug APK (Android 8.0+, **v2.1.1**):

https://github.com/sushantgithub/Pmpandroidapp/raw/cursor/pmp-study-android-app-50e4/releases/PMP_Study.apk

On your phone, open the downloaded file and allow install from this source.

## Study notes (HTML)

Full prep guide by Sushant Kulkarni:

- [`study-notes/PMP_Prep_Guide_Sushant_Kulkarni.html`](study-notes/PMP_Prep_Guide_Sushant_Kulkarni.html)

## Build from source

```bash
export ANDROID_HOME=/path/to/android-sdk
echo "sdk.dir=$ANDROID_HOME" > local.properties
./gradlew assembleDebug test
```

The debug APK is written to `app/build/outputs/apk/debug/app-debug.apk`.

To regenerate Kotlin data from the HTML notes:

```bash
python3 tools/parse_notes.py
```

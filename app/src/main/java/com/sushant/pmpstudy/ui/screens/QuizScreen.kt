package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.StudyRepository
import com.sushant.pmpstudy.domain.AppState
import com.sushant.pmpstudy.domain.QuizGrader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(packId: String, onBack: () -> Unit) {
    val pack      = StudyRepository.pack(packId)
    val questions = pack?.questions.orEmpty()
    var answers   by rememberSaveable(packId) { mutableStateOf(mapOf<String, Int>()) }
    var index     by rememberSaveable(packId) { mutableIntStateOf(0) }
    var finished  by rememberSaveable(packId) { mutableStateOf(false) }
    var skipped   by rememberSaveable(packId) { mutableStateOf(setOf<String>()) }
    var reviewingSkipped by rememberSaveable(packId) { mutableStateOf(false) }
    val haptic = LocalHapticFeedback.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(pack?.title ?: "Quiz", maxLines = 1, overflow = TextOverflow.Ellipsis)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor    = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { inner ->
        if (pack == null) {
            Text("Quiz not found.", modifier = Modifier.padding(inner).padding(16.dp))
            return@Scaffold
        }
        if (questions.isEmpty()) {
            Text("No questions in this set.", modifier = Modifier.padding(inner).padding(16.dp))
            return@Scaffold
        }

        // ── Results screen ───────────────────────────────────────────────────
        if (finished) {
            val result  = QuizGrader.grade(questions, answers)
            val pct     = result.percent
            val colour  = when {
                pct >= 80 -> MaterialTheme.colorScheme.secondary
                pct >= 60 -> MaterialTheme.colorScheme.primary
                else      -> MaterialTheme.colorScheme.error
            }

            // Save to history
            LaunchedEffect(Unit) {
                AppState.saveQuizResult(packId, pct, result.correct, result.total)
            }

            Column(
                modifier            = Modifier
                    .padding(inner)
                    .padding(20.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(12.dp))
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress    = { pct / 100f },
                        modifier    = Modifier.size(120.dp),
                        strokeWidth = 10.dp,
                        color       = colour,
                        trackColor  = MaterialTheme.colorScheme.surfaceVariant
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("$pct%", style = MaterialTheme.typography.headlineMedium, color = colour)
                        Text(
                            "${result.correct}/${result.total}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                Spacer(Modifier.height(20.dp))
                Text(
                    when {
                        pct >= 80 -> "Strong performance 🎉"
                        pct >= 60 -> "Good start, keep going!"
                        else      -> "Use this as a diagnostic"
                    },
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    when {
                        pct >= 80 -> "Review the questions you missed, then try the mixed exam."
                        pct >= 60 -> "Re-read the weak chapter sections, then retry this quiz."
                        else      -> "Study the explanations carefully, then attempt again."
                    },
                    style    = MaterialTheme.typography.bodyMedium,
                    color    = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
                )

                val missed = questions.filter { q -> answers[q.id] != q.correctIndex }
                if (missed.isNotEmpty()) {
                    Text(
                        "Missed questions (${missed.size})",
                        style    = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                    )
                    missed.forEach { q ->
                        Card(
                            colors   = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.10f)
                            ),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        ) {
                            Column(Modifier.padding(14.dp)) {
                                Text(q.prompt, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                                Text(
                                    "✓ ${q.choices.getOrElse(q.correctIndex) { "" }}",
                                    style    = MaterialTheme.typography.labelSmall,
                                    color    = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.padding(top = 6.dp)
                                )
                                Text(
                                    q.explanation,
                                    style    = MaterialTheme.typography.bodySmall,
                                    color    = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                }

                Button(
                    onClick  = { answers = emptyMap(); index = 0; finished = false; skipped = emptySet(); reviewingSkipped = false },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Try again") }
                OutlinedButton(
                    onClick  = onBack,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                ) { Text("Back to quizzes") }
                Spacer(Modifier.height(16.dp))
            }
            return@Scaffold
        }

        // ── Question screen ──────────────────────────────────────────────────
        // If reviewing skipped questions, filter to those
        val activeQuestions = if (reviewingSkipped) questions.filter { it.id in skipped } else questions
        val safeIndex   = index.coerceIn(0, activeQuestions.lastIndex)
        val question    = activeQuestions[safeIndex]
        val selected    = answers[question.id]
        val revealed    = selected != null
        val isSkipped   = question.id in skipped
        val progress    = (safeIndex + 1f) / activeQuestions.size

        Column(modifier = Modifier.padding(inner).fillMaxSize()) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Row(
                modifier          = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    if (reviewingSkipped) "Skipped ${safeIndex + 1}/${activeQuestions.size}"
                    else "Question ${safeIndex + 1} of ${questions.size}",
                    style    = MaterialTheme.typography.labelLarge,
                    color    = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                if (skipped.isNotEmpty() && !reviewingSkipped) {
                    Text(
                        "${skipped.size} skipped",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Column(
                modifier  = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (isSkipped) {
                    Text(
                        "Previously skipped",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Text(question.prompt, style = MaterialTheme.typography.titleMedium)

                question.choices.forEachIndexed { choiceIndex, choice ->
                    val isCorrect = choiceIndex == question.correctIndex
                    val isPicked  = selected == choiceIndex
                    val container = when {
                        revealed && isCorrect              -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.22f)
                        revealed && isPicked && !isCorrect -> MaterialTheme.colorScheme.error.copy(alpha = 0.22f)
                        isPicked                           -> MaterialTheme.colorScheme.primary.copy(alpha = 0.16f)
                        else                               -> MaterialTheme.colorScheme.surfaceVariant
                    }
                    Card(
                        colors    = CardDefaults.cardColors(containerColor = container),
                        elevation = CardDefaults.cardElevation(defaultElevation = if (!revealed) 1.dp else 0.dp),
                        modifier  = Modifier.fillMaxWidth().clickable(enabled = !revealed) {
                            answers = answers + (question.id to choiceIndex)
                            if (choiceIndex == question.correctIndex) {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            } else {
                                haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            }
                            // NOTE: do NOT remove from skipped here — that would
                            // immediately shrink activeQuestions and prevent the
                            // correct/wrong feedback from rendering. Removal happens
                            // in the Next button handler after the user sees the result.
                        }
                    ) {
                        Row(
                            modifier          = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(choice, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                            if (revealed) {
                                when {
                                    isCorrect -> Icon(Icons.Outlined.CheckCircle, contentDescription = "Correct",
                                        tint = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.size(20.dp).padding(start = 4.dp))
                                    isPicked  -> Icon(Icons.Outlined.Cancel, contentDescription = "Wrong",
                                        tint = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(20.dp).padding(start = 4.dp))
                                }
                            }
                        }
                    }
                }

                if (revealed) {
                    val ok = selected == question.correctIndex
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (ok)
                                MaterialTheme.colorScheme.secondary.copy(alpha = 0.14f)
                            else
                                MaterialTheme.colorScheme.error.copy(alpha = 0.14f)
                        )
                    ) {
                        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
                            Text(
                                if (ok) "✓ " else "✗ ",
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (ok) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error
                            )
                            Text(
                                if (ok) "Correct. ${question.explanation}" else "Not quite. ${question.explanation}",
                                style    = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
            }

            // Navigation buttons
            Row(
                modifier              = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (safeIndex > 0) {
                    OutlinedButton(
                        onClick  = { index = safeIndex - 1 },
                        modifier = Modifier.weight(1f)
                    ) { Text("Prev") }
                }
                // Skip button — only when not yet answered
                if (!revealed && !reviewingSkipped) {
                    OutlinedButton(
                        onClick  = {
                            skipped = skipped + question.id
                            if (safeIndex < questions.lastIndex) index = safeIndex + 1
                            else {
                                // reached end — enter skip review if any skipped remain unanswered
                                val unanswered = skipped.filter { id -> answers[id] == null }.toSet()
                                if (unanswered.isNotEmpty()) {
                                    skipped = unanswered
                                    reviewingSkipped = true
                                    index = 0
                                } else finished = true
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) { Text("Skip") }
                }
                Button(
                    onClick  = {
                        // Remove current question from skipped now that user has seen the result
                        val currentId = question.id
                        val newSkipped = if (currentId in skipped) skipped - currentId else skipped

                        if (safeIndex == activeQuestions.lastIndex) {
                            if (reviewingSkipped) {
                                skipped = newSkipped
                                // If any skipped questions still unanswered, keep reviewing
                                val stillUnanswered = newSkipped.filter { id -> answers[id] == null }.toSet()
                                if (stillUnanswered.isNotEmpty()) {
                                    skipped = stillUnanswered
                                    index = 0
                                } else {
                                    finished = true
                                }
                            } else {
                                val unanswered = newSkipped.filter { id -> answers[id] == null }.toSet()
                                skipped = newSkipped
                                if (unanswered.isNotEmpty()) {
                                    skipped = unanswered
                                    reviewingSkipped = true
                                    index = 0
                                } else {
                                    finished = true
                                }
                            }
                        } else {
                            skipped = newSkipped
                            index = safeIndex + 1
                        }
                    },
                    enabled  = revealed,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        when {
                            safeIndex == activeQuestions.lastIndex && skipped.any { id -> answers[id] == null } -> "Review skipped"
                            safeIndex == activeQuestions.lastIndex -> "See score"
                            else -> "Next"
                        }
                    )
                }
            }
        }
    }
}

package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.StudyRepository
import com.sushant.pmpstudy.domain.QuizGrader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(packId: String, onBack: () -> Unit) {
    val pack = StudyRepository.pack(packId)
    val questions = pack?.questions.orEmpty()
    val answers = remember(packId) { mutableStateMapOf<String, Int>() }
    var index by rememberSaveable(packId) { mutableIntStateOf(0) }
    var finished by rememberSaveable(packId) { mutableStateOf(false) }

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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
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
        val safeIndex = index.coerceIn(0, questions.lastIndex)
        if (finished) {
            val result = QuizGrader.grade(questions, answers)
            Column(
                modifier = Modifier
                    .padding(inner)
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Text("Results", style = MaterialTheme.typography.headlineMedium)
                Text(
                    "${result.correct} of ${result.total} correct · ${result.percent}%",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    when {
                        result.percent >= 80 -> "Strong. Review missed items, then mix another set."
                        result.percent >= 60 -> "Solid start. Re-read the weak chapter, then retry."
                        else -> "Treat this as a diagnostic. Study the explanations, then try again."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp, bottom = 20.dp)
                )
                Button(
                    onClick = {
                        answers.clear()
                        index = 0
                        finished = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Try again") }
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                ) { Text("Back to quizzes") }
            }
            return@Scaffold
        }

        val question = questions[safeIndex]
        val selected = answers[question.id]
        val revealed = selected != null
        val progress = (safeIndex + 1f) / questions.size

        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
        ) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Text(
                "Question ${safeIndex + 1} of ${questions.size}",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(question.prompt, style = MaterialTheme.typography.titleMedium)
                question.choices.forEachIndexed { choiceIndex, choice ->
                    val isCorrect = choiceIndex == question.correctIndex
                    val isPicked = selected == choiceIndex
                    val container = when {
                        revealed && isCorrect -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.22f)
                        revealed && isPicked && !isCorrect -> MaterialTheme.colorScheme.error.copy(alpha = 0.22f)
                        isPicked -> MaterialTheme.colorScheme.primary.copy(alpha = 0.16f)
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }
                    Card(
                        colors = CardDefaults.cardColors(containerColor = container),
                        modifier = Modifier.fillMaxWidth().clickable(enabled = !revealed) {
                            answers[question.id] = choiceIndex
                        }
                    ) {
                        Text(
                            choice,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(14.dp)
                        )
                    }
                }
                if (revealed) {
                    val ok = selected == question.correctIndex
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (ok) MaterialTheme.colorScheme.secondary.copy(alpha = 0.14f)
                            else MaterialTheme.colorScheme.error.copy(alpha = 0.14f)
                        )
                    ) {
                        Text(
                            if (ok) "Correct. ${question.explanation}" else "Not quite. ${question.explanation}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(14.dp)
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (safeIndex > 0) {
                    OutlinedButton(
                        onClick = { index = safeIndex - 1 },
                        modifier = Modifier.weight(1f)
                    ) { Text("Previous") }
                }
                Button(
                    onClick = {
                        if (safeIndex == questions.lastIndex) finished = true else index = safeIndex + 1
                    },
                    enabled = revealed,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (safeIndex == questions.lastIndex) "See score" else "Next")
                }
            }
        }
    }
}

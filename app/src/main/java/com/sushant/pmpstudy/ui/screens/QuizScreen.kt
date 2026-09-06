package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.StudyRepository
import com.sushant.pmpstudy.domain.QuizGrader

@Composable
fun QuizScreen() {
    val questions = StudyRepository.questions
    val answers = rememberSaveable { mutableStateMapOf<String, Int>() }
    var submitted by rememberSaveable { mutableStateOf(false) }
    val result = if (submitted) QuizGrader.grade(questions, answers) else null

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Text("Practice quiz", style = MaterialTheme.typography.headlineMedium)
            Text(
                "Fifteen original situational and formula items. These are not PMI exam questions.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
            if (result != null) {
                Text(
                    "Score: ${result.correct} / ${result.total}  (${result.percent}%)",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
        itemsIndexed(questions, key = { _, q -> q.id }) { index, question ->
            Column(Modifier.fillMaxWidth()) {
                Text("${index + 1}. ${question.prompt}", style = MaterialTheme.typography.titleSmall)
                question.choices.forEachIndexed { choiceIndex, choice ->
                    val selected = answers[question.id] == choiceIndex
                    FilterChip(
                        selected = selected,
                        onClick = {
                            if (!submitted) answers[question.id] = choiceIndex
                        },
                        label = { Text(choice) },
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
                if (submitted) {
                    val ok = answers[question.id] == question.correctIndex
                    Text(
                        if (ok) "Correct. ${question.explanation}" else "Not quite. ${question.explanation}",
                        color = if (ok) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
        item {
            Button(
                onClick = {
                    if (submitted) {
                        answers.clear()
                        submitted = false
                    } else {
                        submitted = true
                    }
                },
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Text(if (submitted) "Try again" else "Submit answers")
            }
        }
    }
}

package com.sushant.pmpstudy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.QuizQuestion

@Composable
fun InlineQuizSection(
    title: String,
    questions: List<QuizQuestion>,
    answers: Map<String, Int>,
    onAnswer: (questionId: String, choiceIndex: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 4.dp)
        )
        questions.forEachIndexed { index, question ->
            InlineQuizQuestionCard(
                number = index + 1,
                total = questions.size,
                question = question,
                selected = answers[question.id],
                onAnswer = { onAnswer(question.id, it) }
            )
        }
    }
}

@Composable
private fun InlineQuizQuestionCard(
    number: Int,
    total: Int,
    question: QuizQuestion,
    selected: Int?,
    onAnswer: (Int) -> Unit
) {
    val revealed = selected != null
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                "Question $number of $total",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                question.prompt.inlinePrompt(),
                style = MaterialTheme.typography.bodyMedium
            )
            question.choices.forEachIndexed { choiceIndex, choice ->
                val isCorrect = choiceIndex == question.correctIndex
                val isPicked = selected == choiceIndex
                val container = when {
                    revealed && isCorrect -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.22f)
                    revealed && isPicked && !isCorrect -> MaterialTheme.colorScheme.error.copy(alpha = 0.22f)
                    isPicked -> MaterialTheme.colorScheme.primary.copy(alpha = 0.16f)
                    else -> MaterialTheme.colorScheme.background
                }
                Card(
                    colors = CardDefaults.cardColors(containerColor = container),
                    modifier = Modifier.fillMaxWidth().clickable(enabled = !revealed) {
                        onAnswer(choiceIndex)
                    }
                ) {
                    Text(
                        choice,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
            if (revealed) {
                val ok = selected == question.correctIndex
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (ok) {
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.14f)
                        } else {
                            MaterialTheme.colorScheme.error.copy(alpha = 0.14f)
                        }
                    )
                ) {
                    Text(
                        if (ok) "Correct. ${question.explanation}" else "Not quite. ${question.explanation}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}

private fun String.inlinePrompt(): String {
    val prefix = Regex("^Case Study \\d+ \\([^)]+\\):\\s*")
    return prefix.replace(this, "")
}

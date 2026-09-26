package com.sushant.pmpstudy

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dark = androidx.compose.foundation.isSystemInDarkTheme()
            MaterialTheme(
                colorScheme = if (dark) darkColorScheme() else lightColorScheme()
            ) {
                CompMasteryApp(applicationContext)
            }
        }
    }
}

private class ProgressStore(context: Context) {
    private val prefs = context.getSharedPreferences("comp_mastery_progress", Context.MODE_PRIVATE)

    fun completedWeeks(): Set<Int> =
        prefs.getStringSet("completed_weeks", emptySet()).orEmpty().mapNotNull { it.toIntOrNull() }.toSet()

    fun setWeekComplete(week: Int, complete: Boolean) {
        val updated = completedWeeks().toMutableSet()
        if (complete) updated += week else updated -= week
        prefs.edit().putStringSet("completed_weeks", updated.map { it.toString() }.toSet()).apply()
    }

    fun bestQuizScore(): Int = prefs.getInt("best_quiz_score", 0)

    fun saveQuizScore(score: Int) {
        if (score > bestQuizScore()) prefs.edit().putInt("best_quiz_score", score).apply()
    }
}

private enum class AppScreen {
    HOME, LEARN, EXCEL, TOOLKIT, QUIZ
}

@Composable
private fun CompMasteryApp(context: Context) {
    val store = remember { ProgressStore(context) }
    var screen by remember { mutableStateOf(AppScreen.HOME) }
    var selectedWeek by remember { mutableStateOf<Int?>(null) }
    var completed by remember { mutableStateOf(store.completedWeeks()) }

    Scaffold(
        bottomBar = {
            if (selectedWeek == null) {
                NavigationBar {
                    NavigationBarItem(selected = screen == AppScreen.HOME, onClick = { screen = AppScreen.HOME }, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home") })
                    NavigationBarItem(selected = screen == AppScreen.LEARN, onClick = { screen = AppScreen.LEARN }, icon = { Icon(Icons.Default.School, null) }, label = { Text("Learn") })
                    NavigationBarItem(selected = screen == AppScreen.EXCEL, onClick = { screen = AppScreen.EXCEL }, icon = { Icon(Icons.Default.TableChart, null) }, label = { Text("Excel") })
                    NavigationBarItem(selected = screen == AppScreen.TOOLKIT, onClick = { screen = AppScreen.TOOLKIT }, icon = { Icon(Icons.Default.Calculate, null) }, label = { Text("Tools") })
                    NavigationBarItem(selected = screen == AppScreen.QUIZ, onClick = { screen = AppScreen.QUIZ }, icon = { Icon(Icons.Default.Quiz, null) }, label = { Text("Quiz") })
                }
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding).fillMaxSize()) {
            val weekNumber = selectedWeek
            if (weekNumber != null) {
                val week = courseWeeks.first { it.number == weekNumber }
                WeekDetailScreen(
                    week = week,
                    complete = weekNumber in completed,
                    onBack = { selectedWeek = null },
                    onCompleteChanged = { checked ->
                        store.setWeekComplete(weekNumber, checked)
                        completed = store.completedWeeks()
                    }
                )
            } else {
                when (screen) {
                    AppScreen.HOME -> HomeScreen(
                        completed = completed,
                        bestQuizScore = store.bestQuizScore(),
                        onContinue = {
                            val next = courseWeeks.firstOrNull { it.number !in completed }?.number ?: 10
                            selectedWeek = next
                        },
                        onOpenLearn = { screen = AppScreen.LEARN }
                    )
                    AppScreen.LEARN -> LearnScreen(completed) { selectedWeek = it }
                    AppScreen.EXCEL -> ExcelLabScreen()
                    AppScreen.TOOLKIT -> ToolkitScreen()
                    AppScreen.QUIZ -> QuizScreen(
                        bestScore = store.bestQuizScore(),
                        onScore = { store.saveQuizScore(it) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ScreenHeader(title: String, subtitle: String) {
    Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp)) {
        Text(title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))
        Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun HomeScreen(
    completed: Set<Int>,
    bestQuizScore: Int,
    onContinue: () -> Unit,
    onOpenLearn: () -> Unit
) {
    val progress = completed.size / 10f
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            ScreenHeader(
                "CompMastery",
                "Advanced Compensation & Total Rewards • 10-week practical course"
            )
        }
        item {
            Card(Modifier.padding(horizontal = 20.dp).fillMaxWidth()) {
                Column(Modifier.padding(20.dp)) {
                    Text("Your learning progress", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(12.dp))
                    LinearProgressIndicator(progress = { progress }, modifier = Modifier.fillMaxWidth())
                    Spacer(Modifier.height(8.dp))
                    Text("${completed.size} of 10 weeks completed")
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = onContinue, modifier = Modifier.fillMaxWidth()) {
                        Text(if (completed.size == 10) "Review Course" else "Continue Learning")
                    }
                }
            }
        }
        item {
            Spacer(Modifier.height(14.dp))
            Row(
                Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MetricCard("10", "Weeks", Modifier.weight(1f))
                MetricCard("10", "Excel Labs", Modifier.weight(1f))
                MetricCard("20", "Quiz Qs", Modifier.weight(1f))
            }
        }
        item {
            Spacer(Modifier.height(14.dp))
            Card(Modifier.padding(horizontal = 20.dp).fillMaxWidth()) {
                Column(Modifier.padding(20.dp)) {
                    Text("Assessment", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text("Best score: $bestQuizScore / ${allQuizQuestions.size}")
                    Spacer(Modifier.height(8.dp))
                    Text("Use the Quiz tab after each few weeks to check whether you can apply the concepts, not only recognize the terminology.")
                }
            }
        }
        item {
            Spacer(Modifier.height(14.dp))
            Card(Modifier.padding(horizontal = 20.dp).fillMaxWidth()) {
                Column(Modifier.padding(20.dp)) {
                    Text("How to use this course", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(8.dp))
                    Text("1. Study one week at a time.\n2. Complete the case study and assignment in your own Excel workbook.\n3. Practice the related formula in Excel Lab.\n4. Use Toolkit calculators to verify your manual work.\n5. Mark a week complete only after doing the exercise.")
                    Spacer(Modifier.height(12.dp))
                    OutlinedButton(onClick = onOpenLearn, modifier = Modifier.fillMaxWidth()) { Text("View all 10 weeks") }
                }
            }
        }
    }
}

@Composable
private fun MetricCard(value: String, label: String, modifier: Modifier = Modifier) {
    Card(modifier) {
        Column(Modifier.padding(vertical = 16.dp, horizontal = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(label, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
private fun LearnScreen(completed: Set<Int>, onWeek: (Int) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(bottom = 24.dp)) {
        item { ScreenHeader("10-Week Course", "Senior-level compensation learning for experienced HR professionals") }
        items(courseWeeks) { week ->
            Card(
                onClick = { onWeek(week.number) },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp).fillMaxWidth()
            ) {
                Row(Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = MaterialTheme.shapes.medium, tonalElevation = 3.dp) {
                        Text("W${week.number}", modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp), fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) {
                        Text(week.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(4.dp))
                        Text(week.objective, style = MaterialTheme.typography.bodySmall, maxLines = 3)
                    }
                    if (week.number in completed) {
                        Icon(Icons.Default.CheckCircle, contentDescription = "Completed", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeekDetailScreen(
    week: CourseWeek,
    complete: Boolean,
    onBack: () -> Unit,
    onCompleteChanged: (Boolean) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Week ${week.number}") },
            navigationIcon = {
                IconButton(onClick = onBack) { Icon(Icons.Default.KeyboardArrowLeft, "Back") }
            }
        )
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 28.dp)
        ) {
            item {
                Text(week.title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(week.objective, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(18.dp))
            }
            itemsIndexed(week.lessons) { lessonIndex, lesson ->
                val detail = deepDiveFor(week.number, lessonIndex)
                Card(Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
                    Column(Modifier.padding(18.dp)) {
                        Text(lesson.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(10.dp))
                        Text(lesson.body, style = MaterialTheme.typography.bodyLarge)
                        if (detail != null) {
                            Spacer(Modifier.height(18.dp))
                            HorizontalDivider()
                            DeepDiveSection("Why this matters", detail.whyItMatters)
                            DeepDiveSection("Step-by-step method", detail.method)
                            DeepDiveSection("Worked example", detail.workedExample)
                            DeepDiveSection("Practical exercise", detail.practice)
                            DeepDiveSection("Common mistakes", detail.pitfalls)
                            DeepDiveSection("How to discuss it with managers / HRBPs", detail.partnerConversation)
                        }
                    }
                }
            }
            item {
                SectionCard("Case Study", week.caseStudy)
                Spacer(Modifier.height(12.dp))
                SectionCard("Assignment", week.assignment)
                Spacer(Modifier.height(12.dp))
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(18.dp)) {
                        Text("Knowledge Check", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(8.dp))
                        week.knowledgeCheck.forEachIndexed { index, question ->
                            Text("${index + 1}. $question", modifier = Modifier.padding(vertical = 5.dp))
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { onCompleteChanged(!complete) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (complete) "Mark as Not Completed" else "Mark Week ${week.number} Complete")
                }
            }
        }
    }
}

@Composable
private fun SectionCard(title: String, body: String) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(18.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            Text(body)
        }
    }
}

@Composable
private fun DeepDiveSection(title: String, body: String) {
    Spacer(Modifier.height(16.dp))
    Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
    Spacer(Modifier.height(6.dp))
    Text(body, style = MaterialTheme.typography.bodyLarge)
}

@Composable
private fun ExcelLabScreen() {
    LazyColumn(contentPadding = PaddingValues(bottom = 24.dp)) {
        item { ScreenHeader("Excel Lab", "Practice the calculations and analysis used in real compensation work") }
        itemsIndexed(excelLabs) { index, lab ->
            Card(Modifier.padding(horizontal = 20.dp, vertical = 6.dp).fillMaxWidth()) {
                Column(Modifier.padding(18.dp)) {
                    Text("Lab ${index + 1}: ${lab.title}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text(lab.skill, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(10.dp))
                    Text("Formula", style = MaterialTheme.typography.labelLarge)
                    Surface(tonalElevation = 2.dp, shape = MaterialTheme.shapes.small, modifier = Modifier.fillMaxWidth()) {
                        Text(lab.formula, modifier = Modifier.padding(12.dp), style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(Modifier.height(10.dp))
                    Text("Exercise", style = MaterialTheme.typography.labelLarge)
                    Text(lab.exercise)

                    excelLabDeepDives.getOrNull(index)?.let { detail ->
                        Spacer(Modifier.height(16.dp))
                        HorizontalDivider()
                        DeepDiveSection("Work scenario", detail.scenario)
                        DeepDiveSection("Step-by-step in Excel", detail.steps)
                        DeepDiveSection("Worked sample", detail.sample)
                        DeepDiveSection("Validation checks", detail.validation)
                        DeepDiveSection("Challenge", detail.challenge)
                    }
                }
            }
        }
        item {
            Card(Modifier.padding(horizontal = 20.dp, vertical = 12.dp).fillMaxWidth()) {
                Column(Modifier.padding(18.dp)) {
                    Text("Capstone Excel Workbook", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(8.dp))
                    Text(capstoneGuide, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
private fun ToolkitScreen() {
    var salary by remember { mutableStateOf("") }
    var midpoint by remember { mutableStateOf("") }
    var minimum by remember { mutableStateOf("") }
    var maximum by remember { mutableStateOf("") }
    var increasePct by remember { mutableStateOf("") }
    var bonusPct by remember { mutableStateOf("") }
    var companyFactor by remember { mutableStateOf("100") }
    var individualFactor by remember { mutableStateOf("100") }

    val s = number(salary)
    val mid = number(midpoint)
    val min = number(minimum)
    val max = number(maximum)
    val inc = number(increasePct)
    val bonus = number(bonusPct)
    val cf = number(companyFactor)
    val indf = number(individualFactor)

    val compa = if (s != null && mid != null && mid != 0.0) s / mid else null
    val penetration = if (s != null && min != null && max != null && max != min) (s - min) / (max - min) else null
    val newSalary = if (s != null && inc != null) s * (1 + inc / 100.0) else null
    val bonusPayout = if (s != null && bonus != null && cf != null && indf != null) s * (bonus / 100.0) * (cf / 100.0) * (indf / 100.0) else null

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        ScreenHeader("Compensation Toolkit", "Use these calculators to verify Excel work and understand the formulas")
        Column(Modifier.padding(horizontal = 20.dp, vertical = 4.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Salary positioning", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    MoneyField("Current salary", salary) { salary = it }
                    MoneyField("Range minimum", minimum) { minimum = it }
                    MoneyField("Range midpoint", midpoint) { midpoint = it }
                    MoneyField("Range maximum", maximum) { maximum = it }
                    ResultRow("Compa-Ratio", compa?.let { DecimalFormat("0.000").format(it) } ?: "—")
                    ResultRow("Range Penetration", penetration?.let { "${DecimalFormat("0.0").format(it * 100)}%" } ?: "—")
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Merit / salary increase", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    PercentField("Increase %", increasePct) { increasePct = it }
                    ResultRow("New salary", newSalary?.let { currency(it) } ?: "—")
                    ResultRow("Increase amount", if (newSalary != null && s != null) currency(newSalary - s) else "—")
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Bonus payout", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    PercentField("Target bonus %", bonusPct) { bonusPct = it }
                    PercentField("Company factor %", companyFactor) { companyFactor = it }
                    PercentField("Individual factor %", individualFactor) { individualFactor = it }
                    ResultRow("Calculated payout", bonusPayout?.let { currency(it) } ?: "—")
                    Text("Formula: Base × Target Bonus × Company Factor × Individual Factor", style = MaterialTheme.typography.bodySmall)
                }
            }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp)) {
                    Text("Interpretation reminder", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(8.dp))
                    Text("A calculator produces a number, not a compensation decision. Always add job level, experience, performance, market position, peer equity, skill scarcity, budget and policy before recommending action.")
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun MoneyField(label: String, value: String, onValue: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValue,
        label = { Text(label) },
        placeholder = { Text("e.g. 2000000") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun PercentField(label: String, value: String, onValue: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValue,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        suffix = { Text("%") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ResultRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(label)
        Text(value, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
    }
}

private fun number(text: String): Double? = text.replace(",", "").trim().toDoubleOrNull()
private fun currency(value: Double): String = "₹" + DecimalFormat("#,##,##0.00").format(value)

@Composable
private fun QuizScreen(bestScore: Int, onScore: (Int) -> Unit) {
    var selectedWeek by remember { mutableIntStateOf(0) }
    val questions = if (selectedWeek == 0) allQuizQuestions else allQuizQuestions.filter { it.week == selectedWeek }
    var index by remember(selectedWeek) { mutableIntStateOf(0) }
    var selected by remember(selectedWeek, index) { mutableStateOf<Int?>(null) }
    var score by remember(selectedWeek) { mutableIntStateOf(0) }
    var answered by remember(selectedWeek, index) { mutableStateOf(false) }
    var finished by remember(selectedWeek) { mutableStateOf(false) }

    if (finished) {
        Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(Icons.Default.CheckCircle, null, modifier = Modifier.size(64.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(16.dp))
            Text("Assessment complete", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("$score / ${questions.size}", style = MaterialTheme.typography.headlineMedium)
            val pct = if (questions.isNotEmpty()) score * 100 / questions.size else 0
            Text("$pct%")
            Spacer(Modifier.height(10.dp))
            Text(if (pct >= 80) "Strong result. Review any missed explanations, then continue applying the concepts in Excel." else "Review the related weeks and repeat the practical exercises before retaking the quiz.", textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            Spacer(Modifier.height(20.dp))
            Button(onClick = {
                index = 0; selected = null; score = 0; answered = false; finished = false
            }) { Text("Retake") }
            if (selectedWeek == 0) {
                Spacer(Modifier.height(8.dp))
                Text("Best full-course score: ${maxOf(bestScore, score)} / ${allQuizQuestions.size}", style = MaterialTheme.typography.bodySmall)
            }
        }
        return
    }

    Column(Modifier.fillMaxSize()) {
        ScreenHeader("Knowledge Assessment", "Test application of the compensation concepts")
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(selected = selectedWeek == 0, onClick = { selectedWeek = 0 }, label = { Text("All") })
            }
            items((1..10).toList()) { week ->
                FilterChip(selected = selectedWeek == week, onClick = { selectedWeek = week }, label = { Text("W$week") })
            }
        }
        if (questions.isEmpty()) return
        val q = questions[index]
        Column(
            Modifier.fillMaxSize().padding(20.dp).verticalScroll(rememberScrollState())
        ) {
            Text("Question ${index + 1} of ${questions.size}", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Text("Week ${q.week}", style = MaterialTheme.typography.labelMedium)
            Spacer(Modifier.height(10.dp))
            Text(q.question, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(14.dp))
            q.options.forEachIndexed { optionIndex, option ->
                OutlinedButton(
                    onClick = { if (!answered) selected = optionIndex },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    enabled = !answered
                ) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = selected == optionIndex, onClick = null)
                        Spacer(Modifier.width(8.dp))
                        Text(option, modifier = Modifier.weight(1f))
                    }
                }
            }
            if (!answered) {
                Button(
                    onClick = {
                        answered = true
                        if (selected == q.correctIndex) score++
                    },
                    enabled = selected != null,
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
                ) { Text("Check Answer") }
            } else {
                Card(Modifier.fillMaxWidth().padding(top = 12.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text(if (selected == q.correctIndex) "Correct" else "Review this concept", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        Spacer(Modifier.height(6.dp))
                        Text(q.explanation)
                    }
                }
                Button(
                    onClick = {
                        if (index == questions.lastIndex) {
                            finished = true
                            if (selectedWeek == 0) onScore(score)
                        } else {
                            index++
                            selected = null
                            answered = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                ) {
                    Text(if (index == questions.lastIndex) "Finish" else "Next Question")
                }
            }
        }
    }
}

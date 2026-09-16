package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.StudyRepository
import com.sushant.pmpstudy.domain.EarnedValueMath
import com.sushant.pmpstudy.ui.components.ScreenHeader
import java.util.Locale

@Composable
fun FormulasScreen(onStartDrill: () -> Unit) {
    var ev by rememberSaveable { mutableStateOf("80000") }
    var pv by rememberSaveable { mutableStateOf("100000") }
    var ac by rememberSaveable { mutableStateOf("90000") }
    var bac by rememberSaveable { mutableStateOf("200000") }
    // The four inputs as they stood when Calculate was last pressed. Saving this
    // rather than the formatted rows carries the results through a rotation while
    // still leaving them alone as the user edits the fields.
    var submitted by rememberSaveable { mutableStateOf<String?>(null) }
    val (metrics, error) = remember(submitted) { evmRows(submitted) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            ScreenHeader(
                title = "Formulas",
                subtitle = "Run an EVM example, then memorize SPI, CPI, EAC, and PERT."
            )
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.16f)
                ),
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
            ) {
                Column(Modifier.padding(14.dp)) {
                    Text("Formula drill", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "Test your recall of all ${StudyRepository.formulaDrill.questions.size} formulas.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp, bottom = 10.dp)
                    )
                    Button(onClick = onStartDrill, modifier = Modifier.fillMaxWidth()) {
                        Text("Start drill")
                    }
                }
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("EVM calculator", style = MaterialTheme.typography.titleMedium)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        NumberField("EV", ev, { ev = it }, Modifier.weight(1f))
                        NumberField("PV", pv, { pv = it }, Modifier.weight(1f))
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        NumberField("AC", ac, { ac = it }, Modifier.weight(1f))
                        NumberField("BAC", bac, { bac = it }, Modifier.weight(1f))
                    }
                    Button(
                        onClick = { submitted = "$ev|$pv|$ac|$bac" },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Calculate") }
                    if (error != null) {
                        Text(error, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                    }
                    if (metrics.isNotEmpty()) {
                        metrics.chunked(2).forEach { pair ->
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                                pair.forEach { (label, value) ->
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(vertical = 4.dp)
                                    ) {
                                        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        Text(value, style = MaterialTheme.typography.titleMedium)
                                    }
                                }
                                if (pair.size == 1) Spacer(Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
        items(StudyRepository.formulas, key = { it.name }) { formula ->
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp)) {
                    Text(formula.name, style = MaterialTheme.typography.titleSmall)
                    Text(
                        formula.expression,
                        style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily.Monospace),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    Text(
                        formula.meaning,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }
    }
}

/**
 * Formatted EVM rows for the pipe-joined inputs captured on the last Calculate
 * press, or the message explaining why they could not be computed.
 */
private fun evmRows(submitted: String?): Pair<List<Pair<String, String>>, String?> {
    val blank = emptyList<Pair<String, String>>()
    if (submitted == null) return blank to null
    return runCatching {
        val f = submitted.split('|')
        val result = EarnedValueMath.compute(
            ev = f[0].toDouble(),
            pv = f[1].toDouble(),
            ac = f[2].toDouble(),
            bac = f[3].toDouble()
        )
        val fmt = { n: Double -> String.format(Locale.US, "%,.2f", n) }
        listOf(
            "SV" to fmt(result.sv),
            "CV" to fmt(result.cv),
            "SPI" to fmt(result.spi),
            "CPI" to fmt(result.cpi),
            "EAC" to fmt(result.eacTypical),
            "ETC" to fmt(result.etc),
            "VAC" to fmt(result.vac),
            "TCPI" to fmt(result.tcpi)
        )
    }.fold(
        onSuccess = { rows -> rows to null },
        onFailure = { e ->
            // NumberFormatException extends IllegalArgumentException, so it has to be
            // matched first — otherwise an empty field surfaces the raw JDK message
            // ("For input string: \"\"") instead of the guidance below.
            blank to when (e) {
                is NumberFormatException -> "Enter numbers only."
                is IllegalArgumentException -> e.message ?: "Invalid input."
                else -> "Calculation failed. Check inputs."
            }
        }
    )
}

@Composable
private fun NumberField(
    label: String,
    value: String,
    onValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValue(it.filter { ch -> ch.isDigit() || ch == '.' }) },
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier
    )
}

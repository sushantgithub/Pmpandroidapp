package com.sushant.pmpstudy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sushant.pmpstudy.data.StudyRepository
import com.sushant.pmpstudy.domain.EarnedValueMath
import java.util.Locale

@Composable
fun FormulasScreen() {
    var ev by remember { mutableStateOf("80000") }
    var pv by remember { mutableStateOf("100000") }
    var ac by remember { mutableStateOf("90000") }
    var bac by remember { mutableStateOf("200000") }
    var output by remember { mutableStateOf("Tap Calculate to score this scenario.") }

    Column(Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 12.dp)) {
        Text("Formulas", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Standard earned-value identities used on the exam. Work a numeric example, then scan the sheet.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
        )
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            NumberField("EV", ev, { ev = it }, Modifier.weight(1f))
            NumberField("PV", pv, { pv = it }, Modifier.weight(1f))
        }
        Row(Modifier.fillMaxWidth().padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            NumberField("AC", ac, { ac = it }, Modifier.weight(1f))
            NumberField("BAC", bac, { bac = it }, Modifier.weight(1f))
        }
        Button(
            onClick = {
                output = runCatching {
                    val result = EarnedValueMath.compute(
                        ev = ev.toDouble(),
                        pv = pv.toDouble(),
                        ac = ac.toDouble(),
                        bac = bac.toDouble()
                    )
                    val fmt = { n: Double -> String.format(Locale.US, "%,.2f", n) }
                    buildString {
                        appendLine("SV ${fmt(result.sv)}   CV ${fmt(result.cv)}")
                        appendLine("SPI ${fmt(result.spi)}   CPI ${fmt(result.cpi)}")
                        appendLine("EAC (typical) ${fmt(result.eacTypical)}")
                        appendLine("ETC ${fmt(result.etc)}   VAC ${fmt(result.vac)}")
                        append("TCPI (to BAC) ${fmt(result.tcpi)}")
                    }
                }.getOrElse { "Enter nonzero EV/PV/AC/BAC numbers. BAC must differ from AC." }
            },
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text("Calculate")
        }
        Text(
            output,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 12.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(StudyRepository.formulas, key = { it.name }) { formula ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(14.dp)) {
                        Text(formula.name, style = MaterialTheme.typography.titleSmall)
                        Text(formula.expression, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 4.dp))
                        Text(
                            formula.meaning,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
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

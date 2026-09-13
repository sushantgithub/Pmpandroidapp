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
fun FormulasScreen() {
    var ev by remember { mutableStateOf("80000") }
    var pv by remember { mutableStateOf("100000") }
    var ac by remember { mutableStateOf("90000") }
    var bac by remember { mutableStateOf("200000") }
    var metrics by remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }

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
                        onClick = {
                            runCatching {
                                val result = EarnedValueMath.compute(
                                    ev = ev.toDouble(),
                                    pv = pv.toDouble(),
                                    ac = ac.toDouble(),
                                    bac = bac.toDouble()
                                )
                                val fmt = { n: Double -> String.format(Locale.US, "%,.2f", n) }
                                error = null
                                metrics = listOf(
                                    "SV" to fmt(result.sv),
                                    "CV" to fmt(result.cv),
                                    "SPI" to fmt(result.spi),
                                    "CPI" to fmt(result.cpi),
                                    "EAC" to fmt(result.eacTypical),
                                    "ETC" to fmt(result.etc),
                                    "VAC" to fmt(result.vac),
                                    "TCPI" to fmt(result.tcpi)
                                )
                            }.onFailure {
                                metrics = emptyList()
                                error = "Enter valid EV, PV, AC, and BAC. BAC must differ from AC."
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text("Calculate") }
                    if (error != null) {
                        Text(error!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
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

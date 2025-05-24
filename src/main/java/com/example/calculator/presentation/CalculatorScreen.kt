package com.example.calculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.calculator.domain.Calculator
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CalculatorScreen(viewModel: HistoryViewModel = hiltViewModel()) {
    val calculator = Calculator()
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Calculator", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        // Display for current expression and result
        Text(text = "Expression: $expression", style = MaterialTheme.typography.h6)
        Text(text = "Result: $result", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))
        // Buttons for numbers and operations
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { expression += "1" }) {
                Text("1")
            }
            Button(onClick = { expression += "2" }) {
                Text("2")
            }
            Button(onClick = { expression += "3" }) {
                Text("3")
            }
        }
        // Add more rows for other numbers and operations
        Button(onClick = {
            try {
                val parts = expression.split("+")
                if (parts.size == 2) {
                    val a = parts[0].toDouble()
                    val b = parts[1].toDouble()
                    result = calculator.add(a, b).toString()
                    viewModel.addCalculation(expression, result)
                }
            } catch (e: Exception) {
                result = "Error"
            }
        }) {
            Text("=")
        }
    }
} 
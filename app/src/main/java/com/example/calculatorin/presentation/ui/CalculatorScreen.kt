package com.example.calculatorin.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.calculatorin.presentation.viewmodel.CalculatorViewModel

@Composable
fun CalculatorScreen(
    onNavigateToHistory: () -> Unit,
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val expression by viewModel.expression.collectAsState()
    val result by viewModel.result.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = expression.takeLast(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.End,
                fontSize = 28.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = result,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.End,
                fontSize = 48.sp,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val buttonModifier = Modifier
                .size(80.dp)
                .padding(4.dp)

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CalculatorButton("AC", buttonModifier, onClick = { viewModel.onClearClick() }, color = Color(0xFFB39DDB))
                CalculatorButton("⌫", buttonModifier, onClick = { viewModel.onDeleteClick() }, color = Color(0xFFB39DDB))
                CalculatorButton("÷", buttonModifier, onClick = { viewModel.onOperatorClick("÷") }, color = Color(0xFF7E57C2))
                CalculatorButton("+", buttonModifier, onClick = { viewModel.onOperatorClick("+") }, color = Color(0xFF7E57C2))
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CalculatorButton("7", buttonModifier) { viewModel.onDigitClick("7") }
                CalculatorButton("8", buttonModifier) { viewModel.onDigitClick("8") }
                CalculatorButton("9", buttonModifier) { viewModel.onDigitClick("9") }
                CalculatorButton("-", buttonModifier, onClick = { viewModel.onOperatorClick("-") }, color = Color(0xFF7E57C2))
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CalculatorButton("4", buttonModifier) { viewModel.onDigitClick("4") }
                CalculatorButton("5", buttonModifier) { viewModel.onDigitClick("5") }
                CalculatorButton("6", buttonModifier) { viewModel.onDigitClick("6") }
                CalculatorButton("×", buttonModifier, onClick = { viewModel.onOperatorClick("×") }, color = Color(0xFF7E57C2))
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CalculatorButton("1", buttonModifier) { viewModel.onDigitClick("1") }
                CalculatorButton("2", buttonModifier) { viewModel.onDigitClick("2") }
                CalculatorButton("3", buttonModifier) { viewModel.onDigitClick("3") }
                CalculatorButton(".", buttonModifier) { viewModel.onDigitClick(".") }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CalculatorButton("0", Modifier.weight(2f).height(80.dp).padding(4.dp)) { viewModel.onDigitClick("0") }
                CalculatorButton("=", Modifier.weight(2f).height(80.dp).padding(4.dp), color = Color(0xFF9575CD)) { viewModel.onEqualsClick() }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToHistory,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Histórico", fontSize = 20.sp)
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text = text, fontSize = 28.sp, color = Color.White, modifier = Modifier.wrapContentSize(Alignment.Center)
        )
    }
}
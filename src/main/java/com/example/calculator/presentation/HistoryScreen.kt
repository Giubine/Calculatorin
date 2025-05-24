package com.example.calculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.calculator.data.CalculationEntity
import kotlinx.coroutines.launch

@Composable
fun HistoryScreen(viewModel: HistoryViewModel = hiltViewModel()) {
    val calculations = viewModel.calculations.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "History", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(calculations.value) { calculation ->
                Text(text = "${calculation.expression} = ${calculation.result}", style = MaterialTheme.typography.body1)
                Divider()
            }
        }
        Button(onClick = { scope.launch { viewModel.clearHistory() } }) {
            Text("Clear History")
        }
    }
} 
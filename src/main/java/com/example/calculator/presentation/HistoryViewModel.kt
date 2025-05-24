package com.example.calculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.data.CalculationDao
import com.example.calculator.data.CalculationEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val calculationDao: CalculationDao
) : ViewModel() {

    private val _calculations = MutableStateFlow<List<CalculationEntity>>(emptyList())
    val calculations: StateFlow<List<CalculationEntity>> = _calculations

    init {
        viewModelScope.launch {
            _calculations.value = calculationDao.getAllCalculations()
        }
    }

    fun addCalculation(expression: String, result: String) {
        viewModelScope.launch {
            val calculation = CalculationEntity(
                expression = expression,
                result = result,
                timestamp = System.currentTimeMillis()
            )
            calculationDao.insertCalculation(calculation)
            _calculations.value = calculationDao.getAllCalculations()
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            calculationDao.clearAllCalculations()
            _calculations.value = emptyList()
        }
    }
} 
package com.example.calculatorin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculatorin.data.local.CalculationDao
import com.example.calculatorin.data.local.CalculationEntity
import com.example.calculatorin.domain.model.Calculator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel

class CalculatorViewModel @Inject constructor(
    private val calculator: Calculator,
    private val calculationDao: CalculationDao
) : ViewModel() {

    private val _expression = MutableStateFlow("")
    val expression: StateFlow<String> = _expression.asStateFlow()

    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result.asStateFlow()

    private var lastWasEquals = false

    fun onDigitClick(digit: String) {
        if (lastWasEquals) {
            _expression.value = digit
            lastWasEquals = false
        } else if (_expression.value.length < 20) {
            _expression.value += digit
        }
        calculateResult()
    }

    fun onOperatorClick(operator: String) {
        if (_expression.value.isNotEmpty() && !_expression.value.endsWith(" ")) {
            _expression.value += " $operator "
        }
    }

    fun onClearClick() {
        _expression.value = ""
        _result.value = ""
        lastWasEquals = false
    }

    fun onDeleteClick() {
        if (_expression.value.isNotEmpty()) {
            _expression.value = if (_expression.value.endsWith(" ")) {
                _expression.value.dropLast(3)
            } else {
                _expression.value.dropLast(1)
            }
            calculateResult()
        }
        lastWasEquals = false
    }

    fun onEqualsClick() {
        calculateResult()
        if (_result.value.isNotEmpty()) {
            saveCalculation()
            lastWasEquals = true
        }
    }

    private fun calculateResult() {
        if (_expression.value.isNotEmpty()) {
            calculator.calculate(_expression.value)
                .onSuccess { result ->
                    _result.value = if (result == result.toLong().toDouble()) {
                        result.toLong().toString()
                    } else {
                        String.format("%.6f", result).trimEnd('0').trimEnd('.')
                    }
                }
                .onFailure {
                    _result.value = "Error"
                }
        } else {
            _result.value = ""
        }
    }

    private fun saveCalculation() {
        viewModelScope.launch {
            calculationDao.insertCalculation(
                CalculationEntity(
                    expression = _expression.value,
                    result = _result.value
                )
            )
        }
    }
} 
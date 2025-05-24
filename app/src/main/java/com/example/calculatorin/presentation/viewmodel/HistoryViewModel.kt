package com.example.calculatorin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculatorin.data.local.CalculationDao
import com.example.calculatorin.data.local.CalculationEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val calculationDao: CalculationDao
) : ViewModel() {

    val calculations: Flow<List<CalculationEntity>> = calculationDao.getAllCalculations()

    fun clearHistory() {
        viewModelScope.launch {
            calculationDao.deleteAllCalculations()
        }
    }
} 
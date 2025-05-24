package com.example.calculatorin.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculationDao {
    @Query("SELECT * FROM calculations ORDER BY timestamp DESC")
    fun getAllCalculations(): Flow<List<CalculationEntity>>

    @Insert
    suspend fun insertCalculation(calculation: CalculationEntity)

    @Query("DELETE FROM calculations")
    suspend fun deleteAllCalculations()
} 
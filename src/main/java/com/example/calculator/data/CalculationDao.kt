package com.example.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculationDao {
    @Insert
    suspend fun insertCalculation(calculation: CalculationEntity)

    @Query("SELECT * FROM calculations ORDER BY timestamp DESC")
    suspend fun getAllCalculations(): List<CalculationEntity>

    @Query("DELETE FROM calculations")
    suspend fun clearAllCalculations()
} 
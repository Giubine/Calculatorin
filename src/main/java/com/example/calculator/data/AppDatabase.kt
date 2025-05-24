package com.example.calculator.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CalculationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun calculationDao(): CalculationDao
} 
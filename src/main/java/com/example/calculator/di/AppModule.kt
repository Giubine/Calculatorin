package com.example.calculator.di

import android.content.Context
import androidx.room.Room
import com.example.calculator.data.AppDatabase
import com.example.calculator.data.CalculationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "calculator_db"
        ).build()
    }

    @Provides
    fun provideCalculationDao(database: AppDatabase): CalculationDao {
        return database.calculationDao()
    }
} 
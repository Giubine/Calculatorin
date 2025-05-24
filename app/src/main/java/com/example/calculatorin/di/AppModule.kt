package com.example.calculatorin.di

import android.content.Context
import androidx.room.Room
import com.example.calculatorin.data.local.AppDatabase
import com.example.calculatorin.data.local.CalculationDao
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
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "calculator_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCalculationDao(database: AppDatabase): CalculationDao {
        return database.calculationDao()
    }
} 
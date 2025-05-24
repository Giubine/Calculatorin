package com.example.calculatorin.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CalculatorTest {
    private lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @Test
    fun `test addition`() {
        val result = calculator.calculate("2 + 3")
        assertTrue(result.isSuccess)
        assertEquals(5.0, result.getOrNull(), 0.0)
    }

    @Test
    fun `test subtraction`() {
        val result = calculator.calculate("5 - 3")
        assertTrue(result.isSuccess)
        assertEquals(2.0, result.getOrNull(), 0.0)
    }

    @Test
    fun `test multiplication`() {
        val result = calculator.calculate("4 × 3")
        assertTrue(result.isSuccess)
        assertEquals(12.0, result.getOrNull(), 0.0)
    }

    @Test
    fun `test division`() {
        val result = calculator.calculate("10 ÷ 2")
        assertTrue(result.isSuccess)
        assertEquals(5.0, result.getOrNull(), 0.0)
    }

    @Test
    fun `test division by zero`() {
        val result = calculator.calculate("10 ÷ 0")
        assertTrue(result.isFailure)
    }

    @Test
    fun `test empty expression`() {
        val result = calculator.calculate("")
        assertTrue(result.isSuccess)
        assertEquals(0.0, result.getOrNull(), 0.0)
    }

    @Test
    fun `test decimal numbers`() {
        val result = calculator.calculate("2.5 + 3.5")
        assertTrue(result.isSuccess)
        assertEquals(6.0, result.getOrNull(), 0.0)
    }
} 
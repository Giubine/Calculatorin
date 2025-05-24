package com.example.calculator.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {

    private val calculator = Calculator()

    @Test
    fun testAddition() {
        assertEquals(5.0, calculator.add(2.0, 3.0), 0.0)
    }

    @Test
    fun testSubtraction() {
        assertEquals(1.0, calculator.subtract(3.0, 2.0), 0.0)
    }

    @Test
    fun testMultiplication() {
        assertEquals(6.0, calculator.multiply(2.0, 3.0), 0.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testDivisionByZero() {
        calculator.divide(1.0, 0.0)
    }

    @Test
    fun testDivision() {
        assertEquals(2.0, calculator.divide(6.0, 3.0), 0.0)
    }
} 
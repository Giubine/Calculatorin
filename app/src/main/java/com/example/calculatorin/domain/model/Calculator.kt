package com.example.calculatorin.domain.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Calculator @Inject constructor() {
    fun calculate(expression: String): Result<Double> {
        return try {
            val result = evaluateExpression(expression)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun evaluateExpression(expression: String): Double {
        val tokens = expression.split(" ").filter { it.isNotBlank() }
        if (tokens.isEmpty()) return 0.0

        var result = tokens[0].toDouble()
        var i = 1

        while (i < tokens.size) {
            when (tokens[i]) {
                "+" -> result += tokens[i + 1].toDouble()
                "-" -> result -= tokens[i + 1].toDouble()
                "×" -> result *= tokens[i + 1].toDouble()
                "÷" -> {
                    val divisor = tokens[i + 1].toDouble()
                    if (divisor == 0.0) throw ArithmeticException("Division by zero")
                    result /= divisor
                }
            }
            i += 2
        }

        return result
    }
} 
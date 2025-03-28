package com.example.compose.data

object BmiCalculator {
    fun calculateBmi(weight: Float, height: Float): Float {
        return weight / ((height / 100) * (height / 100))
    }
}

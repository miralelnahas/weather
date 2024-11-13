package com.golyv.ui.utils

object Helper {
    fun kalvinToCelsius(kalvin: Double) = String.format("%.1f", kalvin - 273.15)

    fun kalvinToFahrenheit(kalvin: Double) = String.format("%.1f", (kalvin - 273.15) * 9 / 5 + 32)
}
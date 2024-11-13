package com.golyv.domain.models

data class WeatherDetails(
    val temp: Double,
    val humidity: Double,
    val weatherIcon: String,
    val windSpeed: Double
)
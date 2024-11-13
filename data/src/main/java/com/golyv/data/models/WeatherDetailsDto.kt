package com.golyv.data.models

data class WeatherDetailsDto(
    val windSpeed: Double,
    val temp: Double,
    val humidity: Double,
    val weatherIcon: String
)
package com.golyv.data.network.weather

data class WeatherResponse(
    val main: WeatherDetails,
    val weather: List<Weather>,
    val wind: Wind
)

data class Weather(
    val icon: String
)

data class WeatherDetails(
    val temp: Double,
    val humidity: Double
)

data class Wind(
    val speed: Double
)
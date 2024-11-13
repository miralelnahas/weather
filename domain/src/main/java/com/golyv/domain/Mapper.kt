package com.golyv.domain

import com.golyv.data.database.images.ImageEntity
import com.golyv.data.network.weather.WeatherResponse
import com.golyv.domain.models.ImageDetails
import com.golyv.domain.models.WeatherDetails

fun WeatherResponse.toWeatherDetails() =
    WeatherDetails(
        main.temp,
        main.humidity,
        weather.firstOrNull()?.icon ?: "",
        wind.speed
    )

fun ImageEntity.toImageDetails() = ImageDetails(
    id, imageUrl, locationName, WeatherDetails(temp, humidity, weatherIcon, windSpeed)
)
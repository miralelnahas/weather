package com.golyv.data.repositories

import com.golyv.data.network.weather.WeatherResponse

interface WeatherRemoteDataSource {
    suspend fun getWeatherDetails(lat: Double, lng: Double): Result<WeatherResponse>
}
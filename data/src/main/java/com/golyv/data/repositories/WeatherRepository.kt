package com.golyv.data.repositories

import com.golyv.data.database.images.ImageEntity
import com.golyv.data.models.ImageDetailsDto
import com.golyv.data.models.WeatherDetailsDto
import com.golyv.data.network.weather.WeatherResponse

interface WeatherRepository {
    suspend fun getImages(): List<ImageEntity>
    suspend fun addImage(
        imageDetailsDto: ImageDetailsDto
    ): Long
    suspend fun getImageDetails(id: Long): ImageEntity
    suspend fun getWeatherDetails(lat: Double, lng: Double): Result<WeatherResponse>
    suspend fun saveWeatherDetails(weatherDetailsDto: WeatherDetailsDto)
    suspend fun saveLocation(location: String)
    suspend fun getWeatherDetails() : WeatherDetailsDto?
    suspend fun getLocation() : String
}
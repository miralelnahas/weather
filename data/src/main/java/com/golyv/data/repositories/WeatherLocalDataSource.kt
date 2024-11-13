package com.golyv.data.repositories

import com.golyv.data.database.images.ImageEntity
import com.golyv.data.models.ImageDetailsDto
import com.golyv.data.models.WeatherDetailsDto

interface WeatherLocalDataSource {

    suspend fun addImage(
        imageDetailsDto: ImageDetailsDto
    ): Long

    suspend fun getImages(): List<ImageEntity>
    suspend fun getImageDetails(id: Long): ImageEntity
    suspend fun saveWeatherDetails(weatherDetailsDto: WeatherDetailsDto)
    suspend fun getWeatherDetails() : WeatherDetailsDto?
    suspend fun saveLocation(location: String)
    suspend fun getLocation() : String
}
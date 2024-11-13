package com.golyv.data.repositories

import com.golyv.data.database.images.ImageEntity
import com.golyv.data.models.ImageDetailsDto
import com.golyv.data.models.WeatherDetailsDto
import com.golyv.data.network.weather.WeatherResponse
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource) :
    WeatherRepository {
    override suspend fun getImages(): List<ImageEntity> =
        weatherLocalDataSource.getImages()

    override suspend fun addImage(
        imageDetailsDto: ImageDetailsDto
    ) = weatherLocalDataSource.addImage(imageDetailsDto)

    override suspend fun getImageDetails(id: Long): ImageEntity =
        weatherLocalDataSource.getImageDetails(id)

    override suspend fun getWeatherDetails(lat: Double, lng: Double): Result<WeatherResponse> =
        weatherRemoteDataSource.getWeatherDetails(lat, lng)

    override suspend fun getWeatherDetails(): WeatherDetailsDto? =
        weatherLocalDataSource.getWeatherDetails()

    override suspend fun saveWeatherDetails(weatherDetailsDto: WeatherDetailsDto) {
        weatherLocalDataSource.saveWeatherDetails(weatherDetailsDto)
    }

    override suspend fun saveLocation(location: String) {
        weatherLocalDataSource.saveLocation(location)
    }

    override suspend fun getLocation(): String =
        weatherLocalDataSource.getLocation()
}
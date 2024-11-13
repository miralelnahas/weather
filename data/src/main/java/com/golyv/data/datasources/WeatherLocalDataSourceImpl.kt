package com.golyv.data.datasources

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.golyv.data.database.AppDatabase
import com.golyv.data.database.images.ImageEntity
import com.golyv.data.models.ImageDetailsDto
import com.golyv.data.models.WeatherDetailsDto
import com.golyv.data.repositories.WeatherLocalDataSource
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.weatherDataStore by preferencesDataStore("weather")

class WeatherLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: AppDatabase,
    private val gson: Gson
) : WeatherLocalDataSource {
    private val weatherDataStore = context.weatherDataStore

    override suspend fun addImage(
        imageDetailsDto: ImageDetailsDto
    ) = database.imageEntityDao().insert(
        ImageEntity(
            imageUrl = imageDetailsDto.imageUrl,
            humidity = imageDetailsDto.weatherDetails.humidity,
            temp = imageDetailsDto.weatherDetails.temp,
            locationName = imageDetailsDto.locationName,
            windSpeed = imageDetailsDto.weatherDetails.windSpeed,
            weatherIcon = imageDetailsDto.weatherDetails.weatherIcon
        )
    )

    override suspend fun getImages() =
        database.imageEntityDao().getAllImages()

    override suspend fun getImageDetails(id: Long): ImageEntity =
        database.imageEntityDao().getImage(id)

    override suspend fun saveWeatherDetails(weatherDetailsDto: WeatherDetailsDto) {
        val key = stringPreferencesKey("weather")
        weatherDataStore.edit { pref ->
            pref[key] = gson.toJson(weatherDetailsDto)
        }
    }

    override suspend fun getWeatherDetails(): WeatherDetailsDto? {
        val key = stringPreferencesKey("weather")
        val user: Flow<WeatherDetailsDto> = weatherDataStore.data.map { pref ->
            gson.fromJson(pref[key], WeatherDetailsDto::class.java)
        }
        return user.firstOrNull()
    }

    override suspend fun saveLocation(location: String) {
        val key = stringPreferencesKey("location")
        weatherDataStore.edit { pref ->
            pref[key] = location
        }
    }

    override suspend fun getLocation(): String {
        val key = stringPreferencesKey("location")
        val location: Flow<String> = weatherDataStore.data.map { pref ->
            pref[key] ?: ""
        }
        return location.first()
    }

}
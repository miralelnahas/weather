package com.golyv.domain.useCases

import com.golyv.data.repositories.WeatherRepository
import com.golyv.domain.models.WeatherDetails
import javax.inject.Inject

class GetLocalWeatherDetailsUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke() = weatherRepository.getWeatherDetails()?.let {
        WeatherDetails(it.temp, it.humidity, it.weatherIcon, it.windSpeed)
    }
}
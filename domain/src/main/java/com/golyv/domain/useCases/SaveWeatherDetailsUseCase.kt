package com.golyv.domain.useCases

import com.golyv.data.models.WeatherDetailsDto
import com.golyv.data.repositories.WeatherRepository
import com.golyv.domain.models.WeatherDetails
import javax.inject.Inject

class SaveWeatherDetailsUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(weatherDetails: WeatherDetails) =
        weatherRepository.saveWeatherDetails(
            WeatherDetailsDto(
                weatherDetails.windSpeed,
                weatherDetails.temp,
                weatherDetails.humidity,
                weatherDetails.weatherIcon
            )
        )
}
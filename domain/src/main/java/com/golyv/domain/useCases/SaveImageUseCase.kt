package com.golyv.domain.useCases

import com.golyv.data.models.ImageDetailsDto
import com.golyv.data.models.WeatherDetailsDto
import com.golyv.data.repositories.WeatherRepository
import com.golyv.domain.models.WeatherDetails
import javax.inject.Inject

class SaveImageUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(
        imageUrl: String,
        locationName: String,
        weatherDetails: WeatherDetails
    ) = weatherRepository.addImage(
            ImageDetailsDto(
                imageUrl,
                locationName,
                WeatherDetailsDto(
                    weatherDetails.windSpeed,
                    weatherDetails.temp,
                    weatherDetails.humidity,
                    weatherDetails.weatherIcon
                )
            )
        )
}
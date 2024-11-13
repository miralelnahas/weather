package com.golyv.domain.useCases

import com.golyv.data.repositories.WeatherRepository
import javax.inject.Inject

class SaveLocationUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(locationName: String) =
        weatherRepository.saveLocation(locationName)
}
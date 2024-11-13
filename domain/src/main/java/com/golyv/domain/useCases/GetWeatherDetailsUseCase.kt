package com.golyv.domain.useCases

import com.golyv.data.repositories.WeatherRepository
import com.golyv.domain.toWeatherDetails
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(lat: Double, lng: Double) =
        weatherRepository.getWeatherDetails(lat, lng).map {
            it.toWeatherDetails()
        }
}
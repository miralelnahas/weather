package com.golyv.domain.useCases

import com.golyv.data.repositories.WeatherRepository
import javax.inject.Inject

class GetLocalLocationUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke() =
        weatherRepository.getLocation()
}
package com.golyv.domain.useCases

import com.golyv.data.repositories.WeatherRepository
import com.golyv.domain.toImageDetails
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke() = weatherRepository.getImages().map {
        it.toImageDetails()
    }
}
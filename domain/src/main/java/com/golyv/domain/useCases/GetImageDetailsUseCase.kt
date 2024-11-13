package com.golyv.domain.useCases

import com.golyv.data.repositories.WeatherRepository
import com.golyv.domain.toImageDetails
import javax.inject.Inject

class GetImageDetailsUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(id: Long) = weatherRepository.getImageDetails(id).let {
        it.toImageDetails()
    }
}
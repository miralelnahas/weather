package com.golyv.data.models

data class ImageDetailsDto(
    val imageUrl: String,
    val locationName: String,
    val weatherDetails: WeatherDetailsDto
)
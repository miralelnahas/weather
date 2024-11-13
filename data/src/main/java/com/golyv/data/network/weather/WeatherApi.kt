package com.golyv.data.network.weather

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherDetails(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double
    ): Response<WeatherResponse>
}
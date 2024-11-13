package com.golyv.data.datasources

import com.golyv.data.network.RetrofitClientExt.apiCall
import com.golyv.data.network.weather.WeatherApi
import com.golyv.data.network.weather.WeatherResponse
import com.golyv.data.repositories.WeatherRemoteDataSource
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
) : WeatherRemoteDataSource {
    private val weatherApi = retrofit.create(WeatherApi::class.java)
    override suspend fun getWeatherDetails(lat: Double, lng: Double): Result<WeatherResponse> =
        apiCall {
            weatherApi.getWeatherDetails(lat, lng)
        }
}
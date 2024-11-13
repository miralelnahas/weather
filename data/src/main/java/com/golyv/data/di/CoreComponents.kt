package com.golyv.data.di

import com.golyv.data.datasources.WeatherLocalDataSourceImpl
import com.golyv.data.datasources.WeatherRemoteDataSourceImpl
import com.golyv.data.managers.ConnectionManager
import com.golyv.data.managers.ConnectionManagerImpl
import com.golyv.data.repositories.WeatherLocalDataSource
import com.golyv.data.repositories.WeatherRemoteDataSource
import com.golyv.data.repositories.WeatherRepository
import com.golyv.data.repositories.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreComponents {
    @Binds
    fun connectionManager(connectionManagerImpl: ConnectionManagerImpl): ConnectionManager

    @Binds
    fun weatherRepo(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    fun weatherRemoteDs(weatherRemoteDataSourceImpl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource

    @Binds
    fun weatherLocalDs(weatherLocalDataSourceImpl: WeatherLocalDataSourceImpl): WeatherLocalDataSource

}
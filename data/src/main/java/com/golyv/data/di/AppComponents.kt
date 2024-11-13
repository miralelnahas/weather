package com.golyv.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.golyv.data.BuildConfig
import com.golyv.data.database.AppDatabase
import com.golyv.data.network.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppComponents {
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        authorizationInterceptor: AuthorizationInterceptor
    ): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor).build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, IMAGES_DATA_BASE_NAME)
            .build()

    companion object {
        private const val IMAGES_DATA_BASE_NAME = "images_database.db"
    }
}
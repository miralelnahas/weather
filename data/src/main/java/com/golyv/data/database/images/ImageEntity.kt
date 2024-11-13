package com.golyv.data.database.images

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "image_url")
    val imageUrl : String,

    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double,

    @ColumnInfo(name = "temperature")
    val temp: Double,

    @ColumnInfo(name = "humidity")
    val humidity: Double,

    @ColumnInfo(name = "location_name")
    val locationName: String,

    @ColumnInfo(name = "weather_icon")
    val weatherIcon : String
)
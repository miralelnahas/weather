package com.golyv.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.golyv.data.database.images.ImageEntity
import com.golyv.data.database.images.ImagesDao

@Database(
    entities = [ImageEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageEntityDao(): ImagesDao
}
package com.golyv.data.database.images

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface ImagesDao {
    @Query("SELECT * FROM imageentity ORDER BY id DESC")
    suspend fun getAllImages(): List<ImageEntity>

    @Query("SELECT * FROM imageentity WHERE id = :id")
    suspend fun getImage(id: Long): ImageEntity

    @Insert(onConflict = REPLACE)
    suspend fun insert(imageEntity: ImageEntity) : Long

    @Query("DELETE FROM imageentity")
    suspend fun clearAll()
}
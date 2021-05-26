package com.example.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.app.database.dto.EntityDB

@Dao
interface EntityDao {
    @Insert
    fun addEntity(entity: EntityDB)

    @Query("DELETE FROM entities")
    fun deleteAllEntities()

    @Query("SELECT * FROM entities")
    fun getAllEntities(): List<EntityDB>
}
package com.example.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.app.database.dto.DataDB

@Dao
interface DataDao {
    @Insert
    fun addData(data: DataDB)

    @Query("DELETE FROM data")
    fun deleteAllObjects()

    @Query("SELECT * FROM data")
    fun getAllObjects(): List<DataDB>

    @Query("SELECT * FROM data WHERE uid = :uid")
    fun getObject(uid: String): DataDB
}
package com.example.app.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.app.database.dto.DataDB
import com.example.app.database.dto.StatusDB

@Dao
interface StatusDao {
    @Insert
    fun addStatus(status: StatusDB)

    @Query("DELETE FROM statuses")
    fun deleteAllStatuses()

    @Query("SELECT * FROM statuses")
    fun getAllStatuses(): List<StatusDB>
}
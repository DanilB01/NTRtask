package com.example.app.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "statuses")
data class StatusDB(
        val objectId: Int,
        val tag: String,
        @PrimaryKey
        val uid: String = UUID.randomUUID().toString()
)

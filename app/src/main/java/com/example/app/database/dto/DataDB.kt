package com.example.app.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "data")
data class DataDB(
        val name: String,
        val title: String,
        val entityId: String,
        val objectId: Int,
        @PrimaryKey
        val uid: String = UUID.randomUUID().toString()
)

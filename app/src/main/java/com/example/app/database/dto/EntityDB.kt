package com.example.app.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "entities")
data class EntityDB(
        val name: String,
        val latitude: Float,
        val longitude: Float,
        @PrimaryKey
        val uid: String = UUID.randomUUID().toString()
)

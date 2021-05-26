package com.example.app.network.dto.entity

import com.google.gson.annotations.SerializedName

data class ObjectDTO (
        val name: String,
        @SerializedName("object_id")
        val objectId: Int,
        val title: String
)
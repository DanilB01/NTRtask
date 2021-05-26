package com.example.app.network.dto.entity

import com.google.gson.annotations.SerializedName

data class LocationDTO(
        @SerializedName("gps_lat")
        val latitude: Float,
        @SerializedName("gps_lng")
        val longitude: Float
)

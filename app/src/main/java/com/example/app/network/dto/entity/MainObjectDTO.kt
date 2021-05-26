package com.example.app.network.dto.entity

import com.google.gson.annotations.SerializedName

data class MainObjectDTO(
    @SerializedName("object")
    val mainObject: List<ObjectDTO>
)

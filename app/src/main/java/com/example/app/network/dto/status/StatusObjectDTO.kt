package com.example.app.network.dto.status

import com.google.gson.annotations.SerializedName

data class StatusObjectDTO(
        @SerializedName("object_id")
        val objectId: Int,
        val tag: Int
)

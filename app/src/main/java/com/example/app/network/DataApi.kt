package com.example.app.network

import com.example.app.network.dto.entity.EntityDTO
import com.example.app.network.dto.status.StatusDTO
import retrofit2.http.GET

interface DataApi {
    @GET("ufwuccum01rchdl/entity")
    suspend fun getEntity(): EntityDTO

    @GET("c9o1x8i45q5872k/statuses.json")
    suspend fun getStatuses(): List<StatusDTO>
}
package com.example.app.network


class DataApiRepository(private val dataApi: DataApi) {

    suspend fun getEntity() = dataApi.getEntity()

    suspend fun getStatuses() = dataApi.getStatuses()

}
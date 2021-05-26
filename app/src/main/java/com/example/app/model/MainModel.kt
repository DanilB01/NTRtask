package com.example.app.model

import android.content.Context
import com.example.app.database.AppDatabase
import com.example.app.database.dto.DataDB
import com.example.app.database.dto.EntityDB
import com.example.app.database.dto.StatusDB
import com.example.app.network.DataApiRepository
import com.example.app.network.Network
import com.example.app.recycler.RecyclerItemType
import com.example.app.recycler.dto.RecyclerDataItem
import com.example.app.recycler.dto.RecyclerEntityItem
import com.example.app.recycler.dto.RecyclerItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainModel(private val app: Context) {

    private val api = DataApiRepository(Network.retrofit)
    private val database = AppDatabase.getDatabase(app)

    suspend fun getData() = when(Network.isOnline(app)){
            true -> getDataFromServer()
            false -> getDataFromDatabase()
    }

    private suspend fun getDataFromServer(): List<RecyclerItem> {
        val  dataList = mutableListOf<RecyclerItem>()
        val entity = withContext(Dispatchers.IO) {
            api.getEntity()
        }
        val statuses = withContext(Dispatchers.IO) {
            api.getStatuses()
        }
        dataList.add(RecyclerItem(RecyclerEntityItem(entity.name, entity.location.latitude, entity.location.longitude)))
        entity.objects.mainObject.forEach { currentObject ->
            val tagsList = statuses
                .filter { it.status.objectId == currentObject.objectId }
                .map { it.status.tag.toString() }

            dataList.add(
                RecyclerItem(
                RecyclerDataItem(
                    currentObject.objectId,
                    currentObject.name,
                    currentObject.title,
                    tagsList)
            )
            )
        }
        cacheData(dataList)
        return dataList
    }

    private fun cacheData(dataList: List<RecyclerItem>){
        GlobalScope.launch(Dispatchers.IO) {
            database.statusesDao().deleteAllStatuses()
            database.dataDao().deleteAllObjects()
            database.entitiesDao().deleteAllEntities()

            var entityUid = ""
            dataList.forEach {
                when(it.type) {
                    RecyclerItemType.ENTITY.type -> {
                        val newEntity = EntityDB(it.entity!!.name, it.entity!!.latitude, it.entity!!.longitude)
                        database.entitiesDao().addEntity(newEntity)
                        entityUid = newEntity.uid
                    }
                    RecyclerItemType.DATA.type -> {
                        val newObject = DataDB(it.data!!.name, it.data!!.title, entityUid, it.data!!.id)
                        database.dataDao().addData(newObject)

                        it.data!!.tags.forEach { tag ->
                            val newStatus = StatusDB(it.data!!.id, tag)
                            database.statusesDao().addStatus(newStatus)
                        }
                    }
                }
            }
        }
    }

    private suspend fun getDataFromDatabase(): List<RecyclerItem> {

        val  dataList = mutableListOf<RecyclerItem>()
        val entity = withContext(Dispatchers.IO) {
            database.entitiesDao().getAllEntities().firstOrNull()
        }
        val objects = withContext(Dispatchers.IO) {
            database.dataDao().getAllObjects()
        }
        val statuses = withContext(Dispatchers.IO) {
            database.statusesDao().getAllStatuses()
        }
        if(entity != null) {
            dataList.add(RecyclerItem(RecyclerEntityItem(entity.name, entity.latitude, entity.longitude)))
            val entityObjects = objects.filter { it.entityId == entity.uid }
            entityObjects.forEach { currentObject ->
                val tagsList = statuses
                    .filter { it.objectId == currentObject.objectId }
                    .map { it.tag }

                dataList.add(
                    RecyclerItem(
                    RecyclerDataItem(
                        currentObject.objectId,
                        currentObject.name,
                        currentObject.title,
                        tagsList)
                )
                )
            }
        }
        return dataList
    }
}
package com.example.app.recycler.dto

import com.example.app.data.Data
import com.example.app.data.Entity
import com.example.app.recycler.RecyclerItemType as itemType

class RecyclerItem{
    var type: Int
    var entity: Entity? = null
    var data: Data? = null

    internal constructor(entity: Entity?) {
        this.entity = entity
        type = itemType.ENTITY.type
    }

    internal constructor(data: Data?) {
        this.data = data
        type = itemType.DATA.type
    }
}

package com.example.app.recycler.dto

import com.example.app.recycler.RecyclerItemType as itemType

class RecyclerItem{
    var type: Int
    var entity: RecyclerEntityItem? = null
    var data: RecyclerDataItem? = null

    internal constructor(entity: RecyclerEntityItem?) {
        this.entity = entity
        type = itemType.ENTITY.type
    }

    internal constructor(data: RecyclerDataItem?) {
        this.data = data
        type = itemType.DATA.type
    }
}

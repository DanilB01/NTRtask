package com.example.app.recycler.dto

import com.example.app.AppConst

class RecyclerItem{
    var type: Int = 0
    var entity: RecyclerEntityItem? = null
    var data: RecyclerDataItem? = null

    internal constructor(entity: RecyclerEntityItem?) {
        this.entity = entity
        type = AppConst.TYPE_ENTITY
    }

    internal constructor(data: RecyclerDataItem?) {
        this.data = data
        type = AppConst.TYPE_DATA
    }
}

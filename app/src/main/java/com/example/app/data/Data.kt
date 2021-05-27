package com.example.app.data

data class Data(
        val id: Int,
        val name: String,
        val title: String,
        val tags: List<String>,
        val entity: Entity
) {
    fun getMinTag(): Int {
        var minTag = Int.MAX_VALUE
        if(tags.isNotEmpty()) {
            minTag = tags.minOf { it.toInt() }
        }
        return minTag
    }
}

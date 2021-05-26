package com.example.app.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.ItemDataBinding
import com.example.app.databinding.ItemEntityBinding
import com.example.app.recycler.dto.RecyclerDataItem
import com.example.app.recycler.dto.RecyclerEntityItem
import com.example.app.recycler.dto.RecyclerItem
import java.lang.Exception
import com.example.app.recycler.RecyclerItemType

class DataRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<RecyclerItem>()

    fun updateDataSet(newDataList: List<RecyclerItem>){
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    class DataViewHolder(private val itemBinding: ItemDataBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(dataItem: RecyclerDataItem){
            itemBinding.nameTextView.text = dataItem.name
            itemBinding.titleTextView.text = dataItem.title
            //itemBinding.tagsTextView.text = dataItem.tags.joinToString(separator = "\n")
            itemBinding.tagRatingBar.rating = dataItem.tags.size.toFloat()
        }
    }

    class EntityViewHolder(private val itemBinding: ItemEntityBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(dataItem: RecyclerEntityItem) {
            itemBinding.entityTextView.text = dataItem.name
            itemBinding.latitudeTextView.text = dataItem.latitude.toString()
            itemBinding.longitudeTextView.text = dataItem.longitude.toString()
        }
    }

    override fun getItemViewType(position: Int): Int = dataList[position].type

    override fun getItemCount() = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RecyclerItemType.ENTITY.type -> {
                val itemBinding =
                    ItemEntityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EntityViewHolder(itemBinding)
            }
            RecyclerItemType.DATA.type -> {
                val itemBinding =
                    ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DataViewHolder(itemBinding)
            }
            else -> throw Exception("View type $viewType not found")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataList[position].type){
            RecyclerItemType.DATA.type -> (holder as DataViewHolder).bind(dataList[position].data!!)
            RecyclerItemType.ENTITY.type -> (holder as EntityViewHolder).bind(dataList[position].entity!!)
        }
    }

}
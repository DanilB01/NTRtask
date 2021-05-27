package com.example.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.ItemDataBinding
import com.example.app.databinding.ItemEntityBinding
import com.example.app.data.Data
import com.example.app.data.Entity
import com.example.app.recycler.RecyclerItemType
import com.example.app.recycler.dto.RecyclerItem
import com.example.app.view.IMainActivity
import java.lang.Exception

class DataRecyclerAdapter(private val activity: IMainActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<RecyclerItem>()

    fun updateDataSet(newDataList: List<RecyclerItem>){
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    class DataViewHolder(private val itemBinding: ItemDataBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: Data, activity: IMainActivity){
            itemBinding.nameTextView.text = data.name
            itemBinding.titleTextView.text = data.title
            itemBinding.tagRatingBar.rating = data.tags.size.toFloat()
            itemBinding.itemCardView.setOnClickListener {
                activity.openMaps(data)
            }
        }
    }

    class EntityViewHolder(private val itemBinding: ItemEntityBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: Entity) {
            itemBinding.entityTextView.text = data.name
            itemBinding.latitudeTextView.text = data.latitude.toString()
            itemBinding.longitudeTextView.text = data.longitude.toString()
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
            else -> throw Exception(getException(viewType))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataList[position].type){
            RecyclerItemType.DATA.type -> (holder as DataViewHolder).bind(dataList[position].data!!, activity)
            RecyclerItemType.ENTITY.type -> (holder as EntityViewHolder).bind(dataList[position].entity!!)
        }
    }

    companion object{
        private fun getException(viewType:Int) = "View type $viewType not found"
    }

}
package com.example.app.recycler

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.AppConst
import com.example.app.MapActivity
import com.example.app.databinding.ItemDataBinding
import com.example.app.databinding.ItemEntityBinding
import com.example.app.recycler.dto.RecyclerDataItem
import com.example.app.recycler.dto.RecyclerEntityItem
import com.example.app.recycler.dto.RecyclerItem
import java.lang.Exception

class DataRecyclerAdapter(private val context: Context,
                          private val dataList: List<RecyclerItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class DataViewHolder(private val itemBinding: ItemDataBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(dataItem: RecyclerDataItem, context:Context) {
            itemBinding.nameTextView.text = dataItem.name
            itemBinding.titleTextView.text = dataItem.title
            //itemBinding.tagsTextView.text = dataItem.tags.joinToString(separator = "\n")
            itemBinding.tagRatingBar.rating = dataItem.tags.size.toFloat()
            itemBinding.itemCardView.setOnClickListener {
                val intent = Intent(context, MapActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

    class EntityViewHolder(private val itemBinding: ItemEntityBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(dataItem: RecyclerEntityItem) {
            itemBinding.entityTextView.text = dataItem.name
            itemBinding.latitudeTextView.text = dataItem.latitude.toString()
            itemBinding.longitudeTextView.text = dataItem.longitude.toString()
        }
    }

    override fun getItemViewType(position: Int) = dataList[position].type

    override fun getItemCount() = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            AppConst.TYPE_ENTITY -> {
                val itemBinding =
                    ItemEntityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EntityViewHolder(itemBinding)
            }
            AppConst.TYPE_DATA -> {
                val itemBinding =
                    ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                DataViewHolder(itemBinding)
            }
            else -> throw Exception("View type $viewType not found")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataList[position].type){
            AppConst.TYPE_DATA -> (holder as DataViewHolder).bind(dataList[position].data!!, context)
            AppConst.TYPE_ENTITY -> (holder as EntityViewHolder).bind(dataList[position].entity!!)
        }
    }

}
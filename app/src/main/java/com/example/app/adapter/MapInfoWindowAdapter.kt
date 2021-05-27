package com.example.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.app.data.Data
import com.example.app.databinding.PopupMapInfoBinding
import com.google.android.gms.common.util.DataUtils
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.gson.Gson

class MapInfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {

    private val binding by lazy { PopupMapInfoBinding.inflate(LayoutInflater.from(context)) }

    private fun inflateInfoWindow(marker: Marker) {
        val dataJson = marker.snippet
        val data = Gson().fromJson(dataJson, Data::class.java)
        binding.mapNameTextView.text = data.name
        binding.mapTitleTextView.text = data.title
        binding.mapTagsTextView.text = data.tags.toString()
    }

    override fun getInfoWindow(p0: Marker): View {
        inflateInfoWindow(p0)
        return binding.root
    }

    override fun getInfoContents(p0: Marker): View {
        inflateInfoWindow(p0)
        return binding.root
    }
}
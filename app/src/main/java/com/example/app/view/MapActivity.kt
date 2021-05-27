package com.example.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.app.R
import com.example.app.adapter.MapInfoWindowAdapter
import com.example.app.data.Data
import com.example.app.databinding.ActivityMainBinding
import com.example.app.viewModel.MainViewModel
import com.example.app.viewModel.MapViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private val model: MapViewModel by viewModels()
    private lateinit var mMap: GoogleMap
    //private lateinit var data: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val dataJson = intent.getStringExtra("data")
        model.setInfo(dataJson)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val data = model.data.value!!
        val place = LatLng(
                data.entity.latitude.toDouble(),
                data.entity.longitude.toDouble()
        )
        mMap = googleMap
        mMap.addMarker(
                MarkerOptions()
                        .position(place)
                        .snippet(model.dataJson.value)
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place))
        mMap.setInfoWindowAdapter(MapInfoWindowAdapter(this))
    }
}
package com.example.app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.data.*
import com.google.gson.Gson

class MapViewModel : ViewModel() {

    private var _data: MutableLiveData<Data> = MutableLiveData()
    val data: LiveData<Data> = _data

    private var _dataJson: MutableLiveData<String> = MutableLiveData()
    val dataJson: LiveData<String> = _dataJson

    fun setInfo(json: String?) {
        _dataJson.value = json
        _data.value = Gson().fromJson(_dataJson.value, Data::class.java)
    }

}
package com.example.app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.data.*
import com.example.app.model.MainModel
import com.example.app.recycler.dto.RecyclerItem
import com.google.gson.Gson

class MapViewModel(app: Application) : AndroidViewModel(app) {

    private var _data: MutableLiveData<Data> = MutableLiveData()
    val data: LiveData<Data> = _data

    private var _dataJson: MutableLiveData<String> = MutableLiveData()
    val dataJson: LiveData<String> = _dataJson

    private val mainModel = MainModel(getApplication())

    private var _isOnline: MutableLiveData<Boolean> = MutableLiveData()
    val isOnline: LiveData<Boolean> = _isOnline

    init {
        _isOnline.value = mainModel.isOnline()
    }

    fun setInfo(json: String?) {
        _dataJson.value = json
        _data.value = Gson().fromJson(_dataJson.value, Data::class.java)
    }

}
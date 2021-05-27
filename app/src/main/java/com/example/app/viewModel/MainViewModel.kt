package com.example.app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app.data.Data
import com.example.app.data.Entity
import com.example.app.model.MainModel
import com.example.app.recycler.dto.RecyclerItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val mainModel = MainModel(getApplication())

    private var _dataList: MutableLiveData<List<RecyclerItem>> = MutableLiveData()
    val dataList: LiveData<List<RecyclerItem>> = _dataList

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        setData()
    }

    private fun setData() {
        GlobalScope.launch(Dispatchers.Main) {
            _isLoading.value = true
            _dataList.value = mainModel.getData()
            _isLoading.value = false
        }
    }
}
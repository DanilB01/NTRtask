package com.example.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.app.viewModel.MainViewModel
import com.example.app.R
import com.example.app.databinding.ActivityMainBinding
import com.example.app.recycler.DataRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    private val recyclerAdapter = DataRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.dataRecyclerView.adapter = recyclerAdapter

        model.dataList.observe(this) {
            recyclerAdapter.updateDataSet(it)
        }

        model.isLoading.observe(this) {
            binding.dataProgressBar.isVisible = it
        }
    }
}
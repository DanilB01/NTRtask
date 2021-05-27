package com.example.app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.app.viewModel.MainViewModel
import com.example.app.R
import com.example.app.data.Data
import com.example.app.databinding.ActivityMainBinding
import com.example.app.adapter.DataRecyclerAdapter
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), IMainActivity {

    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    private val recyclerAdapter = DataRecyclerAdapter(this)

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

        model.isOnline.observe(this) {
            if (!it) {
                Toast.makeText(this, getString(R.string.badConnection), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun openMaps(data: Data) {
        val dataJson = Gson().toJson(data)
        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra(getString(R.string.dataIntentKey), dataJson)
        startActivity(intent)
    }
}
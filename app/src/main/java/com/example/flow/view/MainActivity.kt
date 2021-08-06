package com.example.flow.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.flow.R
import com.example.flow.model.CombinedResult
import com.example.flow.viewmodel.FlowViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: FlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(FlowViewModel::class.java)
        viewModel.stuffResultLiveData.observe(this) { result -> onResultObserved(result) }

        viewModel.loadData()
    }

    private fun onResultObserved(result: CombinedResult) {
        Log.d("##MainActivity", "$result")
    }
}
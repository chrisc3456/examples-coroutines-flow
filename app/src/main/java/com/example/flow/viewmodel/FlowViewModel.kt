package com.example.flow.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flow.model.*
import com.example.flow.repository.FlowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class FlowViewModel: ViewModel() {

    val stuffResultLiveData = MutableLiveData<CombinedResult>()
    private val repository = FlowRepository()

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadResultA().collect { resultA ->

                Log.d("##FlowViewModel", "Received result A")

                combine(
                    repository.loadResultB(resultA.value),
                    repository.loadResultC(resultA.value),
                    repository.loadResultD(resultA.value)
                ) { b, c, d ->
                    Log.d("##FlowViewModel", "Received results B, C and D... combining...")
                    stuffResultLiveData.postValue(
                        getCombinedResult(resultA, b, c, d)
                    )
                }.collect()
            }
        }
    }

    private fun getCombinedResult(resultA: ResultA, resultB: ResultB, resultC: ResultC, resultD: ResultD) =
        CombinedResult(
            resultA = resultA.value,
            resultB = resultB.value,
            resultC = resultC.value,
            resultD = resultD.value
        )
}
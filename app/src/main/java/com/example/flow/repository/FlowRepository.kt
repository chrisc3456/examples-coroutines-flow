package com.example.flow.repository

import android.util.Log
import com.example.flow.model.ResultC
import com.example.flow.model.ResultB
import com.example.flow.model.ResultA
import com.example.flow.model.ResultD
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowRepository {

    fun loadResultA() = flow {
        logStuff("Loading A")
        delay(2000)
        logStuff("Emitting A")
        emit(
            ResultA("A")
        )
    }

    fun loadResultB(resultA: String) = flow {
        logStuff("Loading B")
        delay(3000)
        logStuff("Emitting B")
        emit(
            ResultB("B - $resultA")
        )
    }

    fun loadResultC(resultA: String) = flow {
        logStuff("Loading C")
        delay(5000)
        logStuff("Emitting C")
        emit(
            ResultC("C - $resultA")
        )
    }

    fun loadResultD(resultA: String) = flow {
        logStuff("Loading D")
        delay(4000)
        logStuff("Emitting D")
        emit(
            ResultD("D - $resultA")
        )
    }

    private fun logStuff(message: String) {
        Log.d("##FlowRepository", message)
    }
}
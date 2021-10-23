package com.v.nevi.p.sv.android.myapplication

import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    private val repository = HistoryRepository.getInstance()
    val liveDataHistoryList = repository.getAllHistories()
    fun deleteAllHistory(){
        repository.deleteAllHistory()
    }
}
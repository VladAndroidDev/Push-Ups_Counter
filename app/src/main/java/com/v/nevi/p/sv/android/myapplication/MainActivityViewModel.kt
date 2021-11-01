package com.v.nevi.p.sv.android.myapplication

import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {

    private val repository = HistoryRepository.getInstance()
    val liveDataHistoryList = repository.getAllHistories()
    val liveDataGetSumCount=repository.getSumCount()
    val liveDataSumAttempts=repository.getSumAttempts()
    val allPushUps:Int?=null
    fun deleteAllHistory(){
        repository.deleteAllHistory()
    }
}
package com.v.nevi.p.sv.android.myapplication

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        HistoryRepository.initialize(this)
    }
}
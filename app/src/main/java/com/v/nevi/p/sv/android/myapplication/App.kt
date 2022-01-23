package com.v.nevi.p.sv.android.myapplication

import android.app.Application
import com.google.android.gms.ads.MobileAds

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        HistoryRepository.initialize(this)
        AchieveRepository.initialize(this)

    }
}
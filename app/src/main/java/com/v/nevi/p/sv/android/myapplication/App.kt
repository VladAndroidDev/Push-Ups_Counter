package com.v.nevi.p.sv.android.myapplication

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import com.google.android.gms.ads.MobileAds

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        HistoryRepository.initialize(this)
        AchieveRepository.initialize(this)
        MobileAds.initialize(this)
        if (CounterPreferences.getNowIsFirstStartApp(this)) {
            val sensorManager: SensorManager =
                getSystemService(Context.SENSOR_SERVICE) as SensorManager
            val proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            if (proximitySensor == null) {
                CounterPreferences.setHasDeviceProximitySensor(this, false)
            }
            CounterPreferences.setNowIsFirstStartApp(this, false)
        }

    }
}
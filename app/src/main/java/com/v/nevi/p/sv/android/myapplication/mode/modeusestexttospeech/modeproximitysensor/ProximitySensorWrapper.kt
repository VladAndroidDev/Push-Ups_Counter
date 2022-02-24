package com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech.modeproximitysensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech.Wrapper

class ProximitySensorWrapper(context: Context,private val onSensorChangedFunction:()->Unit):
    Wrapper(),SensorEventListener {

    private val proximitySensor: Sensor
    private val sensorManager=context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var isDown=false


    init {
        proximitySensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        lifecycleMonitor.onResumeListeners.add {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        lifecycleMonitor.onPauseListeners.add {
            sensorManager.unregisterListener(this)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] != 0.0f) {
                isDown = false
            } else if (!isDown) {
                onSensorChangedFunction.invoke()
                isDown = true
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}
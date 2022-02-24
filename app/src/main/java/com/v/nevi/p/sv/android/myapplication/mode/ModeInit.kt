package com.v.nevi.p.sv.android.myapplication.mode

import android.content.Context
import com.v.nevi.p.sv.android.myapplication.CounterPreferences
import com.v.nevi.p.sv.android.myapplication.databinding.ActivityCounterBinding
import com.v.nevi.p.sv.android.myapplication.mode.moderightcount.ModeRight
import com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech.modeproximitysensor.ModeProximitySensor
import com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech.modetouch.ModeTouch

class ModeInit {
    companion object {
        fun execute(context: Context, binding: ActivityCounterBinding): Mode {
            return when (CounterPreferences.getPreferencesCurrentMode(context)) {
                ModeEnum.MODE_TOUCH.ordinal -> ModeTouch(context, binding)
                ModeEnum.MODE_RIGHT.ordinal -> ModeRight(context, binding)
                ModeEnum.MODE_PROXIMITY_SENSOR.ordinal -> ModeProximitySensor(context, binding)
                else -> ModeTouch(context, binding)
            }
        }
    }
}
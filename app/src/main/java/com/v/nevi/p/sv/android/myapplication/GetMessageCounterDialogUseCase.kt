package com.v.nevi.p.sv.android.myapplication

import android.content.Context

class GetMessageCounterDialogUseCase {
    companion object{
        fun execute(context: Context):String {
            return if (CounterPreferences.getNowIsFirstStartCounter(context)) {
                CounterPreferences.setNowIsFirstStart(context, false)
                 if (CounterPreferences.getHasDeviceProximitySensor(context)) {
                    context.getString(R.string.first_start_counter_message_with_proximity_sensor)
                } else {
                    context.getString(R.string.first_start_counter_message_without_proximity_sensor)
                }
            }else {
                CounterPreferences.setChangedModeCounter(context,false)
                when (CounterPreferences.getPreferencesCurrentMode(context)) {
                    0 -> {
                        context.getString(R.string.change_on_touch_counter)
                    }
                    1 -> {
                        context.getString(R.string.change_on_right_counter)
                    }
                    else -> {
                        context.getString(R.string.change_on_proximity_sensor)
                    }
                }
            }
        }
    }
}
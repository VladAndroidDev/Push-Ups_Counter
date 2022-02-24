package com.v.nevi.p.sv.android.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val PREF_FIRST_START_COUNTER = "first-start-1"
private const val PREF_ADDED_HISTORY = "added-history-1"
private const val PREF_COUNTER_ACTIVITY = "first-start-counter-activity-1"
private const val PREF_CURRENT_MODE = "current-mode"
private const val PREF_FIRST_START_APP = "pref-first-start-app"
private const val PREF_HAS_DEVICE_PROXIMITY_SENSOR = "pref-has-device-proximity-sensor"
private const val PREF_CHANGED_MODE_COUNTER = "pref-changed-mode-counter"

object CounterPreferences {
    fun getNowIsFirstStartCounter(context: Context): Boolean {
        val pref = context.getSharedPreferences(PREF_COUNTER_ACTIVITY, Context.MODE_PRIVATE)
        return pref.getBoolean(PREF_FIRST_START_COUNTER, true)
    }

    fun setNowIsFirstStart(context: Context, boolean: Boolean) {
        val pref = context.getSharedPreferences(PREF_COUNTER_ACTIVITY, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean(PREF_FIRST_START_COUNTER, boolean)
        editor.apply()
    }

    fun isAddedFirstHistory(context: Context): Boolean {
        val pref = context.getSharedPreferences(PREF_COUNTER_ACTIVITY, Context.MODE_PRIVATE)
        return pref.getBoolean(PREF_ADDED_HISTORY, false)
    }

    fun setIsAddedFirstHistory(context: Context, boolean: Boolean) {
        val pref = context.getSharedPreferences(PREF_COUNTER_ACTIVITY, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean(PREF_ADDED_HISTORY, boolean)
        editor.apply()
    }

    fun getPreferencesCurrentMode(context: Context): Int {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return if (getHasDeviceProximitySensor(context)) {
            sharedPreferences.getInt(PREF_CURRENT_MODE, 2)
        } else {
            sharedPreferences.getInt(PREF_CURRENT_MODE, 0)
        }
    }

    fun setPreferencesCurrentMode(context: Context, selectedMode: Int) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(PREF_CURRENT_MODE, selectedMode)
        editor.apply()
    }

    fun getHasDeviceProximitySensor(context: Context): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getBoolean(PREF_HAS_DEVICE_PROXIMITY_SENSOR, true)
    }

    fun setHasDeviceProximitySensor(context: Context, boolean: Boolean) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(PREF_HAS_DEVICE_PROXIMITY_SENSOR, boolean)
        editor.apply()
    }

    fun getNowIsFirstStartApp(context: Context): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getBoolean(PREF_FIRST_START_APP, true)
    }

    fun setNowIsFirstStartApp(context: Context, boolean: Boolean) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(PREF_FIRST_START_APP, boolean)
        editor.apply()
    }

    fun isChangedModeCounter(context: Context): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getBoolean(PREF_CHANGED_MODE_COUNTER, false)
    }

    fun setChangedModeCounter(context: Context, boolean: Boolean) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(PREF_CHANGED_MODE_COUNTER, boolean)
        editor.apply()
    }
//    fun getCurrentMode(context: Context):Int{
//        val pref=context.getSharedPreferences(PREF_COUNTER_ACTIVITY,Context.MODE_PRIVATE)
//        return pref.getBoolean(PREF_FIRST_START,true)
//    }
}
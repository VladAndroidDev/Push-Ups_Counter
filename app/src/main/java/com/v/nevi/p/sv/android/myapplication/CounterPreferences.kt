package com.v.nevi.p.sv.android.myapplication

import android.content.Context
import android.content.SharedPreferences

private const val PREF_FIRST_START="first-start"
private const val PREF_COUNTER_ACTIVITY="first-start-counter-activity"

object CounterPreferences {
    fun getNowFirstStart(context: Context):Boolean{
        val pref=context.getSharedPreferences(PREF_COUNTER_ACTIVITY,Context.MODE_PRIVATE)
        return pref.getBoolean(PREF_FIRST_START,true)
    }
    fun setNowIsFirstStart(context: Context,boolean: Boolean){
        val pref=context.getSharedPreferences(PREF_COUNTER_ACTIVITY, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean(PREF_FIRST_START,boolean)
        editor.apply()
    }
}
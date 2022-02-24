package com.v.nevi.p.sv.android.myapplication.mode

import android.content.Context
import androidx.lifecycle.*
import java.lang.Exception

typealias lifeCycleHandler = ()->Unit
class CounterActivityLifecycleMonitor(context: Context):DefaultLifecycleObserver{

    init {
        context as LifecycleOwner
        context.lifecycle.addObserver(this)
    }
    companion object{
        private var monitor:CounterActivityLifecycleMonitor?=null
        fun initialize(context: Context){
            monitor = CounterActivityLifecycleMonitor(context)
        }
        fun getInstance():CounterActivityLifecycleMonitor{
            return monitor?:throw Exception("Not initialize Lifecycle observer")
        }
    }
    val onResumeListeners=mutableSetOf<lifeCycleHandler>()
    val onPauseListeners=mutableSetOf<lifeCycleHandler>()
    val onDestroyListeners=mutableSetOf<lifeCycleHandler>()


    override fun onResume(owner: LifecycleOwner) {
        onResumeListeners.forEach {
            it.invoke()
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        onPauseListeners.forEach {
            it.invoke()
        }
    }


    override fun onDestroy(owner: LifecycleOwner) {
        onDestroyListeners.forEach {
            it.invoke()
        }
    }
}
package com.v.nevi.p.sv.android.myapplication.mode

import android.content.Context
import com.v.nevi.p.sv.android.myapplication.CounterActivity
import com.v.nevi.p.sv.android.myapplication.CounterPreferences
import com.v.nevi.p.sv.android.myapplication.counter.Counter
import com.v.nevi.p.sv.android.myapplication.databinding.ActivityCounterBinding

abstract class Mode(context: Context, protected val binding: ActivityCounterBinding) {

    protected val counter: Counter = Counter()
    private val saver: Saver = Saver(context)
    private val adapter = binding.recyclerShowAttempts.adapter as CounterActivity.SetAdapter

    init {
        binding.resetButton.setOnClickListener {
            reset()
        }
        CounterActivityLifecycleMonitor.initialize(context)
    }

    fun saveTrainingResult(context: Context) {
        saver.save(counter)
        if (counter.isNotEmpty()) {
            CounterPreferences.setIsAddedFirstHistory(context, true)
        }
    }

    protected fun reset() {
        if (counter.countPushUps != 0)
            adapter.add(counter.countPushUps)
        binding.counterTextView.text = "0"
        counter.resetCounter()
        binding.counterAll.text = counter.countAllPushUps.toString()
    }
}
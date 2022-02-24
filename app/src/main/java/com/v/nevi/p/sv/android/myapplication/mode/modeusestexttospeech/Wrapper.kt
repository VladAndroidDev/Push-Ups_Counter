package com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech

import com.v.nevi.p.sv.android.myapplication.mode.CounterActivityLifecycleMonitor

abstract class Wrapper {
    val lifecycleMonitor: CounterActivityLifecycleMonitor =
        CounterActivityLifecycleMonitor.getInstance()
}
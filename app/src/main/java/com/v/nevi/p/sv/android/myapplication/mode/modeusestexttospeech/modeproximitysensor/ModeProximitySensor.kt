package com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech.modeproximitysensor

import android.content.Context
import com.v.nevi.p.sv.android.myapplication.databinding.ActivityCounterBinding
import com.v.nevi.p.sv.android.myapplication.mode.Mode
import com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech.ModeUsesTextToSpeech
import com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech.TextToSpeechWrapper

class ModeProximitySensor(context: Context,binding: ActivityCounterBinding) : ModeUsesTextToSpeech(context,binding) {


    private val proximitySensorWrapper: ProximitySensorWrapper = ProximitySensorWrapper(context) {
        counter.incrementPushUps()
        speakCount()
        binding.counterTextView.text = counter.countPushUps.toString()
    }
}
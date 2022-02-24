package com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech.modetouch

import android.content.Context
import com.v.nevi.p.sv.android.myapplication.databinding.ActivityCounterBinding
import com.v.nevi.p.sv.android.myapplication.mode.Mode
import com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech.ModeUsesTextToSpeech

class ModeTouch(context: Context, binding: ActivityCounterBinding) : ModeUsesTextToSpeech(context, binding) {
    init {
        binding.counterTextView.setOnClickListener {
            counter.incrementPushUps()
            speakCount()
            binding.counterTextView.text = counter.countPushUps.toString()
        }
    }
}
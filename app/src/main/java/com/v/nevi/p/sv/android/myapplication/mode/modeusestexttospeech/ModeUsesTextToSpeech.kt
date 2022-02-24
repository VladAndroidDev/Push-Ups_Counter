package com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech

import android.content.Context
import com.v.nevi.p.sv.android.myapplication.databinding.ActivityCounterBinding
import com.v.nevi.p.sv.android.myapplication.mode.Mode

open class ModeUsesTextToSpeech(context: Context, binding: ActivityCounterBinding): Mode(context,binding) {

    private val ttsWrapper: TextToSpeechWrapper = TextToSpeechWrapper(context)

    fun speakCount(){
        ttsWrapper.speak(counter.countPushUps.toString())
    }
}
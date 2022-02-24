package com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech

import android.content.Context
import android.speech.tts.TextToSpeech
import com.v.nevi.p.sv.android.myapplication.mode.modeusestexttospeech.Wrapper
import java.util.*

class TextToSpeechWrapper(context: Context) : Wrapper(), TextToSpeech.OnInitListener {

    private val tts: TextToSpeech = TextToSpeech(context, this)
    private var ttsEnabled = false

    init {
        lifecycleMonitor.onDestroyListeners.add {
            tts.shutdown()
        }
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            if (tts.isLanguageAvailable(Locale(Locale.getDefault().language)) == TextToSpeech.LANG_AVAILABLE) {
                tts.language = Locale(Locale.getDefault().language)
            } else {
                tts.language = Locale.US
            }
            tts.setPitch(1.3f)
            tts.setSpeechRate(0.7f)
            ttsEnabled = true
        } else {
            ttsEnabled = false
        }
    }

    fun speak(text: String) {
        if (!ttsEnabled) return
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }
}
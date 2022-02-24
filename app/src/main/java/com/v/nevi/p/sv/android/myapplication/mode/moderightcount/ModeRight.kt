package com.v.nevi.p.sv.android.myapplication.mode.moderightcount

import android.content.Context
import com.v.nevi.p.sv.android.myapplication.CounterActivity
import com.v.nevi.p.sv.android.myapplication.databinding.ActivityCounterBinding
import com.v.nevi.p.sv.android.myapplication.mode.Mode

class ModeRight(context: Context, binding: ActivityCounterBinding) : Mode(context, binding),
    EditTextDialogFragment.EditTextDialogFragmentCallback {
    init {
        binding.counterTextView.setOnClickListener {
            if(!binding.counterTextView.text.equals("0")){
                reset()
            }
            context as CounterActivity
            val editTextDialogFragment = EditTextDialogFragment(this)
            editTextDialogFragment.show(context.supportFragmentManager, null)
        }
    }

    override fun callback(text: String) {
        if(text.isNotEmpty()) {
            counter.addPushUps(text.toInt())
            binding.counterTextView.text = text
        }
    }
}
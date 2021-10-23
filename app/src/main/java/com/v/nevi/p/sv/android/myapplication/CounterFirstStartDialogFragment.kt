package com.v.nevi.p.sv.android.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class CounterFirstStartDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder=AlertDialog.Builder(it)
            builder
                .setMessage(R.string.first_start_counter_message)
                .setPositiveButton(R.string.ok_button){
                    dialog,id-> dialog.cancel()
                }
            builder.create()
        }?:throw IllegalAccessException()
    }
}
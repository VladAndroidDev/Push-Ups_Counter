package com.v.nevi.p.sv.android.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.math.roundToInt

class CounterFirstStartDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder=MaterialAlertDialogBuilder(it,R.style.AlertDialogTheme)
            builder
                .setTitle(R.string.how_to_use_the_counter)
                .setMessage(R.string.first_start_counter_message)
                .setPositiveButton(R.string.ok_button){
                        dialog, _ -> dialog.cancel()
                }
            builder.create()
        }?:throw IllegalAccessException()
    }
}
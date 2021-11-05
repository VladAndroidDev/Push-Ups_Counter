package com.v.nevi.p.sv.android.myapplication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ExitDialogFragment:DialogFragment() {

    private var activityCounter:CounterActivity?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable=false
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityCounter = context as CounterActivity
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder=MaterialAlertDialogBuilder(it,R.style.AlertDialogThemeExit)
            builder.setMessage(R.string.do_you_really_want_exit)
                .setPositiveButton(R.string.yes_button){dialog, _ ->
                    dialog.cancel()
                    activityCounter?.finish()
                }.setNegativeButton(R.string.no_button){
                        dialog, _ -> dialog.cancel()
                }
            builder.create()
        }?:throw IllegalAccessException()
    }
}
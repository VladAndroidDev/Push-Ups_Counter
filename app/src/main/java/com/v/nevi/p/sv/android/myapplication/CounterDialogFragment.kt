package com.v.nevi.p.sv.android.myapplication

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CounterDialogFragment: DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable=false
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let{
            val builder=MaterialAlertDialogBuilder(it,R.style.AlertDialogTheme)
            builder
                .setTitle(R.string.how_to_use_the_counter)
                .setMessage(GetMessageCounterDialogUseCase.execute(requireContext()))
                .setPositiveButton(R.string.ok_button){
                        dialog, _ -> dialog.cancel()
                }
            builder.create()
        }?:throw IllegalAccessException()
    }
}
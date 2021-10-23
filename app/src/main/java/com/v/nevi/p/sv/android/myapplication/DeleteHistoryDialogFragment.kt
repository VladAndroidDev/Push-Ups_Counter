package com.v.nevi.p.sv.android.myapplication

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class DeleteHistoryDialogFragment: DialogFragment() {

    private val model:MainActivityViewModel by activityViewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder=AlertDialog.Builder(it)
            builder.setMessage(R.string.message_delete)
                .setPositiveButton(R.string.yes_button){
                        dialog, _ ->
                    model.deleteAllHistory()
                    CounterPreferences.setNowIsFirstStart(requireContext(),true)
                    dialog.cancel()
                }
                .setNegativeButton(R.string.no_button){
                        dialog, _ ->dialog.cancel()
                }
                .setCancelable(true)
            builder.create()
        }?:throw IllegalAccessException()
    }
}
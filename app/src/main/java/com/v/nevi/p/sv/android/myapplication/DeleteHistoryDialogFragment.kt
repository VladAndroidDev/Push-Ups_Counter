package com.v.nevi.p.sv.android.myapplication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DeleteHistoryDialogFragment: DialogFragment() {

    private var recreateHistoryFragment: HistoryFragment.RecreateHistoryFragment?=null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        recreateHistoryFragment = context as HistoryFragment.RecreateHistoryFragment
    }
    private val model:MainActivityViewModel by activityViewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder=MaterialAlertDialogBuilder(it,R.style.AlertDialogTheme)
            .setMessage(R.string.message_delete)
                .setPositiveButton(R.string.yes_button){
                        dialog, _ ->
                    model.deleteAllHistory()
                    CounterPreferences.setNowIsFirstStart(requireContext(),true)
                    CounterPreferences.setIsAddedFirstHistory(requireContext(),false)
                    dialog.cancel()
                    recreateHistoryFragment?.recreateHistoryFragment()
                }
                .setNegativeButton(R.string.no_button){
                        dialog, _ ->dialog.cancel()
                }
                .setCancelable(true)
            builder.create()
        }?:throw IllegalAccessException()
    }

    override fun onDetach() {
        super.onDetach()
        recreateHistoryFragment=null
    }
}
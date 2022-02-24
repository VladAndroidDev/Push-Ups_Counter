package com.v.nevi.p.sv.android.myapplication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ExitDialogFragment : DialogFragment() {

    interface ExitCallback {
        fun exit()
    }

    private var callback: ExitCallback? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as ExitCallback
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { frActivity ->
            val builder = MaterialAlertDialogBuilder(frActivity, R.style.AlertDialogThemeExit)
            builder.setMessage(R.string.do_you_really_want_exit)
                .setPositiveButton(R.string.yes_button) { dialog, _ ->
                    dialog.cancel()
                    callback?.exit()

                }.setNegativeButton(R.string.no_button) { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalAccessException()
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }
}
package com.v.nevi.p.sv.android.myapplication.mode.moderightcount

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.v.nevi.p.sv.android.myapplication.R
import com.v.nevi.p.sv.android.myapplication.databinding.EdittextDialogfragmentBinding

class EditTextDialogFragment(mode:ModeRight) : DialogFragment() {

    interface EditTextDialogFragmentCallback{
        fun callback(text:String)
    }

    private val callback: EditTextDialogFragmentCallback = mode as EditTextDialogFragmentCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater=LayoutInflater.from(context)
        val binding=EdittextDialogfragmentBinding.inflate(inflater)
        return activity?.let {
            val builder = MaterialAlertDialogBuilder(it, R.style.AlertDialogThemeExit)
            builder.setView(binding.root)
            binding.buttonDone.setOnClickListener {
                callback.callback(binding.textInputEditText.text.toString())
                dialog?.cancel()
            }
            binding.buttonExit.setOnClickListener {
                dialog?.cancel()
            }
            builder.create()
        } ?: throw IllegalAccessException()
    }
}
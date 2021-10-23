package com.v.nevi.p.sv.android.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class StartFragment : Fragment() {

    interface CounterCallback{
        fun startCounter()
    }
    private var counterCallback: CounterCallback?=null
    private lateinit var startView: View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        counterCallback=context as CounterCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startView=view.findViewById(R.id.start_view)
        startView.setOnClickListener {
            counterCallback?.startCounter()
        }
    }

    override fun onDetach() {
        super.onDetach()
        counterCallback=null
    }
}
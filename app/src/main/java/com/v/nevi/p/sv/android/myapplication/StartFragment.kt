package com.v.nevi.p.sv.android.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.ump.ConsentInformation

import com.google.android.ump.UserMessagingPlatform

import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.ConsentForm


class StartFragment : Fragment() {




    interface CounterCallback {
        fun startCounter()
    }

    interface SettingsCallback {
        fun startSettings()
    }

    private var counterCallback: CounterCallback? = null
    private var settingsCallback: SettingsCallback? = null
    private lateinit var startView: View
    private lateinit var settingsView: View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        counterCallback = context as CounterCallback
        settingsCallback = context as SettingsCallback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startView = view.findViewById(R.id.start_view)
        settingsView = view.findViewById(R.id.settings_view)
        startView.setOnClickListener {
            counterCallback?.startCounter()
        }
        settingsView.setOnClickListener {
            settingsCallback?.startSettings()
        }
    }

    override fun onDetach() {
        super.onDetach()
        counterCallback = null
        settingsCallback = null
    }
}
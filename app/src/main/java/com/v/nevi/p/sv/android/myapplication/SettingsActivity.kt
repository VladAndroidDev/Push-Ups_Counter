package com.v.nevi.p.sv.android.myapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import kotlin.properties.Delegates

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        private var listPreference: ListPreference? = null
        private var currentMode by Delegates.notNull<Int>()
        private var newMode by Delegates.notNull<Int>()

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            listPreference = findPreference("list")
            if (!CounterPreferences.getHasDeviceProximitySensor(requireContext())) {
                listPreference?.entries =
                    requireContext().resources.getStringArray(R.array.mode_entries_without_proximity_sensor)
                listPreference?.entryValues =
                    requireContext().resources.getStringArray(R.array.mode_values_without_proximity_sensor)
            }
            currentMode = CounterPreferences.getPreferencesCurrentMode(
                requireContext()
            )
            newMode = currentMode
            listPreference?.setValueIndex(currentMode)
            listPreference?.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference, newValue ->
                    if (preference is ListPreference) {
                        newMode = preference.entryValues.indexOf(newValue)
                        CounterPreferences.setPreferencesCurrentMode(requireContext(), newMode)
                    }
                    true
                }
        }

        override fun onDestroy() {
            if (currentMode != newMode) {
                CounterPreferences.setChangedModeCounter(requireContext(), true)
            }
            super.onDestroy()
        }
    }
}
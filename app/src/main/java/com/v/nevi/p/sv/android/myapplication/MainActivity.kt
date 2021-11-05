package com.v.nevi.p.sv.android.myapplication

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.v.nevi.p.sv.android.myapplication.HistoryFragment.RecreateHistoryFragment


class MainActivity : AppCompatActivity(), CounterCallback, HistoryFragment.DeleteCallback,
    RecreateHistoryFragment {

    private var timeLastBack: Long = 0
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController


    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        navController.addOnDestinationChangedListener { _, _, _ -> toast?.cancel() }
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        if (callingActivity != null) {
            navController.navigate(R.id.HistoryFragment)
        }
    }

    override fun startCounter() {
        navController.navigate(R.id.counter_activity)
    }

    override fun startDeleteDialog() {
        val dialogDelete = DeleteHistoryDialogFragment()
        dialogDelete.show(supportFragmentManager, null)
    }

    override fun recreateHistoryFragment() {
        recreate()
    }

    override fun onBackPressed() {
        if (timeLastBack + 2000 < System.currentTimeMillis()) {
            timeLastBack = System.currentTimeMillis()
            toast = Toast.makeText(this, R.string.click_again, Toast.LENGTH_LONG)
            toast?.show()
        } else {
            super.onBackPressed()
            toast?.cancel()
        }
    }
}


